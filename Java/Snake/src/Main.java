import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.leapmotion.leap.*;

import java.io.IOException;

/**
 * Created by benjamintoofer on 11/15/14.
 */
public class Main extends Application{

    private final int WIDTH = 400;
    private final int HEIGHT = 300;
    private final RenderSystem ren = new RenderSystem(WIDTH,HEIGHT);
    private final GraphicsContext gc = ren.getGraphicsContext2D();


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Group root = new Group();
        root.getChildren().add(ren);
        //root.getChildren().add(new Button("Button"));



        primaryStage.setTitle("Snake");
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT, Color.WHITE));
        primaryStage.show();

        Thread gameThread = new Thread(new Runnable() {
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

                        Thread.sleep(60);
                    }
                }catch(InterruptedException ex){}
            }
        });
        gameThread.start();

        //control.removeListener(listener);
    }

    public static void main(String[] args)
    {


        // Keep this process running until Enter is pressed


        launch(args);


        System.exit(0);
    }

}
