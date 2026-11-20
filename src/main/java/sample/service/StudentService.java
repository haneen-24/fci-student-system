package sample.service;

import sample.model.Student;
import sample.repository.Repository;
import sample.validation.StudentValidator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final Repository<Student> studentRepository;
    private final StudentValidator studentValidator;

    public StudentService(
            Repository<Student> studentRepository,
            StudentValidator studentValidator) {

        this.studentRepository = studentRepository;
        this.studentValidator = studentValidator;
    }

    public String addStudent(Student student) {

        String error = studentValidator.validate(student);

        if (error != null) {
            return error;
        }

        for (Student existingStudent : studentRepository.findAll()) {

            if (existingStudent.getStudentId()
                    .equals(student.getStudentId())) {

                return "Student ID already exists";
            }
        }

        studentRepository.add(student);

        return null;
    }

    public String editStudent(Student student) {

        String error = studentValidator.validate(student);

        if (error != null) {
            return error;
        }

        Student existingStudent = null;

        for (Student currentStudent : studentRepository.findAll()) {

            if (currentStudent.getStudentId()
                    .equals(student.getStudentId())) {

                existingStudent = currentStudent;
                break;
            }
        }

        if (existingStudent == null) {
            return "Student ID does not exist";
        }

        studentRepository.update(existingStudent, student);

        return null;
    }

    public String deleteStudent(String studentId) {

        if (studentId == null || studentId.isBlank()) {
            return "Student ID is required";
        }

        Student existingStudent = null;

        for (Student student : studentRepository.findAll()) {

            if (student.getStudentId().equals(studentId)) {

                existingStudent = student;
                break;
            }
        }

        if (existingStudent == null) {
            return "Student ID does not exist";
        }

        studentRepository.delete(existingStudent);

        return null;
    }

    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }
    public List<Student> search(String text) {

    String keyword = text.toLowerCase().trim();

    List<Student> result = new ArrayList<>();

    for (Student student : studentRepository.findAll()) {

        boolean nameMatches = student.getStudentName()
                .toLowerCase().contains(keyword);

        boolean idMatches = student.getStudentId()
                .toLowerCase().contains(keyword);

        if (nameMatches || idMatches) {
            result.add(student);
        }
    }

    return result;
}
}
