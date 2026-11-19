package sample.validation;

import sample.model.Course;

public class CourseValidator implements Validator<Course>{
     
    @Override
    public String validate(Course course){

        if (course.getCode() ==null 
                || course.getCode().trim().isEmpty()){
            return "Course code cannot be empty";
        }
        
        if (course.getName() ==null
                || course.getName().trim().isEmpty()){
            return "Course name cannot be empty";
        }

        if (course.getCreditHours() <= 0){
            return "Credit hours must be greater than zero";
        }
        return null;
    }
}