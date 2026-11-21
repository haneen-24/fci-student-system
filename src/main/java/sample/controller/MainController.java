package sample.controller;

import java.util.function.Supplier;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import sample.view.MainView;

public class MainController {

    private final MainView view;

    public MainController(MainView view) {
        this.view = view;
        view.getHomeButton().setOnAction(e -> showHome());
    }

    // "When this button is clicked, show that page in the middle"
    public void bind(Button button, Supplier<Node> page) {
        button.setOnAction(e -> view.setContent(page.get()));
    }

    public void showHome() {
        Label welcome = new Label("Welcome to the Student Management System");
        view.setContent(new VBox(welcome));
    }
}
