package sample.repository;                                           // نفس الـ package

import sample.model.Grade;                                           // الـ model

public class FileGradeRepository extends FileRepository<Grade> {     // بيورّث الشغل المشترك

    public FileGradeRepository() {                                   // الـ constructor
        super("data/grades.txt");                                    // مسار ملف الدرجات
    }

    @Override                                                        // من الكلاس الأساسي
    protected String toLine(Grade g) {                               // درجة ← سطر
        return g.getStudentId() + "," + g.getCourseCode()            // studentId,courseCode
                + "," + g.getScore();                                // ,score
    }

    @Override                                                        // من الكلاس الأساسي
    protected Grade fromLine(String line) {                          // سطر ← درجة
        String[] p = line.split(",");                                // قسّم عند الفاصلة
        return new Grade(Integer.parseInt(p[0].trim()),              // رقم الطالب
                p[1].trim(),                                         // كود المادة
                Double.parseDouble(p[2].trim()));                    // الدرجة
    }

    @Override                                                        // من الكلاس الأساسي
    protected boolean sameKey(Grade a, Grade b) {                    // المفتاح = الطالب + المادة
        return a.getStudentId() == b.getStudentId()                  // نفس الطالب
                && a.getCourseCode()
                        .equalsIgnoreCase(b.getCourseCode());        // ونفس المادة
    }
}
