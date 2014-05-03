import java.awt.*;

public class ContainerBox {
	
	int minX,maxX,minY,maxY;
	private Color colorFilled;
	private Color colorBorder;
	private static final Color DEFAULT_COLOR_FILLER = Color.black;
	private static final Color DEFALT_COLOR_BORDER = Color.yellow;
	
	public ContainerBox(int x,int y, int width, int height, Color fill, Color border)
	{
		minX = x;
		minY = y;
		maxX = x + width - 1;
		maxY = y + height - 1;
		colorFilled = fill;
		colorBorder = border;
	}
	public ContainerBox(int x,int y, int width, int height)
	{
		this(x,y,width,height,DEFAULT_COLOR_FILLER,DEFALT_COLOR_BORDER);
	}
	public void set(int x,int y,int width,int height)
	{
		minX = x;
		minY = y;
		maxX = x + width - 1;
		maxY = y + height - 1;
	}
	public void draw(Graphics g)
	{
		g.setColor(colorFilled);
		g.fillRect(minX, minY, maxX-minX, maxY-minY);
		g.setColor(colorBorder);
		g.drawRect(minX, minY, maxX-minX, maxY-minY);
	}
}
