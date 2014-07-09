package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Table mainTable = new Table();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(mainTable, TestConstants.SCENE_WIDTH, TestConstants.SCENE_HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}
