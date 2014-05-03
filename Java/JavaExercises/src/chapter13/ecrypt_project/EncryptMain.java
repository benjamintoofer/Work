package chapter13.ecrypt_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EncryptMain {

	public static void main(String[] args) {
		
		File input;
		File output;
		PrintWriter outputFile = null;
		String inputName,outputName;
		String tempString = " ";
		Scanner scan = new Scanner(System.in);
		Scanner scanFile = null;
		
		System.out.print("Enter input file name: ");
		inputName = scan.next();
		System.out.print("Enter output file name: " );
		outputName = scan.next();
		
		input = new File("/Users/benjamintoofer/Documents/Java/JavaExercises/src/chapter13/ecrypt_project/"+inputName);
		output = new File("/Users/benjamintoofer/Documents/Java/JavaExercises/src/chapter13/ecrypt_project/"+outputName);
		
		
		try {
			scanFile = new Scanner(input);
			outputFile = new PrintWriter(output);
		} catch (FileNotFoundException e) {
				
			e.printStackTrace();
		}
		
		String temp = " ";
		while(scanFile.hasNext())
		{
			
			temp = scanFile.next();
			for(int i = 0; i < temp.length();i++)
			{
				tempString += changeLetter(temp.charAt(i));
			}
			tempString += " ";
		}
		
		System.out.println(tempString);
		outputFile.print(tempString);
		outputFile.close();
	}
	public static char changeLetter(char input)
	{
		char returnChar = ' ';
		
		switch(input)
		{
			case 'a':
				returnChar = 'k';
				break;
			case 'b':
				returnChar = 'n';
				break;
			case 'c':
				returnChar = 'g';
				break;
			case 'd':
				returnChar = 'c';
				break;
			case 'e':
				returnChar = 'a';
				break;
			case 'f':
				returnChar = 'd';
				break;
			case 'g':
				returnChar = 's';
				break;
			case 'h':
				returnChar = 'x';
				break;
			case 'i':
				returnChar = 'b';
				break;
			case 'j':
				returnChar = 'v';
				break;
			case 'k':
				returnChar = 'f';
				break;
			case 'l':
				returnChar = 'h';
				break;
			case 'm':
				returnChar = 'j';
				break;
			case 'n':
				returnChar = 't';
				break;
			case 'o':
				returnChar = 'i';
				break;
			case 'p':
				returnChar = 'u';
				break;
			case 'q':
				returnChar = 'm';
				break;
			case 'r':
				returnChar = 'y';
				break;
			case 's':
				returnChar = 'l';
				break;
			case 't':
				returnChar = 'z';
				break;
			case 'u':
				returnChar = 'q';
				break;
			case 'v':
				returnChar = 'r';
				break;
			case 'w':
				returnChar = 'o';
				break;
			case 'x':
				returnChar = 'p';
				break;
			case 'y':
				returnChar = 'w';
				break;
			case 'z':
				returnChar = 'e';
				break;
				
				
		}
		return returnChar;
	}

}
