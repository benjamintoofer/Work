import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamintoofer on 11/16/14.
 */
public class Snake {

    private ArrayList<SnakeSegment> segmentList;
    public SnakeSegment headSnake;
    public SnakeSegment tailSnake;

    public Snake()
    {
        segmentList = new ArrayList<SnakeSegment>();

    }
    public void setSnake(int x, int y)
    {
        SnakeSegment newSeg = new SnakeSegment(x,y);
        headSnake = newSeg;
        tailSnake = headSnake;
        segmentList.add(newSeg);
    }
    public void addSegment()
    {
        SnakeSegment newSeg = new SnakeSegment(tailSnake.xPos,tailSnake.yPos);
        tailSnake = newSeg;
        segmentList.add(newSeg);
    }

    public void resetSnake()
    {
        segmentList.clear();
        headSnake = null;
        tailSnake = null;
    }
    public ArrayList<SnakeSegment> getSegmentList()
    {
        return segmentList;
    }
}
