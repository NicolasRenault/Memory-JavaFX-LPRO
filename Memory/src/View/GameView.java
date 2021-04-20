package View;

import Classes.Board;
import Classes.Card;
import Classes.Game;
import Classes.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Main;

public class GameView {

    private Stage stage;
    private Game game;

    public GameView(Stage stage, Game game) {
        this.stage = stage;
        this.game = game;
    }

    public void show() {
        Group root = new Group();

        GridPane boardGrid = getBoardGrid(game.getBoard());
        GridPane playerGrid = getPlayerGrid(game.getPlayers());
        GridPane buttonGrid = getButtonGroup();

        root.getChildren().addAll(boardGrid, playerGrid, buttonGrid);

        Scene scene = new Scene(root, Main.getWindowWidth(), Main.getWindowHeight());
        scene.getStylesheets().add("ressources/css/" + Main.getCssTheme() + ".css");
        stage.setScene(scene);

        stage.show();
    }

    private GridPane getBoardGrid(Board board) {
        GridPane gridPane = new GridPane();

        int tabSize = board.getTabSize();

        for(int i = 0; i < tabSize; i++) {
            for(int j = 0; j < tabSize; j++) {
                final int x = i;
                final int y = j;

                Card card = board.findCard(i, j);
                final boolean face = card.isFace();
                String message = face ? card.getType() : " ";

                Button button = new Button(message);
                button.setPrefSize(80, 80);
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent event) {
                        if (!face) {
                            play(x, y);
                        }
                    }
                });
                button.getStyleClass().add("card");
                gridPane.add(button, i, j);
            }
        }
        gridPane.setTranslateY(100);
        gridPane.setTranslateX(30);
        gridPane.getStyleClass().add("cardGrid");
        return gridPane;
    }

    private GridPane getPlayerGrid(Player[] players) {
        GridPane gridPane = new GridPane();

        for (int i = 0; i < players.length; i++) {
            String message = players[i].getName() + " Score : " + players[i].getScore();

            if (players[i].getPenalty() > 0) {
                message += " Malus : ";
                for(int j = 0; i < players[j].getScore(); j++) {
                    message += "X ";
                }
            }
            Label label = new Label(message);
            label.getStyleClass().add("player");
            if (game.getTurn()-1 == i) {
                label.getStyleClass().add("selected");
            }

            gridPane.add(label, 0, i);
        }
        gridPane.setTranslateY(100);
        gridPane.setTranslateX(500);
        gridPane.getStyleClass().add("playerGrid");

        return gridPane;
    }

    private GridPane getButtonGroup() {
        GridPane gridPane = new GridPane();

        Button button1 = new Button("Abandonner");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                game.surrender();
            }
        });
        gridPane.add(button1, 0, 0);

        Button button2 = new Button("Sauvegarder");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                game.save();
            }
        });
        gridPane.add(button2, 1, 0);

        Button button3 = new Button("Recommencer");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                game.restart();
                show();
            }
        });
        gridPane.add(button3, 2, 0);

        Button button4 = new Button("Changer l'apparence");
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                game.quit();
            }
        });
        gridPane.add(button4, 3, 0);

        gridPane.setHgap(10);
        gridPane.setTranslateY(400);
        gridPane.setTranslateX(500);
        gridPane.getStyleClass().add("buttonGrid");

        return gridPane;
    }

    private void play(int x, int y) {
        game.play(x, y);
        show();
    }
}
