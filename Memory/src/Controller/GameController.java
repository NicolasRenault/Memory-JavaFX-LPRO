package Controller;

import Classes.Game;
import Classes.Player;
import View.GameView;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameController {

    private Game game;
    private GameView gameView;

    public GameController(Stage stage) {
        Player Pierre = new Player("Pierre");
        Player Paul = new Player("Paul");
        Player Jack = new Player("Jack");
        Player Nicolas = new Player("Nicolas");
        Player[] players = {Pierre,Paul,Jack,Nicolas};


        this.game = new Game(players,8,0);
        game.init();
        this.gameView = new GameView(stage, game);
    }

    public GameController(Stage stage, Game game) {
            this.gameView = new GameView(stage, game);
    }

    public GameController(Stage stage, Player[] players, int nbPairs, int nbPenalty) {
        this.game = new Game(players, nbPairs, nbPenalty);
        game.init();
        this.gameView = new GameView(stage, game);
    }

    public void launch() {
        gameView.show();
    }
}
