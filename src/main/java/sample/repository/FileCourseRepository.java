package sample.repository;                                           // نفس الـ package

import sample.model.Course;                                          // الـ model

public class FileCourseRepository extends FileRepository<Course> {   // بيورّث الشغل المشترك

    public FileCourseRepository() {                                  // الـ constructor
        super("data/courses.txt");                                   // مسار ملف المواد
    }

    @Override                                                        // من الكلاس الأساسي
    protected String toLine(Course c) {                              // مادة ← سطر
        return c.getCode() + "," + c.getName() + "," + c.getCreditHours();  // code,name,creditHours
    }

    @Override                                                        // من الكلاس الأساسي
    protected Course fromLine(String line) {                         // سطر ← مادة
        String[] p = line.split(",");                                // قسّم عند الفاصلة
        return new Course(p[0].trim(), p[1].trim(),                  // الكود والاسم
                Integer.parseInt(p[2].trim()));                      // والساعات كرقم
    }

    @Override                                                        // من الكلاس الأساسي
    protected boolean sameKey(Course a, Course b) {                  // المفتاح = الكود
        return a.getCode().equalsIgnoreCase(b.getCode());            // نفس الكود؟
    }
}
