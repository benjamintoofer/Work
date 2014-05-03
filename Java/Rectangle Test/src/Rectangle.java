import javax.swing.*;
import java.awt.*;


public class Rectangle{
	
	float x,y;
	float width,height;
	float rotation;
	
	public  Rectangle(float x,float y,float width,float height)
	{
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	public void update()
	{
		this.x += 3;
		this.y += 4;
	}
	public void draw(Graphics g)
	{
		g.drawRect((int)(x),(int)(y), (int)width, (int)height);
	}
}
