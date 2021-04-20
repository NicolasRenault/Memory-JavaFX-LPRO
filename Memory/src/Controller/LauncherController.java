package Controller;

import Classes.Game;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.*;

public class LauncherController {

    private Stage stage;

    public LauncherController(Stage stage) {
        this.stage = stage;
    }

    private void initLauncherForm() {
        System.out.println("yes");
    }

    public void launchNewGame() {
        GameController gameController = new GameController(stage);
        gameController.launch();
    }

    public void launchSavedGame() {
        Game game = null;
        try {
            FileInputStream fi = new FileInputStream(new File("save.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);

            game = (Game) oi.readObject();
            System.out.println(game.toString());

            fi.close();
            oi.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Il n'y a pas de partie sauvegarder\n" +
                    "Nous allons créer une nouvelle partie");

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("Détails de l'exception :");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();
            game = null;
        }

        if (game != null) {
            GameController gameController = new GameController(stage, game);
            gameController.launch();
        } else {
            initLauncherForm();
        }

    }
}
