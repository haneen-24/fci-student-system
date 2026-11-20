package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import sample.model.Enrollment;
import sample.service.EnrollmentService;
import sample.util.AlertHelper;
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

            String courseCode =
                    view.getCourseCodeField().getText().trim();

            if (studentText.isEmpty() || courseCode.isEmpty()) {

                AlertHelper.showError(
                        "Missing Data",
                        "Please enter Student ID and Course Code."
                );

                return;
            }

            int studentId = Integer.parseInt(studentText);

            service.enrollStudent(studentId, courseCode);

            loadEnrollments();

            view.getStudentIdField().clear();
            view.getCourseCodeField().clear();

            AlertHelper.showInfo(
                    "Success",
                    "Student enrolled successfully."
            );

        } catch (NumberFormatException e) {

            AlertHelper.showError(
                    "Invalid Data",
                    "Student ID must be a number."
            );

        } catch (IllegalArgumentException e) {

            AlertHelper.showError(
                    "Enrollment Error",
                    e.getMessage()
            );

        } catch (Exception e) {

            AlertHelper.showError(
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

    public EnrollmentView getView() {
        return view;
    }
}