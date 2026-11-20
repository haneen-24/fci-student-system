package sample.controller;                                         

import javafx.collections.FXCollections;                          
import javafx.collections.ObservableList;                          

import sample.model.Grade;                                         
import sample.service.GradeService;
import sample.util.AlertHelper;
import sample.view.GradeView;
import sample.view.GradeView.GradeForm;

public class GradeController {

    private final GradeView view;
    private final GradeService service;

    public GradeController(GradeService service) {
        this.service = service;
        this.view = new GradeView();

        refreshTable();
        setupSearch();
        setupAddButton();
        setupEditButton();
    }

    private void refreshTable() {
        ObservableList<Grade> grades = 
                FXCollections.observableArrayList(service.getAll());
        view.getGradeTable().setItems(grades);
    }

    private void setupSearch() {
        view.getSearchField().textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    ObservableList<Grade> result =
                            FXCollections.observableArrayList(
                                    service.search(newValue));
                    view.getGradeTable().setItems(result);
                });
    }

    private void setupAddButton() {
        view.getAddButton().setOnAction(event -> addGrade());
    }

    private void addGrade() {
        GradeForm form = new GradeForm("Add Grade", null);

        form.getSaveButton().setOnAction(event -> {
            Grade grade = readGrade(form);
            if (grade == null) {
                return;
            }

            String error = service.add(grade);
            if (error != null) {
                AlertHelper.showError("Invalid Data", error);
                return;
            }

            form.getStage().close();
            refreshTable();
            AlertHelper.showInfo("Success",
                    "Grade added successfully.");
        });

        form.getCancelButton().setOnAction(
                event -> form.getStage().close());

        form.show();
    }

    private void setupEditButton() {
        view.getEditButton().setOnAction(event -> editGrade());
    }

    private void editGrade() {
        Grade selected = view.getGradeTable()
                .getSelectionModel().getSelectedItem();

        if (selected == null) {
            AlertHelper.showError("No Grade Selected",
                    "Please select a grade first.");
            return;
        }

        GradeForm form = new GradeForm("Edit Grade", selected);

        form.getSaveButton().setOnAction(event -> {
            Grade updated = readGrade(form);
            if (updated == null) { 
                return; 
            }

            String error = service.update(selected, updated);
            if (error != null) {
                AlertHelper.showError("Invalid Data", error);
                return;
            }

            form.getStage().close();
            refreshTable(); 
            AlertHelper.showInfo("Success",
                    "Grade updated successfully.");
        });

        form.getCancelButton().setOnAction(
                event -> form.getStage().close());

        form.show();
    }

    private Grade readGrade(GradeForm form) {
        try {
            int studentId = Integer.parseInt(
                    form.getStudentIdField().getText().trim());
            String courseCode = 
                    form.getCourseCodeField().getText().trim();
            double score = Double.parseDouble(
                    form.getScoreField().getText().trim());
            return new Grade(studentId, courseCode, score);
        } catch (NumberFormatException e) {
            AlertHelper.showError("Invalid Data",
                    "Student ID must be a whole number and score must be a number.");
            return null; 
        }
    }

    public GradeView getView() { 
        return view;
    }
}

