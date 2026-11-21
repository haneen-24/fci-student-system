package sample.repository;

import sample.model.Enrollment;

public class FileEnrollmentRepository extends FileRepository<Enrollment> {

    public FileEnrollmentRepository() {
        super("data/enrollments.txt");
    }

    @Override
    protected String toLine(Enrollment e) {
        return e.getStudentId() + "," + e.getCourseCode();
    }

    @Override
    protected Enrollment fromLine(String line) {
        String[] p = line.split(",");
        return new Enrollment(Integer.parseInt(p[0].trim()), p[1].trim());
    }

    @Override
    protected boolean sameKey(Enrollment a, Enrollment b) {
        return a.getStudentId() == b.getStudentId()
                && a.getCourseCode().equalsIgnoreCase(b.getCourseCode());
    }
}
