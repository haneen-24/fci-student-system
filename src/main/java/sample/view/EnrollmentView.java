package sample.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import sample.model.Enrollment;

public class EnrollmentView {

    private final BorderPane root;

    private TextField studentIdField;
    private TextField courseIdField;

    private Button enrollButton;
    private TableView<Enrollment> enrollmentTable;

    public EnrollmentView() {

        root = new BorderPane();

        createLayout();
    }

    private void createLayout() {

        Label title = new Label("Student Enrollment");

        title.setStyle(
                "-fx-font-size: 24px; -fx-font-weight: bold;"
        );

        VBox header = new VBox(title);
        header.setPadding(new Insets(20));

        root.setTop(header);

        root.setCenter(createMainContent());
    }

    private VBox createMainContent() {

        VBox content = new VBox(20);

        content.setPadding(new Insets(25));

        Label description =
                new Label("Register students in courses");

        GridPane form = new GridPane();

        form.setHgap(15);
        form.setVgap(15);

        Label studentLabel = new Label("Student ID:");

        studentIdField = new TextField();
        studentIdField.setPromptText("Enter Student ID");

        Label courseLabel = new Label("Course ID:");

        courseIdField = new TextField();
        courseIdField.setPromptText("Enter Course ID");

        form.add(studentLabel, 0, 0);
        form.add(studentIdField, 1, 0);

        form.add(courseLabel, 0, 1);
        form.add(courseIdField, 1, 1);

        enrollButton = new Button("Enroll Student");

        enrollButton.setPrefWidth(150);

        VBox formBox = new VBox(15);
        formBox.getChildren().addAll(form, enrollButton);

        enrollmentTable = new TableView<>();

        TableColumn<Enrollment, Integer> studentColumn =
                new TableColumn<>("Student ID");

        studentColumn.setCellValueFactory(
                new PropertyValueFactory<>("studentId")
        );

        TableColumn<Enrollment, String> courseColumn =
                new TableColumn<>("Course Code");

        courseColumn.setCellValueFactory(
                new PropertyValueFactory<>("courseCode")
        );

        enrollmentTable.getColumns().addAll(
                studentColumn,
                courseColumn
        );

        enrollmentTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        content.getChildren().addAll(
                description,
                formBox,
                new Label("All Enrollments"),
                enrollmentTable
        );

        return content;
    }

    public BorderPane getRoot() {
        return root;
    }

    public TextField getStudentIdField() {
        return studentIdField;
    }

    public TextField getCourseIdField() {
        return courseIdField;
    }

    public Button getEnrollButton() {
        return enrollButton;
    }

    public TableView<Enrollment> getEnrollmentTable() {
        return enrollmentTable;
    }
}
