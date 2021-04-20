package sample;


import Classes.Board;
import Controller.GameController;
import Controller.HomeController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static int windowWidth = 900;
    private static int windowHeight = 600;
    private static String cssTheme = "default";

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Memory");

        HomeController homeController = new HomeController(primaryStage);
        homeController.launch();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static int getWindowWidth() {
        return windowWidth;
    }

    public static int getWindowHeight() {
        return windowHeight;
    }

    public static String getCssTheme() {
        return cssTheme;
    }

    public static void setCssTheme(String cssTheme) {
        Main.cssTheme = cssTheme;
    }
}
