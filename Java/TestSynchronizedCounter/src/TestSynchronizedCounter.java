
public class TestSynchronizedCounter 
{

	public static void main(String[] args) 
	{
		Thread threadIncrement = new Thread()
		{
			@Override
			public void run()
			{
				for (int i = 0; i < 10; ++i) {
		               System.out.print(i+"increment ");SynchronizedCounter.increment();
		               try {
		                  sleep(1);
		               } catch (InterruptedException e) {}
				}
			}
		};
		Thread threadDecrement = new Thread()
		{
			public void run()
			{
				for(int i = 0; i < 10; i++){
					System.out.print(i+"decrement ");SynchronizedCounter.decrement();
					try{
						sleep(1);
					}catch (InterruptedException e){};
				}
			}
		};
		threadIncrement.start();
	    threadDecrement.start();

	}

}
