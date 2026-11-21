package sample.repository;                                           // نفس package الـ Repository

import java.io.IOException;                                          // أخطاء القراءة والكتابة
import java.nio.charset.StandardCharsets;                            // ترميز UTF-8 عشان الحروف العربي
import java.nio.file.Files;                                          // قراءة وكتابة الملفات
import java.nio.file.Path;                                           // مسار الملف
import java.nio.file.Paths;                                          // لتحويل النص لمسار
import java.util.ArrayList;                                          // قايمة النتايج
import java.util.List;                                               // نوع القايمة

public abstract class FileRepository<T> implements Repository<T> {   // كلاس أساسي: بيعمل الشغل المشترك لكل الملفات

    private final Path path;                                         // مسار الملف (مثلاً data/courses.txt)

    protected FileRepository(String filePath) {                      // الـ constructor بياخد مسار الملف
        this.path = Paths.get(filePath);                             // حوّل النص لمسار وخزّنه
    }

    protected abstract String toLine(T item);                        // كل موديول يقول: إزاي أحوّل الكائن لسطر
    protected abstract T fromLine(String line);                      // وإزاي أحوّل السطر لكائن
    protected abstract boolean sameKey(T a, T b);                    // وإزاي أعرف إن الاتنين نفس العنصر (بالمفتاح)

    @Override                                                        // موجودة في Repository
    public List<T> findAll() {                                       // هات كل العناصر من الملف
        List<T> items = new ArrayList<>();                           // قايمة فاضية
        if (!Files.exists(path)) {                                   // لو الملف لسه مش موجود
            return items;                                            // رجّع قايمة فاضية
        }
        try {
            for (String line : Files.readAllLines(                   // اقري كل الأسطر
                    path, StandardCharsets.UTF_8)) {
                if (line.isBlank()) {                                // لو السطر فاضي
                    continue;                                        // تجاهله
                }
                try {
                    items.add(fromLine(line));                       // حوّل السطر لكائن وضيفه
                } catch (RuntimeException e) {                       // لو السطر بايظ (فاصلة زيادة مثلاً)
                    System.err.println("Skipped bad line: " + line); // اطبع تنبيه وكمّل
                }
            }
        } catch (IOException e) {                                    // لو حصلت مشكلة في الملف
            e.printStackTrace();                                     // اطبع الخطأ
        }
        return items;                                                // رجّع العناصر
    }

    @Override                                                        // موجودة في Repository
    public void add(T item) {                                        // ضيف عنصر
        List<T> items = findAll();                                   // هات الموجود
        items.add(item);                                             // ضيف الجديد
        saveAll(items);                                              // احفظ الكل
    }

    @Override                                                        // موجودة في Repository
    public void update(T oldItem, T newItem) {                       // استبدل عنصر بعنصر
        List<T> items = findAll();                                   // هات الموجود
        for (int i = 0; i < items.size(); i++) {                     // لف على العناصر
            if (sameKey(items.get(i), oldItem)) {                    // لو ده العنصر القديم (بالمفتاح)
                items.set(i, newItem);                               // استبدله بالجديد
                break;                                               // كفاية كده
            }
        }
        saveAll(items);                                              // احفظ الكل
    }

    @Override                                                        // موجودة في Repository
    public void delete(T item) {                                     // امسح عنصر
        List<T> items = findAll();                                   // هات الموجود
        items.removeIf(existing -> sameKey(existing, item));         // شيل اللي مفتاحه مطابق
        saveAll(items);                                              // احفظ الكل
    }

    private void saveAll(List<T> items) {                            // اكتب كل العناصر في الملف
        List<String> lines = new ArrayList<>();                      // قايمة أسطر
        for (T item : items) {                                       // لف على العناصر
            lines.add(toLine(item));                                 // حوّل كل عنصر لسطر
        }
        try {
            if (path.getParent() != null) {                          // لو الملف جوه فولدر
                Files.createDirectories(path.getParent());           // اعمل الفولدر لو مش موجود
            }
            Files.write(path, lines, StandardCharsets.UTF_8);        // اكتب الأسطر (بيمسح القديم)
        } catch (IOException e) {                                    // لو فشلت الكتابة
            e.printStackTrace();                                     // اطبع الخطأ
        }
    }
}
