package sample.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddStudentView {
    private final Stage stage; 
    
    private final TextField IDField;
    private final TextField NameField;
    private final TextField emailField;
    private final TextField phoneField;
    private final TextField departmentField;
    private final TextField academicYearField;

    private final Button saveButton;
    private final Button cancelButton;
    public AddStudentView() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Student");

        Label title =
                new Label("Add New Student");

        Label IDLabel = new Label("ID:");
        IDField = new TextField();
          IDField.setPromptText(
                "Enter student ID"
        );


        Label nameLabel = new Label("Name:");
        NameField = new TextField();
        NameField.setPromptText(
                "Enter student name"
        );

        Label emailLabel = new Label("Email:");
        emailField = new TextField();
        emailField.setPromptText(
                "Enter student email"
        );

        Label phoneLabel = new Label("Phone:");
        phoneField = new TextField();
        phoneField.setPromptText(
                "Enter student phone"
        );

        Label departmentLabel = new Label("Department:");
        departmentField = new TextField();
        departmentField.setPromptText(
                "Enter student department"
        );

        Label academicYearLabel = new Label("Academic Year:");
        academicYearField = new TextField();
        academicYearField.setPromptText(
                "Enter academic year"
        );

        saveButton = new Button("Save");
        cancelButton = new Button("Cancel"); 

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(IDLabel, 0, 0);
        gridPane.add(IDField, 1, 0);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(NameField, 1, 1);
        gridPane.add(emailLabel, 0, 2);
        gridPane.add(emailField, 1, 2);
        gridPane.add(phoneLabel, 0, 3);
        gridPane.add(phoneField, 1, 3);
        gridPane.add(departmentLabel, 0, 4);
        gridPane.add(departmentField, 1, 4);
        gridPane.add(academicYearLabel, 0, 5);
        gridPane.add(academicYearField, 1, 5);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.getChildren().addAll(saveButton, cancelButton);

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(title, gridPane, buttonBox);

        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
    }
    public Stage getStage() {
        return stage;
    }
    public TextField getIDField() {
        return IDField;
    }
    public TextField getNameField() {
        return NameField;
    }
    public TextField getEmailField() {
        return emailField;
    }
    public TextField getPhoneField() {
        return phoneField;
    }
    public TextField getDepartmentField() {
        return departmentField;
    }
    public TextField getAcademicYearField() {
        return academicYearField;
    }
    public Button getSaveButton() {
        return saveButton;
    }
    public Button getCancelButton() {
        return cancelButton;
    }
    public void show() {
        stage.show();
    }
    public void showAndWait() {
        stage.showAndWait();
    }
}

