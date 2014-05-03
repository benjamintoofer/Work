import java.awt.*;
import java.util.Formatter;
import javax.swing.*;

public class BouncingBallSimple extends JPanel{
	
	//containers width and height
	private static final int BOX_WIDTH = 640;
	private static final int BOX_HEIGHT = 480;
	
	//ball's properties
	private float ballRadius = 100;
	private float ballX = 100;
	private float ballY = 100;
	private float speedX = 13;
	private float speedY = 12;
	
	private static final int UPDATE_RATE = 30;
	
	//Constructor
	public BouncingBallSimple()
	{
		this.setPreferredSize(new Dimension(BOX_WIDTH,BOX_HEIGHT));
		
		Thread gameThread = new Thread()
		{
			public void run()
			{
				while(true)
				{
					ballX += speedX;
					ballY += speedY;
					System.out.println("ball's x + radius:"+(ballX + ballRadius)+"box's width:"+BOX_WIDTH);
					
					if(ballX + ballRadius > BOX_WIDTH)
					{
						speedX *= -1;
						ballX = BOX_WIDTH - ballRadius;
					}else if(ballX - ballRadius < 0)
					{
						speedX *= -1;
						ballX = ballRadius;
					}
					if(ballY + ballRadius > BOX_HEIGHT)
					{
						speedY *= -1;
						ballY = BOX_HEIGHT - ballRadius;
					}else if(ballY - ballRadius < 0)
					{
						speedY *= -1;
						ballY = ballRadius;
					}
					repaint();
					try{
						Thread.sleep(1000/UPDATE_RATE);
					}catch(InterruptedException e){}
				}
			}

			private void print(String string) {
				// TODO Auto-generated method stub
				
			}
		};
		gameThread.start();
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
		//ball
		g.setColor(Color.BLUE);
		g.fillOval((int)(ballX - ballRadius), (int)(ballY - ballRadius), (int)(2*ballRadius), (int)(2*ballRadius));
		//ball info
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New",Font.PLAIN,12));
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb);
		formatter.format("Ball @(%3.1f,%3.1f) Speed=(%2.1f,%2.1f)", ballX, ballY,
	            speedX, speedY);
		g.drawString(sb.toString(),20,20);
	}
	public static void main(String[] args)
	{
		
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run()
			{
				JFrame frame = new JFrame("A Bouncing Ball");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new BouncingBallSimple());
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}
