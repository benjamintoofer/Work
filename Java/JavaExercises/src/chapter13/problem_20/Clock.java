package chapter13.problem_20;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Clock extends JPanel{

	int radius;
	boolean showNumbers;
	boolean showSecondHand;
	Date today;
	
	public Clock(int radius)
	{
		this.radius = radius;
		this.showNumbers = false;
		this.showSecondHand = false;
		this.today = new Date();
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		
	}
	public void setSecondHand(boolean showHand)
	{
		this.showSecondHand = showHand;
	}
	public void setShowNumber(boolean show)
	{
		this.showNumbers = show;
	}
}
