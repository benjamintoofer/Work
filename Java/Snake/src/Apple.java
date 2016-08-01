import javafx.geometry.Point2D;

/**
 * Created by benjamintoofer on 11/16/14.
 */
public class Apple {

    public int xPos,yPos;

    public Apple(Point2D point)
    {
        setApplePos(point);
    }

    public void setApplePos(Point2D point)
    {
        this.xPos = (int)point.getX();
        this.yPos = (int)point.getY();
    }
}
