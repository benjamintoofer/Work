import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Created by benjamintoofer on 11/17/14.
 */
public class MainMenu {

    public Button easyButton,hardButton;
    private RenderSystem ren;


    public MainMenu(RenderSystem ren)
    {
        this.ren = ren;
        easyButton = new Button((int)(ren.getWidth()/3 - 50),(int)(ren.getHeight()/2),100,60,"Easy");
        hardButton = new Button((int)(ren.getWidth()*2/3 - 50),(int)(ren.getHeight()/2),100,60,"Hard");

    }

    public void drawMenu(GraphicsContext gc)
    {
        gc.setFill(Color.BLACK);

        gc.fillText("Menu", ren.getWidth() / 2, ren.getHeight() / 4);
        gc.setFill(Color.AQUA);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        //EasyMode Button
        easyButton.drawButton(gc);

        //HardMode Button
        hardButton.drawButton(gc);



    }

}
