package sample.repository;

import sample.model.Grade;

public class FileGradeRepository extends FileRepository<Grade> {

    public FileGradeRepository() {
        super("data/grades.txt");
    }

    @Override
    protected String toLine(Grade g) {
        return g.getStudentId() + "," + g.getCourseCode() + "," + g.getScore();
    }

    @Override
    protected Grade fromLine(String line) {
        String[] p = line.split(",");
        return new Grade(
                Integer.parseInt(p[0].trim()),
                p[1].trim(),
                Double.parseDouble(p[2].trim()));
    }

    @Override
    protected boolean sameKey(Grade a, Grade b) {
        return a.getStudentId() == b.getStudentId()
                && a.getCourseCode().equalsIgnoreCase(b.getCourseCode());
    }
}
