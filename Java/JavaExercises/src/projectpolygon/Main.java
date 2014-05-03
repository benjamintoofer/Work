package projectpolygon;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
	
	RegularPolygonPanel pentagon = new RegularPolygonPanel(5);
	RegularPolygonPanel hexagon = new RegularPolygonPanel(6);
	RegularPolygonPanel heptagon = new RegularPolygonPanel(7);
	RegularPolygonPanel octagon = new RegularPolygonPanel(8);
	RegularPolygonPanel nonagon = new RegularPolygonPanel(9);
	RegularPolygonPanel decagon = new RegularPolygonPanel(10);
	
	public Main()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		
		this.add(pentagon);
		this.add(hexagon);
		this.add(heptagon);
		this.add(octagon);
		this.add(nonagon);
		this.add(decagon);
	}
	public static void main(String[] args)
	{
		Main frame = new Main();
		frame.pack();
		frame.setTitle("Regular Polygon Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
