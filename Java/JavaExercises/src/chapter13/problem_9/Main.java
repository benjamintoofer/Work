package chapter13.problem_9;

/*
 *  (Create four fans) 
 *  Write a program that places four fans 
 *  in a frame of GridLayout with two rows and two columns
 */

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
	
	public Main()
	{
		this.setLayout(new GridLayout(2,2));
		Fan fan1 = new Fan(50.0,50.0,40.0,8);
		Fan fan2 = new Fan(50.0,50.0,40.0,9);
		Fan fan3 = new Fan(50.0,50.0,40.0,3);
		Fan fan4 = new Fan(50.0,50.0,40.0,4);
		add(fan1);
		add(fan2);
		add(fan3);
		add(fan4);
	}
	

	public static void main(String[] args) {
		
		Main frame = new Main();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		frame.setVisible(true);

	}

}
