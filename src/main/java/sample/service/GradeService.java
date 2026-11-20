package sample.service;                                     

import sample.model.Grade;                                  
import sample.repository.Repository;                        
import sample.validation.Validator;                        

import java.util.ArrayList;                                 
import java.util.List;                                      

public class GradeService {                                 

    private final Repository<Grade> repository;             
    private final Validator<Grade> validator;               

    public GradeService(Repository<Grade> repository,       
                        Validator<Grade> validator) {       
        this.repository = repository;                       
        this.validator = validator;                         
    }

    public List<Grade> getAll() {                           
        return repository.findAll();                        
    }

    public String add(Grade grade) {                        
        String error = validator.validate(grade);           
        if (error != null) {                                
            return error;                                  
        }

        if (exists(grade.getStudentId(), grade.getCourseCode())) { 
            return "This student already has a grade in this course."; 
        }

        repository.add(grade);                              
        return null;                                       
    }

    public String update(Grade oldGrade, Grade newGrade) { 
        String error = validator.validate(newGrade);        
        if (error != null) {                               
            return error;                                   
        }

        boolean keyChanged =                               
                oldGrade.getStudentId() != newGrade.getStudentId()
                || !oldGrade.getCourseCode()
                        .equalsIgnoreCase(newGrade.getCourseCode());

        if (keyChanged && exists(newGrade.getStudentId(), 
                newGrade.getCourseCode())) {
            return "This student already has a grade in this course."; 
        }

        repository.update(oldGrade, newGrade);          
        return null;                                      
    }

    public List<Grade> search(String text) {          
        String keyword = text.toLowerCase().trim();  
        List<Grade> result = new ArrayList<>(); 

        for (Grade grade : repository.findAll()) { 
            boolean idMatches = String.valueOf(grade.getStudentId())
                    .startsWith(keyword);     
            boolean codeMatches = grade.getCourseCode() 
                    .toLowerCase().startsWith(keyword); 

            if (idMatches || codeMatches) {  
                result.add(grade);           
            }
        }

        return result;                              
    }

    private boolean exists(int studentId, String courseCode) {  
        for (Grade grade : repository.findAll()) {      
            if (grade.getStudentId() == studentId  
                    && grade.getCourseCode()
                            .equalsIgnoreCase(courseCode)) { 
                return true;                            
            }
        }
        return false;                                    
    }
}
