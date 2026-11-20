package sample.validation;
import sample.model.Student;

public class StudentValidator implements Validator <Student> {
    public String validate(Student student) { if (student == null) {
            return "Student cannot be null";
        }

        if (student.getStudentId() == null || student.getStudentId().isBlank()) {
            return "Student ID cannot be null or empty";
        }

        if (student.getStudentName() == null || student.getStudentName().isBlank()) {
            return "Student name cannot be null or empty";
        }

        if (student.getStudentEmail() == null || student.getStudentEmail().isBlank()|| !student.getStudentEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Student email is invalid";
        }

        if (student.getStudentPhone() == null || student.getStudentPhone().isBlank()|| !student.getStudentPhone().matches("^01[0125][0-9]{8}$")) {
            return "Invalid Egyptian phone number";
        }

        if (student.getStudentDepartment() == null || student.getStudentDepartment().isBlank()) {
            return "Student department cannot be null or empty";
        }

        if (student.getAcademicYear() < 1 || student.getAcademicYear() > 4) {
            return "Academic year must be between 1 and 4";
        }

        return null; 
    }
}

