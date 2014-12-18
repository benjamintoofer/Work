package toofer;

import javafx.scene.image.Image;
import toofer.gfx.SpriteSheet;

/**
 * Created by benjamintoofer on 12/16/14.
 */
public class Player {

    private int xPos,yPos,xVel,yVel;
    private  int width,height;
    public Image playerImage;

    public Player(int x, int y,SpriteSheet ss)
    {
        xPos = x;
        yPos = y;
        xVel = 0;
        yVel = 0;

        this.width = 7;
        this.height = 14;

        playerImage = ss.cropImage(0,30,GameConstants.SPRITE_WIDTH,GameConstants.SPRITE_WIDTH * 2);
    }

    public int getxPos()
    {
        return xPos;
    }

    public void setxPos(int xPos)
    {
        this.xPos = xPos;
    }

    public int getyPos()
    {
        return yPos;
    }

    public void setyPos(int yPos)
    {
        this.yPos = yPos;
    }

    public int getxVel()
    {
        return xVel;
    }

    public void setxVel(int xVel)
    {
        this.xVel = xVel;
    }

    public int getyVel()
    {
        return yVel;
    }

    public void setyVel(int yVel)
    {
        this.yVel = yVel;
    }

    public void tick()
    {
        xPos += xVel;
        yPos += yVel;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
}
