package sample;

/**
 * Created by benjamintoofer on 8/18/14.
 */
public class Gravitron {

    private double xPos,yPos,xPull,yPull;
    private boolean gravityOn;
    private final double PULL_MAGNITUDE = 2;

    public Gravitron()
    {
        xPos = yPos = xPull = yPull = 0;
        gravityOn = false;
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

    public void setGravityOn(boolean gravityOn)
    {
        this.gravityOn = gravityOn;
        if(gravityOn)
        {
            xPull = PULL_MAGNITUDE;
            yPull = PULL_MAGNITUDE;
        }else{
            xPull = 0;
            yPull = 0;
        }
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

    public boolean isGravityOn()
    {
        return gravityOn;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
