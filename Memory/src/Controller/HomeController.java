package Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Main;

public class HomeController {

    private Stage stage;

    public HomeController(Stage stage) {
        this.stage = initHomeView(stage);
    }

    private Stage initHomeView(Stage stage) {
        GridPane root = new GridPane();
        Label label1 = new Label("Memory");
        root.add(label1, 0, 0);

        Button button1 = new Button("Nouvelle partie");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                launchNewGame();
            }
        });
        root.add(button1, 0, 1);

        Button button2 = new Button("Charger une partie");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                launchSavedGame();
            }
        });
        root.add(button2, 0, 2);

        Button button3 = new Button("Quitter le jeu");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        root.add(button3, 0, 3);

        Label label2 = new Label("Jeu réaliser par Aurélien Ravaud et Nicolas Renault");
        root.add(label2, 0, 4);


        stage.setScene(new Scene(root, Main.getWindowWidth(), Main.getWindowHeight()));
        return stage;
    }

    public void launch() {
        stage.show();
    }

    public void launchNewGame() {
        LauncherController launcherController = new LauncherController(stage);
        launcherController.launchNewGame();
    }

    public void launchSavedGame() {
        LauncherController launcherController = new LauncherController(stage);
        launcherController.launchSavedGame();
    }
}
