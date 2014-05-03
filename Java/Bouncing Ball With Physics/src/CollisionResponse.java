
public class CollisionResponse {
	
	public float t;
	
	private static final float T_EPSILON = .005f;
	
	public float newSpeedX;
	public float newSpeedY;
	
	public void CollisionResponse()
	{
		reset();
	}
	public void copy(CollisionResponse another)
	{
		this.t =another.t;
		this.newSpeedX = another.newSpeedX;
		this.newSpeedY = another.newSpeedY;
	}
	public void reset()
	{
		this.t = Float.MAX_VALUE;
		System.out.print(this.t);
	}
	public float getNewX(float currentX,float speedX)
	{
		if(t > T_EPSILON)
		{
			return (float)(currentX + speedX * (t - T_EPSILON));
		}else{
			return currentX;
		}
	}
	public float getNewY(float currentY,float speedY)
	{
		if(t > T_EPSILON)
		{
			return (float)(currentY + speedY*(t - T_EPSILON));
		}else{
			return currentY;
		}
	}
}
