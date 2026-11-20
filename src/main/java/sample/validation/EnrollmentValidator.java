package sample.validation;

import sample.model.Enrollment;

public class EnrollmentValidator implements Validator<Enrollment> {

    @Override
    public String validate(Enrollment enrollment) {          

        if (enrollment == null) {                            
            return "Enrollment cannot be null.";             
        }

        if (enrollment.getStudentId() <= 0) {                
            return "Invalid student ID.";                    
        }

        if (enrollment.getCourseCode() == null               
                || enrollment.getCourseCode().trim().isEmpty()) {  
            return "Course code cannot be empty.";           
        }

        return null;                                         
    }
}