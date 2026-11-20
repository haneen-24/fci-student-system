package sample.view;
import sample.model.Student;
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

public class EditeStudentView {
    private final Stage stage;
    private final TextField IDField;
    private final TextField NameField;
    private final TextField emailField;
    private final TextField phoneField;
    private final TextField departmentField;
    private final TextField academicYearField;    
    
    private final Button Edit;
    private final Button cancel;  
    public EditeStudentView(Student student) {

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit Student");
        Label title =
                new Label("Edit Student");
        
        Label IDLabel = new Label("ID:");
        IDField = new TextField();
        IDField.setEditable(false);

        Label nameLabel = new Label("Name:");
        NameField = new TextField();
       

        Label emailLabel = new Label("Email:");
        emailField = new TextField();

        Label phoneLabel = new Label("Phone:");
        phoneField = new TextField();

        Label departmentLabel = new Label("Department:");
        departmentField = new TextField();

        Label academicYearLabel = new Label("Academic Year:");
        academicYearField = new TextField();
        IDField.setText(student.getStudentId());
        NameField.setText(student.getStudentName());
        emailField.setText(student.getStudentEmail());
        phoneField.setText(student.getStudentPhone());
        departmentField.setText(student.getStudentDepartment());
        academicYearField.setText(String.valueOf(student.getAcademicYear()));

        Edit = new Button("Edit");
        cancel = new Button("Cancel");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(15);
        gridPane.setVgap(15);

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
        buttonBox.getChildren().addAll(Edit, cancel);

        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25));
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
    public Button getEditButton() {
        return Edit;
    }
    public Button getCancelButton() {
        return cancel;
    }
    public void show() {
        stage.show();
    }
    public void showAndWait() {
        stage.showAndWait();
}
}