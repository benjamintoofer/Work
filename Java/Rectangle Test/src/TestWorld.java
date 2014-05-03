import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Formatter;

import javax.swing.*;

public class TestWorld extends JPanel{
	
	private static final int UPDATE_RATE = 45;
	private float fps = 0;
	
	private Rectangle r1 = new Rectangle(250,250,200,150);
	private Rectangle r2 = new Rectangle(450,50,150,100);
	private GameCanvas canvas = new GameCanvas();
	public TestWorld(){
		
		this.setPreferredSize(new Dimension(640,640));
		this.add(canvas);
		
		Thread gameThread = new Thread()
		{
			public void run(){
				long lastTimeChecked = System.nanoTime(); 
				float frames = 0;
				while(true){
					//r1.update();
					
					repaint();
					try{
						Thread.sleep(1000/UPDATE_RATE);
					}catch(InterruptedException e){}
					frames++;
					if(System.nanoTime() - lastTimeChecked >= 1000000000L){
					 fps = frames;
					 frames = 0;
					 lastTimeChecked = System.nanoTime();
					}
				}
			}
		};
		gameThread.start();
	}
class GameCanvas extends JPanel implements KeyListener
{
	public GameCanvas()
	{
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setBackground(Color.GREEN);
		r1.draw(g);
		r2.draw(g);
		//FPS Counter
		g.setColor(Color.RED);
		g.setFont(new Font("Dialog",Font.BOLD,18));
		g.drawString("FPS: "+fps, 5, 20);
	}
	public Dimension getPreferredSize()
	{
		return (new Dimension(640, 640));
	}
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			System.out.println("Shut up");
		}
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e) {}
}
	/*public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		
		r1.draw(g);
		r2.draw(g);
		//FPS Counter
		g.setColor(Color.RED);
		g.setFont(new Font("Dialog",Font.BOLD,18));
		g.drawString("FPS: "+fps, 5, 20);
		
	}*/
}
