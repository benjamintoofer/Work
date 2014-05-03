import java.util.Scanner;


public class Driver {
	public static void main(String[] args) {
		
		int numCourses = 0;
		int numStudents = 0;
		Course[] courses = new Course[20];
		Student[] students = new Student[10];
		Scanner scanner = new Scanner( System.in );
		int menuChoice = 0;
		
		while(menuChoice != 6)
		{
			printMenu();
			menuChoice = scanner.nextInt();
			
			switch(menuChoice)
			{
				case 1:
					//Print Courses
					Course.sortClasses(courses,numCourses);
					printCourses(courses,numCourses);
					pausePrint();
					break;
				case 2:
					//Print Student
					printStudents(students,numStudents);
					pausePrint();
					break;
				case 3:
					//Add Student
					students[numStudents++] = addStudent(scanner,courses,numCourses);
					break;
				case 4:
					//Add Course
					courses[numCourses++] = addCourse(scanner);
					break;
				case 5:
					//Edit Student
					editStudent(scanner,courses,students,numStudents,numCourses);
					break;
				case 6:
					//Exit
					break;
			}
			
		}
		System.out.print("GoodBye!");
		
	

	}
	public static void printMenu()
	{
		System.out.println("\n-------------------------");
		System.out.println("**   1. Print Courses  **");
		System.out.println("**   2. Print Students **");
		System.out.println("**   3. Add Student    **");
		System.out.println("**   4. Add Course     **");
		System.out.println("**   5. Edit Student   **");
		System.out.println("**   6. Exit           **");
		System.out.println("-------------------------");
	}
	public static void printCourses(Course courses[],int numCourses)
	{
		if(numCourses != 0)
		{
			System.out.println("Course Name     Course Instructor Course ID   Course Credit  Start Time  End Time");
			System.out.println("---------------------------------------------------------------------------------");
			for(int i = 0; i < numCourses; i++)
			{
				System.out.println(courses[i].toString());
			}
		}else{
			System.out.println("No courses");
		}
		
	}
	public static void pausePrint()
	{
		
		System.out.print("\n\nPress enter to go back to the Main Menu");
		new Scanner(System.in).nextLine();
		
	}
	public static void addCourseToStudent(Student student,Course courses[],int numCourses)
	{
		boolean found = false;
		String name = "";
		String courseName;
		String str1;
		int added;
		int courseNum = 0;
		Scanner scan = new Scanner(System.in);
		
		if(numCourses != 0)
		{
		System.out.println("Enter Student's courses by course ID");
		System.out.println("You can assign no more than 5 classes");
		System.out.println("Type '0' if you are done entering classes");
		for(int i = 0; i < 5; i++)
		{
			System.out.print("course "+student.getCoursesTaken()+":");
			
			courseName = scan.nextLine();
			
			//Check of user entered 0
			if(!courseName.equals("0"))
			{
				String[] subject = courseName.split(" ");
				//Make sure String and number was entered
				if(subject.length == 2 && Character.isDigit(subject[1].charAt(0)))
				{
					name = subject[0];
					courseNum = Integer.parseInt(subject[1]);
				}
				
				
				//Check entered ID with other ID
				for(int j = 0; j < numCourses; j++)
				{
					
					str1 = courses[j].getCourseID().subject;
					
					if(name.equalsIgnoreCase(str1) && courseNum == courses[j].getCourseID().num)
					{
						added = student.addCourse(courses[j]);
						
						if(added == 1)
						{
							found = true;
						}else{
							found = false;
						}
						
						break;
					}
					if(j == numCourses-1)
					{
						
						found = false;
					}
				}
				
			}else{
				
				break;
			}
			if(found == false)
			{
				System.out.println("Enrty Ivalid. Please Try Again");
				i--;
			}
		}
	}else{
		System.out.println("No available classes at the moment");
	}
	}
	public static Student addStudent(Scanner scanner,Course courses[],int numCourses)
	{
		Student student = new Student();
		String str1;
		String str2;
		double num;
		
		//Get student's name
		System.out.println("What is the student's name?");
		str1 = scanner.next();
		str2 = scanner.next();
		student.setName(str1, str2);
		//get ID
		System.out.println("What is the student's ID?");
		num = scanner.nextDouble();
		student.setStudentID((int)num);
		//get GPA
		System.out.println("What is the student's GPA?");
		scanner.skip("\n");
		num = scanner.nextDouble();
		student.setStudentGPA(num);
		//Add classes
		addCourseToStudent(student,courses,numCourses);
		
		return student;
	}
	public static void printStudents(Student students[],int numStudents)
	{
		if(numStudents != 0)
		{
			for(int i = 0; i < numStudents; i++)
			{
				System.out.println(students[i].toString());
			}
		}else{
			System.out.println("No students entered");
		}
		
	}
	public static void editStudent(Scanner scanner,Course courses[], Student students[],int numStudents,int numCourses)
	{
		if(numStudents != 0)
		{
			String str1,str2;
			double num;
			
			System.out.print("Enter student's name: ");
			scanner.skip("\n");
			str1 = scanner.nextLine();
			
			for(int i = 0; i < numStudents; i++)
			{
				if(str1.equalsIgnoreCase(students[i].getFirstName()+" "+students[i].getLastName()))
				{
					System.out.println("Enter new name");
					str1 = scanner.next();
					str2 = scanner.next();
					students[i].setName(str1, str2);
					System.out.println("Enter new student ID");
					num = scanner.nextInt();
					students[i].setStudentID((int)num);
					System.out.println("Enter new student GPA");
					num = scanner.nextDouble();
					students[i].setStudentGPA(num);
					
					addCourseToStudent(students[i],courses,numCourses);
					
				}else{
					System.out.println("Student not found");
				}
			}
		}else{
			System.out.println("No students etnered");
		}
		
	}
	public static Course addCourse(Scanner scanner)
	{
		Course course = new Course();
		int num1 = 0;
		int num2 = 0;
		String str;
		boolean timeInput = true;
		
		
		System.out.println("What is the class name?");
		scanner.skip("\n");
		str = scanner.nextLine();
		course.setName(str);
		System.out.println("Who is the instructor?");
		str = scanner.nextLine();
		course.setInstructor(str);
		System.out.println("What is the course ID?\nPlace a space betwwen the subject and the subjet number(ex:Math 11)");
		str = scanner.next();
		num1 = scanner.nextInt();
		course.setCourseID(str,num1);
		System.out.println("How many credits?");
		num1 = scanner.nextInt();
		course.setCred(num1);
		
		while(timeInput)
		{
			Scanner scan = new Scanner(System.in);
			System.out.println("What is the start and end time in military time?(ex:1330 1430)");
			
			try{
				num1 = scan.nextInt();
				num2 = scan.nextInt();
			}catch(Exception e){
				System.out.println("Did not enter a number");
			}
			
			timeInput = course.setTime(num1, num2);
		}
		
		
		return course;
	}
}
