package sample.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainView {

    private final BorderPane root = new BorderPane();
    private final StackPane contentArea = new StackPane();

    private final Button homeButton = createMenuButton("Home");
    private final Button studentsButton = createMenuButton("Students");
    private final Button coursesButton = createMenuButton("Courses");
    private final Button enrollmentButton = createMenuButton("Enrollment");
    private final Button gradesButton = createMenuButton("Grades");

    public MainView() {
        root.setTop(createHeader());
        root.setLeft(createSidebar());
        root.setCenter(contentArea);
    }

    private HBox createHeader() {
        Label title = new Label("Student Management System");
        Label subtitle = new Label("University Portal");
        VBox branding = new VBox(3, title, subtitle);

        HBox header = new HBox(branding);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 25, 15, 25));
        return header;
    }

    private VBox createSidebar() {
        Label menuTitle = new Label("MENU");

        VBox sidebar = new VBox(10, menuTitle, homeButton, studentsButton,
                coursesButton, enrollmentButton, gradesButton);
        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(220);
        return sidebar;
    }

    private Button createMenuButton(String text) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setPrefHeight(42);
        button.setAlignment(Pos.CENTER_LEFT);
        return button;
    }

    // Swap whatever is shown in the middle of the window
    public void setContent(Node node) {
        contentArea.getChildren().setAll(node);
    }

    public BorderPane getRoot() { return root; }
    public Button getHomeButton() { return homeButton; }
    public Button getStudentsButton() { return studentsButton; }
    public Button getCoursesButton() { return coursesButton; }
    public Button getEnrollmentButton() { return enrollmentButton; }
    public Button getGradesButton() { return gradesButton; }
}