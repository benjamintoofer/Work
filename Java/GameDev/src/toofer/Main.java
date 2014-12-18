package toofer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WIDTH = GameConstants.GAME_WIDTH;
    private static final int HEIGHT = GameConstants.GAME_HEIGHT;
    private static final int SCALE = GameConstants.FRAME_SCALE;

    private Game myGame = new Game(WIDTH,HEIGHT,SCALE);

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Group group = new Group();
        group.getChildren().add(myGame);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(group, WIDTH * SCALE, HEIGHT * SCALE));
        primaryStage.show();

        myGame.start();
    }


    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }
}
