package sample.repository;

import sample.model.Course;

public class FileCourseRepository extends FileRepository<Course> {

    public FileCourseRepository() {
        super("data/courses.txt");
    }

    @Override
    protected String toLine(Course c) {
        return c.getCode() + "," + c.getName() + "," + c.getCreditHours();
    }

    @Override
    protected Course fromLine(String line) {
        String[] p = line.split(",");
        return new Course(p[0].trim(), p[1].trim(), Integer.parseInt(p[2].trim()));
    }

    @Override
    protected boolean sameKey(Course a, Course b) {
        return a.getCode().equalsIgnoreCase(b.getCode());
    }
}
