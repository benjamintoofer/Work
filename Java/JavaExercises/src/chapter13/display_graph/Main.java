package chapter13.display_graph;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main extends JFrame{
	
	File myFile = new File("/Users/benjamintoofer/Documents/Java/JavaExercises/src/chapter13/display_graph/GraphText.txt");
	Scanner scan = null;
	int numNodes  = 0;
	int index = 0;
	GraphView view;
	
	
	public Main()
	{
		//setLayout(new FlowLayout());
		
		//Throw exception if no file found
		try {
				scan = new Scanner(myFile);
			} catch (FileNotFoundException e) {
					
				e.printStackTrace();
			}
				
		//Get number of nodes
		numNodes = scan.nextInt();
		view = new GraphView(numNodes);
		this.add(view);
		
		String temp = scan.nextLine();
		while(scan.hasNextLine())
		{
			Scanner newScan;
			
			temp = scan.nextLine();
			newScan = new Scanner(temp);
			
			int index = newScan.nextInt();
			view.position[index][0] = newScan.nextInt();
			view.position[index][1] = newScan.nextInt();
			view.edge[index] = new ArrayList<Integer>();
			while(newScan.hasNext())
			{
				view.edge[index].add(newScan.nextInt());
			}
			//System.out.println(temp);
			System.out.println("x: "+view.position[index][0]+" y: "+view.position[index][1]);
			view.repaint();
		}
	}

	public static void main(String[] args) {
		
		Main frame = new Main();
		frame.setSize(400,400);
		frame.setTitle("Graph Exercise");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
class GraphView extends JPanel
{
	int[][] position;
	ArrayList[] edge;
	
	public GraphView(int numNodes)
	{
		position = new int[numNodes][2];
		edge = new ArrayList[numNodes];
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		
		for(int i = 0; i < position.length;i++)
		{
			g.drawString(i+"", position[i][0]-10, position[i][1]-10);
			g.fillOval(position[i][0]-5, position[i][1]-5, 10, 10);
			//System.out.println(position[i][0])
			for(int j = 0; j < edge[i].size();j++)
			{
				int node = (Integer) edge[i].get(j);
				System.out.println(i+") "+position[i][0]+" " +position[i][1]+" " + position[node][0]+" " + position[node][1]);
				//g.setColor(Color.BLACK);
				g.drawLine(position[i][0], position[i][1], position[node][0], position[node][1]);
			}
			
		}
	}
}