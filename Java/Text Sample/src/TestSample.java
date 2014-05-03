import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class TestSample extends JPanel{
	
	private static final int UPDATE_RATE = 30;
	private float fps = 0;
	private float bs = 0;
	
	public static final int CANVAS_HEIGHT = 640;
	public static final int CANVAS_WIDTH = 640;
	
	private DrawCanvas canvas;
	private JTextField upText;
	private JTextField rightText;
	private JTextField leftText;
	
	public TestSample()
	{
		canvas = new DrawCanvas();
		upText = new JTextField("j",10);
		rightText = new JTextField();
		leftText = new JTextField();
		upText.setEditable(false);
		upText.setLocation(5,105);
		
		canvas.add(upText);
		this.setPreferredSize(new Dimension(CANVAS_WIDTH,CANVAS_HEIGHT));
		this.setLayout(new BorderLayout());
		this.add(canvas,BorderLayout.CENTER);
	}
}
class DrawCanvas extends JPanel implements KeyListener{
	
	public DrawCanvas()
	{
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.CYAN);
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			System.out.println("UP");
		}
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	
	public Dimension getPreferredSize()
	{
		return (new Dimension(440,340));
	}
}