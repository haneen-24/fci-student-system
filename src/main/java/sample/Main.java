package sample;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import sample.controller.MainController;
import sample.controller.StudentController;
import sample.repository.FileStudentRepository;
import sample.service.StudentService;
import sample.validation.StudentValidator;
import sample.view.MainView;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // 1) Build the Students module: repository -> service -> controller
        StudentService studentService = new StudentService(
               new FileStudentRepository(),  // CHECK THIS LINE (see below)
                new StudentValidator());
        StudentController studentController = new StudentController(studentService);

        // 2) Build the main window
        MainView view = new MainView();
        MainController controller = new MainController(view);

        // 3) Connect each sidebar button to a screen
        controller.bind(view.getStudentsButton(), () -> studentController.getView().getRoot());
        controller.bind(view.getCoursesButton(), () -> placeholder("Courses"));
        controller.bind(view.getEnrollmentButton(), () -> placeholder("Enrollment"));
        controller.bind(view.getGradesButton(), () -> placeholder("Grades"));

        controller.showHome();

        stage.setScene(new Scene(view.getRoot(), 1100, 700));
        stage.setTitle("FCI Student System");
        stage.show();
    }

    // Temporary screen for modules that aren't connected yet
    private Node placeholder(String name) {
        return new VBox(new Label(name + " module coming soon"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}