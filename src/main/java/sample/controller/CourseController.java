package sample.controller;  
                                       
import javafx.collections.FXCollections;                          
import javafx.collections.ObservableList;                          

import sample.model.Course;                                       
import sample.service.CourseService;                               
import sample.util.AlertHelper;                                    
import sample.view.CourseView;                                     
import sample.view.CourseView.CourseForm;                         

public class CourseController {                                   

    private final CourseView view;                                 
    private final CourseService service;                          

    public CourseController(CourseService service) {              
        this.service = service;                                   
        this.view = new CourseView();                              

        refreshTable();                                            

        setupSearch();                                            
        setupAddButton();                                         
        setupEditButton();                                         
    }

    private void refreshTable() {                                
        ObservableList<Course> courses =                          
                FXCollections.observableArrayList(service.getAll());
        view.getCourseTable().setItems(courses);                  
    }

    private void setupSearch() {                                  
        view.getSearchField().textProperty().addListener(         
                (observable, oldValue, newValue) -> {             
                    ObservableList<Course> result =               
                            FXCollections.observableArrayList(
                                    service.search(newValue));
                    view.getCourseTable().setItems(result);        
                });
    }


    private void setupAddButton() {                                
        view.getAddButton().setOnAction(event -> addCourse());     
    }

    private void addCourse() {                                     
        CourseForm form = new CourseForm("Add Course", null);      

        form.getSaveButton().setOnAction(event -> {                
            Course course = readCourse(form);                      
            if (course == null) {                                  
                return;                                            
            }

            String error = service.add(course);                    
            if (error != null) {                                   
                AlertHelper.showError("Invalid Data", error);      
                return;                                            
            }

            form.getStage().close();                               
            refreshTable();                                        
            AlertHelper.showInfo("Success",                        
                    "Course added successfully.");
        });

        form.getCancelButton().setOnAction(                        
                event -> form.getStage().close());                 

        form.show();                                               
    }


    private void setupEditButton() {                               
        view.getEditButton().setOnAction(event -> editCourse());   
    }

    private void editCourse() {                                    
        Course selected = view.getCourseTable()                    
                .getSelectionModel().getSelectedItem();

        if (selected == null) {                                    
            AlertHelper.showError("No Course Selected",            
                    "Please select a course first.");
            return;                                                
        }

        CourseForm form = new CourseForm("Edit Course", selected); 

        form.getSaveButton().setOnAction(event -> {                
            Course updated = readCourse(form);                     
            if (updated == null) {                                 
                return;                                            
            }

            String error = service.update(selected, updated);      
            if (error != null) {                                   
                AlertHelper.showError("Invalid Data", error);      
                return;                                            
            }

            form.getStage().close();                               
            refreshTable();                                        
            AlertHelper.showInfo("Success",                        
                    "Course updated successfully.");
        });

        form.getCancelButton().setOnAction(                        
                event -> form.getStage().close());                 

        form.show();                                              
    }

    private Course readCourse(CourseForm form) {                   
        try {
            String code = form.getCodeField().getText().trim();    
            String name = form.getNameField().getText().trim();   
            int hours = Integer.parseInt(                          
                    form.getHoursField().getText().trim());
            return new Course(code, name, hours);                  
        } catch (NumberFormatException e) {                        
            AlertHelper.showError("Invalid Data",                 
                    "Credit hours must be a whole number.");
            return null;                                           
        }
    }

    public CourseView getView() {                                 
        return view;
    }
}