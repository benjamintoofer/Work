
import java.awt.*;
import java.util.Formatter;

public class Ball {

	float x,y;
	float speedX,speedY;
	float radius;
	private Color color;
	private static final Color DEFAULT_COLOR = Color.BLUE;
	
	public Ball(float x,float y, float radius,float speed,float angleInDegree,Color color)
	{
		this.x = x;
		this.y = y;
		
		this.speedX = (float)(speed * Math.cos(angleInDegree*(Math.PI/180)));
		this.speedY = (float)(-speed * Math.sin(angleInDegree*(Math.PI/180)));
		this.radius = radius;
		this.color = color;
	}
	public Ball(float x, float y, float radius, float speed, float angleInDegree)
	{
		this(x,y,radius,speed,angleInDegree,DEFAULT_COLOR);
	}
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval((int)(x - radius), (int)(y - radius), (int)(2*radius), (int)(2*radius));
	}
	public void moveOneStepCollisionDetection(ContainerBox box)
	{
		float ballMinX = box.minX + radius;
		float ballMaxX = box.maxX - radius;
		float ballMinY = box.minY + radius;
		float ballMaxY = box.maxY - radius;
		
		 x += speedX;
	     y += speedY;
	      
		if (x < ballMinX) {
	         speedX = -speedX; 
	         x = ballMinX;     
	      } else if (x > ballMaxX) {
	         speedX = -speedX;
	         x = ballMaxX;
	      }
	      // May cross both x and y bounds
	      if (y < ballMinY) {
	         speedY = -speedY;
	         y = ballMinY;
	      } else if (y > ballMaxY) {
	         speedY = -speedY;
	         y = ballMaxY;
	      }
	}
	public float getSpeed()
	{
		return (float)(Math.sqrt((speedX*speedX)+(speedY*speedY)));
	}
	public float getMoveAngle() 
	{
	    return (float)Math.toDegrees(Math.atan2(-speedY, speedX));
	}
	public float getMass()
	{
		return radius*radius*radius/1000f;
	}
	public float getKineticEnergy() 
	{
	    return 0.5f * getMass() * (speedX * speedX + speedY * speedY);
	}
	public String toString() 
	{
	      sb.delete(0, sb.length());
	      formatter.format("@(%3.0f,%3.0f) r=%3.0f V=(%2.0f,%2.0f) " +
	            "S=%4.1f \u0398=%4.0f KE=%3.0f", 
	            x, y, radius, speedX, speedY, getSpeed(), getMoveAngle(),
	            getKineticEnergy());  // \u0398 is theta
	      return sb.toString();
	 }
	   // Re-use to build the formatted string for toString()
	   private StringBuilder sb = new StringBuilder();
	   private Formatter formatter = new Formatter(sb);
	
}
