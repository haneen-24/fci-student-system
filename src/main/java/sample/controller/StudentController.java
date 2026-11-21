package sample.controller;

import sample.service.StudentService;
import sample.view.StudentView;
import sample.view.AddStudentView;
import sample.view.EditeStudentView;
import sample.model.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Alert;

public class StudentController {

    private final StudentService studentService;
    private final StudentView view;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;

        this.view = new StudentView();

        refreshTable();

        setupSearch();

        setupAddButton();

        setupEditButton();

        setupDeleteButton();
    }

    private void refreshTable() {

        ObservableList<Student> students =
                FXCollections.observableArrayList(
                        studentService.getAllStudents()
                );

        view.getStudentTable().setItems(students);
    }

    private void setupSearch() {

        view.getSearchField().textProperty().addListener(
                (observable, oldValue, newValue) -> {

                    ObservableList<Student> result =
                            FXCollections.observableArrayList(
                                    studentService.search(newValue)
                            );

                    view.getStudentTable().setItems(result);
                }
        );
    }

    private void setupAddButton() {

        view.getAddButton().setOnAction(
                event -> openAddStudentView()
        );
    }

    private void openAddStudentView() {

        AddStudentView addView = new AddStudentView();

        addView.getCancelButton().setOnAction(event -> {
            addView.getStage().close();
        });

        addView.getSaveButton().setOnAction(event -> {

            try {

                String id = addView.getIDField().getText().trim();

                String name = addView.getNameField().getText().trim();

                String email = addView.getEmailField().getText().trim();

                String phone = addView.getPhoneField().getText().trim();

                String department =
                        addView.getDepartmentField().getText().trim();

                int academicYear = Integer.parseInt(
                        addView.getAcademicYearField().getText().trim()
                );

                Student student = new Student(
                        id,
                        name,
                        email,
                        phone,
                        department,
                        academicYear
                );

                String error = studentService.addStudent(student);

                if (error != null) {
                    showError(error);
                    return;
                }

                addView.getStage().close();

                refreshTable();

                showSuccess("Student added successfully");

            } catch (NumberFormatException ex) {

                showError("Academic year must be a number");
            }
        });

        addView.show();
    }

    private void setupEditButton() {

        view.getEditButton().setOnAction(
                event -> openEditStudentView()
        );
    }

    private void openEditStudentView() {

        Student selectedStudent =
                view.getStudentTable()
                        .getSelectionModel()
                        .getSelectedItem();

        if (selectedStudent == null) {

            showError("Please select a student first");

            return;
        }

        EditeStudentView editView =
                new EditeStudentView(selectedStudent);

        editView.getCancelButton().setOnAction(event -> {
            editView.getStage().close();
        });

        editView.getEditButton().setOnAction(event -> {

            try {

                String id = editView.getIDField().getText().trim();

                String name = editView.getNameField().getText().trim();

                String email = editView.getEmailField().getText().trim();

                String phone = editView.getPhoneField().getText().trim();

                String department =
                        editView.getDepartmentField().getText().trim();

                int academicYear = Integer.parseInt(
                        editView.getAcademicYearField().getText().trim()
                );

                Student updatedStudent = new Student(
                        id,
                        name,
                        email,
                        phone,
                        department,
                        academicYear
                );

                String error =
                        studentService.editStudent(updatedStudent);

                if (error != null) {

                    showError(error);

                    return;
                }

                editView.getStage().close();

                refreshTable();

                showSuccess("Student updated successfully");

            } catch (NumberFormatException ex) {

                showError("Academic year must be a number");
            }
        });

        editView.show();
    }

    private void setupDeleteButton() {

        view.getDeleteButton().setOnAction(event -> {

            Student selectedStudent =
                    view.getStudentTable()
                            .getSelectionModel()
                            .getSelectedItem();

            if (selectedStudent == null) {

                showError("Please select a student first");

                return;
            }

            String error = studentService.deleteStudent(
                    selectedStudent.getStudentId()
            );

            if (error != null) {

                showError(error);

                return;
            }

            refreshTable();

            showSuccess("Student deleted successfully");
        });
    }

    public StudentView getView() {

        return view;
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
        
}

