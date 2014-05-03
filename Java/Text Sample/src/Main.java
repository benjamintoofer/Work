import javax.swing.*;
import java.awt.Graphics;
import java.util.Scanner;


public class Main {
	public static void main(String[] args)
	{
		boolean[] lockers = new boolean [100];
		int numLockers = 100;
		int students = 100;
		
		for(int i = 1; i <= students; i++)
		{
			for(int j = i;j <= numLockers; j = j + i)
			{
				if(lockers[j-1] == false)
				{
					lockers[j-1] = true;
				}
				else
				{
					lockers[j - 1] = false;
				}
				 
			}
		}
		
		for(int i = 0; i < numLockers; i++)
		{
			if(lockers[i])
			{
				System.out.println("Locker "+(i+1)+" is open");
			}else
			{
				System.out.println("Locker "+(i+1)+" is closed");
			}
			
		}
		
		
	}
	
	
}
