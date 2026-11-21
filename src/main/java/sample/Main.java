package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import sample.controller.CourseController;
import sample.controller.EnrollmentController;
import sample.controller.GradeController;
import sample.controller.MainController;
import sample.controller.StudentController;
import sample.repository.FileCourseRepository;
import sample.repository.FileEnrollmentRepository;
import sample.repository.FileGradeRepository;
import sample.repository.FileStudentRepository;
import sample.service.CourseService;
import sample.service.EnrollmentService;
import sample.service.GradeService;
import sample.service.StudentService;
import sample.validation.CourseValidator;
import sample.validation.EnrollmentValidator;
import sample.validation.GradeValidator;
import sample.validation.StudentValidator;
import sample.view.EnrollmentView;
import sample.view.MainView;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        StudentService studentService = new StudentService(
                new FileStudentRepository(),
                new StudentValidator());
        StudentController studentController =
                new StudentController(studentService);

        CourseService courseService = new CourseService(
                new FileCourseRepository(),
                new CourseValidator());
        CourseController courseController =
                new CourseController(courseService);

        EnrollmentService enrollmentService = new EnrollmentService(
                new FileEnrollmentRepository(),
                new EnrollmentValidator());
        EnrollmentController enrollmentController =
                new EnrollmentController(enrollmentService, new EnrollmentView());

        GradeService gradeService = new GradeService(
                new FileGradeRepository(),
                new GradeValidator());
        GradeController gradeController =
                new GradeController(gradeService);

        MainView view = new MainView();
        MainController controller = new MainController(view);

        controller.bind(view.getStudentsButton(),
                () -> studentController.getView().getRoot());
        controller.bind(view.getCoursesButton(),
                () -> courseController.getView().getRoot());
        controller.bind(view.getEnrollmentButton(),
                () -> enrollmentController.getView().getRoot());
        controller.bind(view.getGradesButton(),
                () -> gradeController.getView().getRoot());

        controller.showHome();

        stage.setScene(new Scene(view.getRoot(), 1100, 700));
        stage.setTitle("FCI Student System");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
