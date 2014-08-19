package sample;

/**
 * Created by benjamintoofer on 8/18/14.
 */
public class Gravitron {

    private double xPos,yPos,xPull,yPull;

    public Gravitron()
    {
        xPos = yPos = xPull = yPull = 0;
    }

    public void setyPos(double yPos)
    {
        this.yPos = yPos;
    }

    public void setxPos(double xPos)
    {
        this.xPos = xPos;
    }

    public void setxPull(double xPull)
    {
        this.xPull = xPull;
    }

    public void setyPull(double yPull)
    {
        this.yPull = yPull;
    }

    public double getxPos()
    {
        return xPos;
    }

    public double getxPull()
    {
        return xPull;
    }

    public double getyPos()
    {
        return yPos;
    }

    public double getyPull()
    {
        return yPull;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
