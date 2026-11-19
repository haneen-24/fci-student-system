package sample.service;

import sample.model.Course;
import sample.repository.Repository;
import sample.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private final Repository<Course> repository;
    private final Validator<Course> validator;

    public CourseService(Repository<Course> repository,
                         Validator<Course> validator){
        this.repository=repository;
        this.validator=validator;
    }
    
    public List<Course> getall(){
        return repository.findAll();
    }

    public String add(Course course){
        String error = validator.validate(course);
        if (error != null){
            return error;
        }

        if (codeExists(course.getCode())){
            return "Course code already exists";
        }

        repository.add(course);
        return null;
    }

    public String update(Course oldCourse, Course newCourse) {
        String error = validator.validate(newCourse);
        if (error != null){
            return error;
        }

        boolean codeChange = !oldCourse.getCode()
                .equalsIgnoreCase(newCourse.getCode());
        if (codeChange && codeExists(newCourse.getCode())) {
            return "Course code already exists";
        }

        repository.update(oldCourse, newCourse);
        return null;
    }

    public List<Course> search(String text){
        String keyword = text.toLowerCase().trim();
        List<Course> result = new ArrayList<>();
        for (Course course : repository.findAll()){
            boolean nameMatches = course.getName()
            .toLowerCase().contains(keyword);
            boolean codeMatches = course.getCode()
            .toLowerCase().contains(keyword);

            if (nameMatches || codeMatches){
                result.add(course);
            }
        }
        return result;
    }
    
    private boolean codeExists(String code){
        for (Course course : repository.findAll()){
            if (course.getCode().equalsIgnoreCase(code)){
                return true;
            }
        }
        return false;
    }
}
