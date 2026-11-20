
package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import sample.model.Enrollment;
import sample.service.EnrollmentService;
import sample.view.EnrollmentView;

public class EnrollmentController {

    private final EnrollmentView view;
    private final EnrollmentService service;

    private final ObservableList<Enrollment> enrollments;

    public EnrollmentController(
            EnrollmentService service,
            EnrollmentView view) {

        this.service = service;
        this.view = view;

        enrollments = FXCollections.observableArrayList();

        view.getEnrollmentTable().setItems(enrollments);

        loadEnrollments();

        setupEnrollButton();
    }

    private void setupEnrollButton() {

        view.getEnrollButton().setOnAction(event -> {
            enrollStudent();
        });
    }

    private void enrollStudent() {

        try {

            String studentText =
                    view.getStudentIdField().getText().trim();

            String courseText =
                    view.getCourseIdField().getText().trim();

            if (studentText.isEmpty() || courseText.isEmpty()) {

                showAlert(
                        "Missing Data",
                        "Please enter Student ID and Course ID."
                );

                return;
            }

            int studentId = Integer.parseInt(studentText);

            String courseCode = courseText;

            service.enrollStudent(studentId, courseCode);

            loadEnrollments();

            view.getStudentIdField().clear();
            view.getCourseIdField().clear();

            showAlert(
                    "Success",
                    "Student enrolled successfully."
            );

        } catch (NumberFormatException e) {

            showAlert(
                    "Invalid Data",
                    "Student ID and Course ID must be numbers."
            );

        } catch (IllegalArgumentException e) {

            showAlert(
                    "Enrollment Error",
                    e.getMessage()
            );

        } catch (Exception e) {

            showAlert(
                    "Error",
                    "Something went wrong: " + e.getMessage()
            );
        }
    }

    private void loadEnrollments() {

        enrollments.setAll(
                service.getAllEnrollments()
        );

        view.getEnrollmentTable().refresh();
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public EnrollmentView getView() {
        return view;
    }
}

