package projectpolygon;

import javax.swing.*;

import java.awt.*;

public class RegularPolygonPanel extends JPanel{
	
	private int numOfSides;
	private static int RADIUS = 20;
	
	public RegularPolygonPanel(int numOfSides)
	{
		this.numOfSides = numOfSides;
		setPreferredSize(new Dimension(2*RADIUS, 2*RADIUS));
	}
	
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Polygon poly = new Polygon();
		int xPos = 0;
		int yPos = 0;
		int centerX = getWidth()/2;
		int centerY = getHeight()/2;
		double angle = 0;
		
		for(int i = 0; i < numOfSides; i++)
		{
			angle = (i *(2*Math.PI/numOfSides));
			xPos = centerX + (int)(Math.cos(angle)*RADIUS);
			yPos = centerY + (int)(Math.sin(angle)*RADIUS);
			System.out.println(getWidth()+" "+getHeight());
			poly.addPoint(xPos, yPos);
		}
		g.drawPolygon(poly);
	}
}
