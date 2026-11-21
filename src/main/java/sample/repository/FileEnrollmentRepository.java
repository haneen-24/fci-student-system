package sample.repository;                                           // نفس الـ package

import sample.model.Enrollment;                                      // الـ model

public class FileEnrollmentRepository                                // بيورّث الشغل المشترك
        extends FileRepository<Enrollment> {

    public FileEnrollmentRepository() {                              // الـ constructor
        super("data/enrollments.txt");                               // مسار ملف التسجيلات
    }

    @Override                                                        // من الكلاس الأساسي
    protected String toLine(Enrollment e) {                          // تسجيل ← سطر
        return e.getStudentId() + "," + e.getCourseCode();           // studentId,courseCode
    }

    @Override                                                        // من الكلاس الأساسي
    protected Enrollment fromLine(String line) {                     // سطر ← تسجيل
        String[] p = line.split(",");                                // قسّم عند الفاصلة
        return new Enrollment(Integer.parseInt(p[0].trim()),         // رقم الطالب
                p[1].trim());                                        // كود المادة
    }

    @Override                                                        // من الكلاس الأساسي
    protected boolean sameKey(Enrollment a, Enrollment b) {          // المفتاح = الطالب + المادة
        return a.getStudentId() == b.getStudentId()                  // نفس الطالب
                && a.getCourseCode()
                        .equalsIgnoreCase(b.getCourseCode());        // ونفس المادة
    }
}
