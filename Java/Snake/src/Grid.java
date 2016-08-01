/**
 * Created by benjamintoofer on 11/17/14.
 */
public class Grid {

    private int appWidth,appHeight;
    private int cols,rows;
    public final int PIXEL_WIDTH = 20;
    public final int PIXEL_HEIGHT = 20;

    public Grid(int Width,int Height)
    {
        this.appWidth = Width;
        this.appHeight = Height;

        cols = appWidth/PIXEL_WIDTH;
        rows = appHeight/PIXEL_HEIGHT;
    }

    public int getCols()
    {
        return cols;
    }

    public int getRows()
    {
        return rows;
    }
}
