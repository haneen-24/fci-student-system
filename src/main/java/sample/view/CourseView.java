package sample.view;                                             

import javafx.geometry.Insets;                                    
import javafx.geometry.Pos;                                       
import javafx.scene.Scene;                                        
import javafx.scene.control.Button;                              
import javafx.scene.control.Label;                               
import javafx.scene.control.TableColumn;                         
import javafx.scene.control.TableView;                            
import javafx.scene.control.TextField;                            
import javafx.scene.control.cell.PropertyValueFactory;           
import javafx.scene.layout.GridPane;                              
import javafx.scene.layout.HBox;                                  
import javafx.scene.layout.Priority;                             
import javafx.scene.layout.VBox;                                  
import javafx.stage.Modality;                                     
import javafx.stage.Stage;                                        

import sample.model.Course;                                       
public class CourseView {                                        

    private final VBox root;                                      
    private TextField searchField;                                
    private TableView<Course> courseTable;                        
    private Button addButton;                                     
    private Button editButton;                                   

    public CourseView() {                                         
        root = new VBox(20);                                      
        root.setPadding(new Insets(30));                         

        Label title = new Label("Courses");                       
        Label description = new Label("Search and manage courses");  
        VBox titleSection = new VBox(5, title, description);     

        root.getChildren().addAll(                                
                titleSection,                                     
                createSearchSection(),                           
                createCourseTable()                              
        );

        VBox.setVgrow(courseTable, Priority.ALWAYS);              
    }
    private HBox createSearchSection() {                          
        searchField = new TextField();                           
        searchField.setPromptText("Search by name or code...");  
        searchField.setPrefHeight(40);                           

        addButton = new Button("➕ Add Course");                 
        editButton = new Button("✏ Edit Course");                
        addButton.setPrefHeight(40);                              
        editButton.setPrefHeight(40);                            

        HBox box = new HBox(10, searchField, addButton, editButton);  
        box.setAlignment(Pos.CENTER_LEFT);                        
        HBox.setHgrow(searchField, Priority.ALWAYS);              
        return box;                                               
    }

    private TableView<Course> createCourseTable() {               
        courseTable = new TableView<>();                         

        TableColumn<Course, String> codeColumn =                  
                new TableColumn<>("Code");                       
        codeColumn.setCellValueFactory(                           
                new PropertyValueFactory<>("code"));              

        TableColumn<Course, String> nameColumn =                  
                new TableColumn<>("Course Name");                
        nameColumn.setCellValueFactory(                           
                new PropertyValueFactory<>("name"));              

        TableColumn<Course, Integer> hoursColumn =                
                new TableColumn<>("Credit Hours");                
        hoursColumn.setCellValueFactory(                          
                new PropertyValueFactory<>("creditHours"));       

        courseTable.getColumns().addAll(                          
                codeColumn, nameColumn, hoursColumn);
        courseTable.setColumnResizePolicy(                        
                TableView.CONSTRAINED_RESIZE_POLICY);
        return courseTable;                                       
    }

    public VBox getRoot() {                                       
        return root;
    }

    public TextField getSearchField() {                           
        return searchField;
    }

    public TableView<Course> getCourseTable() {                   
        return courseTable;
    }

    public Button getAddButton() {                                
        return addButton;
    }

    public Button getEditButton() {                               
        return editButton;
    }

    public static class CourseForm {                              

        private final Stage stage;                                
        private final TextField codeField;                        
        private final TextField nameField;                       
        private final TextField hoursField;                       
        private final Button saveButton;                          
        private final Button cancelButton;                        

        public CourseForm(String windowTitle, Course course) {    
            stage = new Stage();                                  
            stage.setTitle(windowTitle);                         
            stage.initModality(Modality.APPLICATION_MODAL);       

            codeField = new TextField();                          
            nameField = new TextField();                          
            hoursField = new TextField();                         
            codeField.setPromptText("e.g. CS101");                
            nameField.setPromptText("e.g. Programming 1");        
            hoursField.setPromptText("e.g. 3");                   

            if (course != null) {                                 
                codeField.setText(course.getCode());              
                nameField.setText(course.getName());              
                hoursField.setText(                               
                        String.valueOf(course.getCreditHours())); 
            }

            GridPane form = new GridPane();                      
            form.setHgap(15);                                     
            form.setVgap(15);                                    
            form.add(new Label("Course Code"), 0, 0);             
            form.add(codeField, 1, 0);                            
            form.add(new Label("Course Name"), 0, 1);             
            form.add(nameField, 1, 1);                            
            form.add(new Label("Credit Hours"), 0, 2);           
            form.add(hoursField, 1, 2);                           

            saveButton = new Button("Save");                     
            cancelButton = new Button("Cancel");                  
            HBox buttons = new HBox(10, saveButton, cancelButton);
            buttons.setAlignment(Pos.CENTER_RIGHT);               

            VBox formRoot = new VBox(20, new Label(windowTitle),  
                    form, buttons);
            formRoot.setPadding(new Insets(25));                  

            stage.setScene(new Scene(formRoot, 420, 280));        
        }

        public TextField getCodeField() {                         
            return codeField;
        }

        public TextField getNameField() {                         
            return nameField;
        }

        public TextField getHoursField() {                        
            return hoursField;
        }

        public Button getSaveButton() {                           
            return saveButton;
        }

        public Button getCancelButton() {                         
            return cancelButton;
        }

        public Stage getStage() {                                 
            return stage;
        }

        public void show() {                                      
            stage.showAndWait();                                  
        }
    }
}
