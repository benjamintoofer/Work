import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class BallWorld extends JPanel
{
	private static final int UPDATE_RATE = 30;
	
	private Ball ball;
	private ContainerBox box;
	
	private DrawCanvas canvas;
	private int canvasHeight;
	private int canvasWidth;
	
	public BallWorld(int width,int height)
	{
		canvasWidth = width;
		canvasHeight = height;
		
		Random rand = new Random();
		int radius = 200;
		int x = rand.nextInt(canvasWidth);
		int y = rand.nextInt(canvasHeight);
		int speed = 5;
		int angleInDegree = rand.nextInt(360);
		ball = new Ball(x,y,radius,speed,angleInDegree,Color.BLUE);
		
		box = new ContainerBox(0,0,canvasWidth,canvasHeight,Color.BLACK,Color.YELLOW);
		
		canvas = new DrawCanvas();
	    this.setLayout(new BorderLayout());
	    this.add(canvas, BorderLayout.CENTER);
	    
		this.addComponentListener(new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e)
			{
				Component c = (Component)e.getSource();
				Dimension dim = c.getSize();
				canvasWidth = dim.width;
				canvasHeight = dim.height;
				//Adjust the bounds of the container to fill the window
				box.set(0, 0, canvasWidth, canvasHeight);
			}
		});
		gameStart();
	}
	public void gameStart()
	{
		Thread gameThread = new Thread()
		{
			public void run()
			{
				while(true)
				{
					gameUpdate();
					repaint();
					try{
						Thread.sleep(1000/UPDATE_RATE);
					}catch(InterruptedException e){}
				}
			}
		};
		gameThread.start();
	}
	public void gameUpdate()
	{
		ball.moveOneStepCollisionDetection(box);
	}
	class DrawCanvas extends JPanel
	{
		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			box.draw(g);
			ball.draw(g);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier New",Font.PLAIN,12));
			g.drawString("Ball "+ball.toString(),20, 30);
		}
		public Dimension getPreferredSize()
		{
			return (new Dimension(canvasWidth, canvasHeight));
		}
	}
	
}
