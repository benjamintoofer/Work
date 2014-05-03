
public class CollisionPhysics {
	
	private static CollisionResponse tempResponse = new CollisionResponse();
	
	
	public static void pointIntersectsRectangleOuter( float pointX, float pointY, float speedX, float speedY, float radius,
	         float rectX1, float rectY1, float rectX2, float rectY2,
	         float timeLimit, CollisionResponse response) {
	      
	      response.reset();  // Reset detected collision time to infinity
	      
	      // A outer rectangular container box has 4 borders. 
	      // Need to look for the earliest collision, if any.
	  
	      // Right border
	      pointIntersectsLineVertical(pointX, pointY, speedX, speedY, radius,
	            rectX2, timeLimit, tempResponse);
	      if (tempResponse.t < response.t) {
	         response.copy(tempResponse);  // Copy into resultant response
	      }
	      // Left border
	      pointIntersectsLineVertical(pointX, pointY, speedX, speedY, radius,
	            rectX1, timeLimit, tempResponse);
	      if (tempResponse.t < response.t) {
	         response.copy(tempResponse);
	      }
	      // Top border
	      pointIntersectsLineHorizontal(pointX, pointY, speedX, speedY, radius,
	            rectY1, timeLimit, tempResponse);
	      if (tempResponse.t < response.t) {
	         response.copy(tempResponse);
	      }
	      // Bottom border
	      pointIntersectsLineHorizontal(pointX, pointY, speedX, speedY, radius,
	            rectY2, timeLimit, tempResponse);
	      if (tempResponse.t < response.t) {
	         response.copy(tempResponse);
	      }
	   }
	public static void pointIntersectsLineVertical(
			float pointX,float pointY, float speedX, float speedY,float radius,
			float lineX,float timeLimit,CollisionResponse response)
	{
		response.reset();
		
		if(speedX == 0)
		{
			return;
		}
		
		float distance;
		
		if(lineX > pointX)
		{
			distance = lineX - pointX - radius;
		}else{
			distance = lineX - pointX + radius;
		}
		float t = distance/speedX;
		
		if(t > 0 && t <= timeLimit)
		{
			response.t = t;
	        response.newSpeedX = -speedX;
	        response.newSpeedY = speedY;
		}
	}
	public static void pointIntersectsLineHorizontal(
			float pointX,float pointY,float speedX,float speedY,float radius,
			float lineY,float timeLimit,CollisionResponse response)
	{
		response.reset();
		
		if(speedY == 0)
		{
			return;
		}
		
		float distance;
		
		if(lineY > pointY)
		{
			distance = lineY - pointY - radius;
		}else{
			distance = lineY - pointY + radius; 
		}
		
		float t = distance / speedY;
		
		if(t > 0 && t <= timeLimit)
		{
			response.t = t;
			response.newSpeedX = speedX;
			response.newSpeedY = -speedY;
		}
	}	
}
