import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javax.swing.event.MouseInputListener;
import java.util.EventListener;

/**
 * Created by benjamintoofer on 11/17/14.
 */
public class Button {

    private int x,y;
    private String name;
    private int width,height;

    public Button(int x, int y,int width,int height,String name)
    {
        this.x = x;
        this.y = y;
        this.name = name;
        this.width = width;
        this.height = height;
    }
    public void drawButton(GraphicsContext gc)
    {
        gc.setFill(Color.BLACK);
        gc.fillText(name,x ,y);
        gc.setFill(Color.AQUA);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.strokeRoundRect(x,y,width,height,5,10);
        gc.fillRoundRect(x,y,width,height,5,10);
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}
