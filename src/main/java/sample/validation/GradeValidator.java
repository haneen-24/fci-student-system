package sample.validation;                                  

import sample.model.Grade;                                  

public class GradeValidator implements Validator<Grade> {   

    @Override                                               
    public String validate(Grade grade) {  
                 
        if (grade == null) {                               
            return "Grade cannot be null.";                 
        }

        if (grade.getStudentId() <= 0) {                   
            return "Invalid student ID.";                   
        }

        if (grade.getCourseCode() == null                  
                || grade.getCourseCode().trim().isEmpty()) { 
            return "Course code cannot be empty.";         
        }

        if (grade.getScore() < 0 || grade.getScore() > 100) {  
            return "Score must be between 0 and 100.";      
        }

        return null;                                        
    }
}