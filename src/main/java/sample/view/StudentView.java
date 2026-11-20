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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import sample.model.Student;

public class StudentView {

    private final BorderPane root;

    private TextField searchField;

    private TableView<Student> studentTable;

    private Button addButton;
    private Button editButton;
    private Button deleteButton;

    public StudentView() {

        root = new BorderPane();

        createLayout();
    }


    private void createLayout() {

        root.setTop(createHeader());

        root.setLeft(createSidebar());

        root.setCenter(createMainContent());
    }



    private HBox createHeader() {

        Label logo = new Label("STUDENT MANAGEMENT");

        Label subtitle = new Label("Student Management System");

        VBox branding = new VBox(3);

        branding.getChildren().addAll(
                logo,
                subtitle
        );

        Label user = new Label("Admin ● Online");

        HBox header = new HBox();

        header.setAlignment(Pos.CENTER_LEFT);

        header.setPadding(new Insets(15, 25, 15, 25));

        header.setSpacing(20);

        header.getChildren().addAll(
                branding,
                user
        );

        HBox.setHgrow(branding, Priority.ALWAYS);

        return header;
    }


    private VBox createSidebar() {

        VBox sidebar = new VBox(10);

        sidebar.setPadding(new Insets(20));

        sidebar.setPrefWidth(220);

        Label managementTitle = new Label("MANAGEMENT");

        Button homeButton = createMenuButton("Home");

        addButton = createMenuButton("Add Student");

        editButton = createMenuButton("Edit Student");

        deleteButton = createMenuButton("Delete Student");

        VBox spacer = new VBox();

        VBox.setVgrow(spacer, Priority.ALWAYS);

        Button logoutButton = createMenuButton("Logout");

        sidebar.getChildren().addAll(

                managementTitle,

                homeButton,

                addButton,
                editButton,

                spacer,

                logoutButton
        );

        return sidebar;
    }

    // =========================
    // MENU BUTTON
    // =========================

    private Button createMenuButton(String text) {

        Button button = new Button(text);

        button.setMaxWidth(Double.MAX_VALUE);

        button.setPrefHeight(42);

        button.setAlignment(Pos.CENTER_LEFT);

        return button;
    }

    // =========================
    // MAIN CONTENT
    // =========================

    private VBox createMainContent() {

        VBox content = new VBox(20);

        content.setPadding(new Insets(30));

        Label title = new Label("Student Search");

        Label description = new Label(
                "Search and manage registered students"
        );

        VBox titleSection = new VBox(5);

        titleSection.getChildren().addAll(
                title,
                description
        );

        HBox searchSection = createSearchSection();

        Label tableTitle = new Label("Registered Students");

        TableView<Student> table = createStudentTable();

        content.getChildren().addAll(

                titleSection,

                searchSection,

                tableTitle,

                table
        );

        VBox.setVgrow(table, Priority.ALWAYS);

        return content;
    }

    // =========================
    // SEARCH AREA
    // =========================

    private HBox createSearchSection() {

        searchField = new TextField();

        searchField.setPromptText(
                "Search student by ID or name..."
        );

        searchField.setPrefHeight(45);

        Button searchButton = new Button("Search");

        searchButton.setPrefHeight(45);

        HBox searchBox = new HBox(10);

        searchBox.setAlignment(Pos.CENTER_LEFT);

        searchBox.getChildren().addAll(
                searchField,
                searchButton
        );

        HBox.setHgrow(searchField, Priority.ALWAYS);

        return searchBox;
    }

    // =========================
    // STUDENT TABLE
    // =========================

    private TableView<Student> createStudentTable() {

        studentTable = new TableView<>();

        // Student ID

        TableColumn<Student, String> idColumn =
                new TableColumn<>("Student ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("studentId")
        );

        // Student Name

        TableColumn<Student, String> nameColumn =
                new TableColumn<>("Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("studentName")
        );

        // Email

        TableColumn<Student, String> emailColumn =
                new TableColumn<>("Email");

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("studentEmail")
        );

        // Phone

        TableColumn<Student, String> phoneColumn =
                new TableColumn<>("Phone");

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("studentPhone")
        );

        // Department

        TableColumn<Student, String> departmentColumn =
                new TableColumn<>("Department");

        departmentColumn.setCellValueFactory(
                new PropertyValueFactory<>("studentDepartment")
        );

        // Academic Year

        TableColumn<Student, Integer> yearColumn =
                new TableColumn<>("Academic Year");

        yearColumn.setCellValueFactory(
                new PropertyValueFactory<>("academicYear")
        );

        studentTable.getColumns().addAll(
               idColumn,
                nameColumn,
                emailColumn,
                phoneColumn,
                departmentColumn,
                yearColumn
        );

        studentTable.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        return studentTable;
    }

    // =========================
    // GET ROOT
    // =========================

    public BorderPane getRoot() {

        return root;
    }

    // =========================
    // GET SEARCH FIELD
    // =========================

    public TextField getSearchField() {

        return searchField;
    }

    // =========================
    // GET STUDENT TABLE
    // =========================

    public TableView<Student> getStudentTable() {

        return studentTable;
    }

    // =========================
    // GET BUTTONS
    // =========================

    public Button getAddButton() {

        return addButton;
    }

    public Button getEditButton() {

        return editButton;
    }

    public Button getDeleteButton() {

        return deleteButton;
    }
}