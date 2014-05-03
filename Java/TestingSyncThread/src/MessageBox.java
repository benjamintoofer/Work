
public class MessageBox 
{
	private String message;
	private boolean hasMessage;
	
	public synchronized void putMessage(String message)
	{
		while(hasMessage)
		{
			try{
				wait();
			}catch(InterruptedException e){}
		}
		hasMessage = true;
		this.message = message + " Put @ "+System.nanoTime();
		notify();
	}
	public synchronized String getMessage() {
	      while (!hasMessage) {
	         // no new message
	         try {
	            wait();  // release the lock of this object
	         } catch (InterruptedException e) { }
	      }
	      // acquire the lock and continue
	      hasMessage = false;
	      notify();
	      return message + " Get @ " + System.nanoTime();
	   }
}
