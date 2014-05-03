
public class Student {

	final private int MAX_COURSES = 5;
	private String firstName;
	private String lastName;
	private int studentID;
	private double studentGPA;
	private int coursesTaken = 0;
	private Course[] studentSchedule = new Course[MAX_COURSES];
	
	//Constructor
	public Student(String fName,String lName,int ID,double GPA)
	{
		this.firstName = fName;
		this.lastName = lName;
		this.studentID = ID;
		this.studentGPA = GPA;
	}
	public Student()
	{
		this("N/A","N/A",0,0);
	}
	

	//Setters
	public void setName(String fName,String lName)
	{
		this.firstName = fName;
		this.lastName = lName;
	}
	public void setStudentID(int ID)
	{
		this.studentID = ID;
	}
	public void setStudentGPA(double GPA)
	{
		this.studentGPA = GPA;
	}
	public int addCourse(Course course)
	{
		boolean added = false;
		
		if(coursesTaken == 0)
		{
			this.studentSchedule[coursesTaken++] = course;
			added = true;
		}
		else if(coursesTaken < MAX_COURSES )
		{
			for(int i = 0; i < coursesTaken; i++)
			{
				//Add class if its begin time is greater than last class end time
				if(course.getMilitaryBeginTime() > this.studentSchedule[coursesTaken-1].getMilitaryEndTime())
				{
					this.studentSchedule[coursesTaken++] = course;
					added = true;
					break;
				
				}else if(course.getMilitaryEndTime() < this.studentSchedule[i].getMilitaryBeginTime()) 
				{
					//Add class if its end time is before the begin time of the first class
					if(i == 0)
					{
						this.studentSchedule[coursesTaken++] = course;
						added = true;
						break;
					}else if(course.getMilitaryBeginTime() > this.studentSchedule[i-1].getMilitaryEndTime())
					{
						this.studentSchedule[coursesTaken++] = course;
						added = true;
						break;
					}
				}
			}
		//Sort Function
			sortClasses();
		}else{
			System.out.print(firstName+" "+lastName+"'s schedule is full");
		}
		if(added)
		{
			return 1;
		}else{
			return 0;
		}
			
	}
	
	//Getters
	public String getFirstName()
	{
		return this.firstName;
	}
	public String getLastName()
	{
		return this.lastName;
	}
	public int getStudentID()
	{
		return this.studentID;
	}
	public double getStudentGPA()
	{
		return this.studentGPA;
	}
	public int getCoursesTaken()
	{
		return coursesTaken;
	}
	//toString
	public String toString()
	{
		String printClasses = "";
		for(int i = 0; i < coursesTaken; i++)
		{
			printClasses = printClasses + studentSchedule[i]+"\n";
		}
		return "Student Name: "+firstName+" "+lastName+
			   " Student ID: "+studentID+" Student GPA: "+studentGPA+
			   " \nClasses:\n"+printClasses;
	}
	private void sortClasses()
	{
		Course temp;
		
		for(int i = 0; i < coursesTaken; i++)
		{
			for(int j = i+1; j < coursesTaken; j++)
			{
				if(this.studentSchedule[i].getMilitaryEndTime() < this.studentSchedule[j].getMilitaryEndTime())
				{
					//do nothing
				}else{
					temp = studentSchedule[i];
					studentSchedule[i] = studentSchedule[j];
					studentSchedule[j] = temp;
				}
			}
		}
	}
}
