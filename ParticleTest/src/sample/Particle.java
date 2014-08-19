package sample;

public class Particle {

    private double xPos,yPos,xVel,yVel;
    private int age;

    /*
    Getters
     */
    public double getxPos()
    {
        return xPos;
    }

    public double getyPos()
    {
        return yPos;
    }

    public double getxVel()
    {
        return xVel;
    }

    public double getyVel()
    {
        return  yVel;
    }

    public int getAge()
    {
        return age;
    }

    /*
    Setters
     */

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
