package chapter13.problem_9;

import javax.swing.*;
import java.awt.*;

public class Fan extends JPanel{
	
	private double xPos,yPos;
	private double radius;
	private int numPaddle;
	private final int arcLength = 30;
	private int spaceLength;
	
	public Fan(double xPos,double yPos,double radius,int numPaddle)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.radius = radius;
		this.numPaddle = numPaddle;
		this.spaceLength = (360 - (30*numPaddle))/numPaddle;
		
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//draw Oval
		g.drawOval((int)this.getCenterX(),(int)this.getCenterY(), (int)(2 * radius), (int)(2 * radius));
		
		//draw arcs
		for(int i = 0; i < numPaddle; i++)
		{
			int start = (i * spaceLength) + (i * arcLength);
			g.fillArc((int)(this.getCenterX() + (xPos*.05)), (int)(this.getCenterY() + (yPos*.05)), (int)(2*radius * .95), (int)(2*radius * .95),start, arcLength);
		}
	
	}
	public double getCenterX()
	{
		return(xPos - (xPos*.5));
	}
	public double getCenterY()
	{
		return(yPos - (yPos*.5));
	}
	public int getArcLength()
	{
		return this.arcLength;
	}
}
