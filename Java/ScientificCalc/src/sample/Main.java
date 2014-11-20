package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private final int MAX_WIDTH = 700;
    private final int MAX_HEIGHT = 450;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Calculator myCalc = new Calculator(MAX_WIDTH,MAX_HEIGHT);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(myCalc, MAX_WIDTH, MAX_HEIGHT));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }
}
