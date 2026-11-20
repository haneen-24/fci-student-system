package sample.service;


import sample.model.Enrollment;
import sample.repository.Repository;
import sample.validation.Validator;

import java.util.List;

public class EnrollmentService {

    private final Repository<Enrollment> repository;
    private final Validator<Enrollment> validator;

    public EnrollmentService(
            Repository<Enrollment> repository,
            Validator<Enrollment> validator) {

        this.repository = repository;
        this.validator = validator;
    }

    public void enrollStudent(int studentId, String courseCode) {

        Enrollment enrollment =
                new Enrollment(studentId, courseCode);

        String error = validator.validate(enrollment);
        if (error != null) {                                   
            throw new IllegalArgumentException(error);
        }

        if (isAlreadyEnrolled(studentId, courseCode)) {
            throw new IllegalArgumentException(
                    "Student is already enrolled in this course."
            );
        }

        repository.add(enrollment);
    }

    public boolean isAlreadyEnrolled(
            int studentId, String courseCode) {

        List<Enrollment> enrollments =
                repository.findAll();

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getStudentId() == studentId
                    && enrollment.getCourseCode().equalsIgnoreCase(courseCode)) {

                return true;
            }
        }

        return false;
    }

    public List<Enrollment> getAllEnrollments() {
        return repository.findAll();
    }
}
