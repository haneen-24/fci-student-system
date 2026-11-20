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

import sample.model.Grade;                                        

public class GradeView {                                          

    private final VBox root;                                      
    private TextField searchField;                                
    private TableView<Grade> gradeTable;                          
    private Button addButton;                                     
    private Button editButton;                                    

    public GradeView() {                                          
        root = new VBox(20);                                      
        root.setPadding(new Insets(30));                          

        Label title = new Label("Grades");                        
        Label description = new Label("Search and manage student grades"); 
        VBox titleSection = new VBox(5, title, description);     

        root.getChildren().addAll(                                
                titleSection, 
                createSearchSection(),  
                createGradeTable()                              
        );

        VBox.setVgrow(gradeTable, Priority.ALWAYS);
    }

    private HBox createSearchSection() { 
        searchField = new TextField();
        searchField.setPromptText("Search by student ID or course code..."); 
        searchField.setPrefHeight(40);

        addButton = new Button("➕ Add Grade");
        editButton = new Button("✏ Edit Grade"); 
        addButton.setPrefHeight(40); 
        editButton.setPrefHeight(40); 

        HBox box = new HBox(10, searchField, addButton, editButton); 
        box.setAlignment(Pos.CENTER_LEFT);                       
        HBox.setHgrow(searchField, Priority.ALWAYS);             
        return box;                                               
    }

    private TableView<Grade> createGradeTable() {                 
        gradeTable = new TableView<>();                           

        TableColumn<Grade, Integer> studentColumn =              
                new TableColumn<>("Student ID");                 
        studentColumn.setCellValueFactory(                       
                new PropertyValueFactory<>("studentId"));        

        TableColumn<Grade, String> courseColumn =                 
                new TableColumn<>("Course Code");                 
        courseColumn.setCellValueFactory(                         
                new PropertyValueFactory<>("courseCode"));        

        TableColumn<Grade, Double> scoreColumn =                  
                new TableColumn<>("Score");                       
        scoreColumn.setCellValueFactory(                         
                new PropertyValueFactory<>("score"));            

        gradeTable.getColumns().addAll( 
                studentColumn, courseColumn, scoreColumn);
        gradeTable.setColumnResizePolicy(  
                TableView.CONSTRAINED_RESIZE_POLICY);
        return gradeTable; 
    }

    public VBox getRoot() {
        return root;
    }

    public TextField getSearchField() {
        return searchField;
    }

    public TableView<Grade> getGradeTable() {                     
        return gradeTable;
    }

    public Button getAddButton() {                                
        return addButton;
    }

    public Button getEditButton() {                               
        return editButton;
    }

    public static class GradeForm { 

        private final Stage stage; 
        private final TextField studentIdField; 
        private final TextField courseCodeField;
        private final TextField scoreField;
        private final Button saveButton;
        private final Button cancelButton;
        public GradeForm(String windowTitle, Grade grade) {
            stage = new Stage(); 
            stage.setTitle(windowTitle);
            stage.initModality(Modality.APPLICATION_MODAL);

            studentIdField = new TextField();
            courseCodeField = new TextField();
            scoreField = new TextField();
            studentIdField.setPromptText("e.g. 1");
            courseCodeField.setPromptText("e.g. CS101");
            scoreField.setPromptText("0 - 100");

            if (grade != null) { 
                studentIdField.setText(
                        String.valueOf(grade.getStudentId()));
                courseCodeField.setText(grade.getCourseCode());
                scoreField.setText(
                        String.valueOf(grade.getScore()));
            }

            GridPane form = new GridPane();
            form.setHgap(15);
            form.setVgap(15);
            form.add(new Label("Student ID"), 0, 0);
            form.add(studentIdField, 1, 0); 
            form.add(new Label("Course Code"), 0, 1);
            form.add(courseCodeField, 1, 1);
            form.add(new Label("Score"), 0, 2);
            form.add(scoreField, 1, 2);

            saveButton = new Button("Save");
            cancelButton = new Button("Cancel"); 
            HBox buttons = new HBox(10, saveButton, cancelButton); 
            buttons.setAlignment(Pos.CENTER_RIGHT);               

            VBox formRoot = new VBox(20, new Label(windowTitle),  
                    form, buttons);
            formRoot.setPadding(new Insets(25));                 

            stage.setScene(new Scene(formRoot, 420, 280));        
        }

        public TextField getStudentIdField() {                    
            return studentIdField;
        }

        public TextField getCourseCodeField() {                  
            return courseCodeField;
        }

        public TextField getScoreField() {                        
            return scoreField;
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
