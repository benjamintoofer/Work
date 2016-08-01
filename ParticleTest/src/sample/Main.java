package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {


    private final int WIDTH = 1200;
    private final int HEIGHT = 900;
    private final RenderSystem ren = new RenderSystem(WIDTH,HEIGHT);
    private final GraphicsContext gc = ren.getGraphicsContext2D();

    @Override
    public void start(Stage primaryStage) throws Exception{


        Group root = new Group();
        root.getChildren().add(ren);


        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT, Color.BLACK));
        primaryStage.show();

        Thread thread = new Thread(new Runnable() {

                @Override
                public void run()
                {
                    try{
                        while(true)
                        {
                            ren.tick();
                            //ren.render(gc);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run()
                                {
                                    ren.render(gc);
                                }
                            });

                            Thread.sleep(30);
                        }
                    }catch(InterruptedException ex){}
                }



        });
        thread.start();
    }


    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }
}
