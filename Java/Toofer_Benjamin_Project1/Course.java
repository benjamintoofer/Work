
public class Course {
	
	private String courseName;
	private String courseInstructor;
	private CourseID courseID = new CourseID();
	private int courseCred;
	private int courseBeginTime;
	private int courseEndTime;
	final private int NOON = 1200;
	
	//Constructors
	public Course(String name,String instruct,String subject,int subID,int cred,int beginTime,int endTime)
	{
		this.courseName = name;
		this.courseInstructor = instruct;
		this.courseID.subject = subject;
		this.courseID.num = subID;
		this.courseCred = cred;
		//this.setTime(beginTime, endTime);
		this.courseBeginTime = beginTime;
		this.courseEndTime = endTime;
	}
	public Course()
	{
		this("N/A","N/A","N/A",0,0,0,0);
	}
	
	//Setters
	public void setName(String name)
	{
		this.courseName = name.format("%-18s", name);
	}
	public void setInstructor(String instruct)
	{
		this.courseInstructor = instruct.format("%-18s", instruct);
	}
	public void setCourseID(String subject,int num)
	{
		this.courseID.subject = subject;
		this.courseID.num = num;
	}
	public void setCred(int cred)
	{
		this.courseCred = cred;
	}
	public boolean setTime(int begin, int end)
	{
		int beginHour = (begin);
		int beginMin = (begin - (begin/100)*100);
		int endHour = (end);
		int endMin = (end - (end/100)*100);
		
		//System.out.println("beginHour: "+beginHour+" beginMin:"+beginMin);
		//System.out.println("endHour: "+endHour+" endMin:"+endMin);
		if(beginHour <= 2400 && beginMin <= 59 && endHour <= 2400 && endMin <= 59 && begin < end)
		{
			this.courseBeginTime = begin;
			this.courseEndTime = end;
			return false;
		}else{
			return true;
		}
	}
	
	//Getters
	public String getName()
	{
		return this.courseName;
	}
	public String getInstruct()
	{
		return this.courseInstructor;
	}
	public CourseID getCourseID()
	{
		return this.courseID;
	}
	public int getCred()
	{
		return this.courseCred;
	}
	public int getMilitaryBeginTime()
	{
		return this.courseBeginTime;
	}
	public int getMilitaryEndTime()
	{
		return this.courseEndTime;
	}
	public String getStdBeginTime()
	{
		int hour = (this.courseBeginTime/100)%12;
		if(hour == 0)
		{
			hour = 12;
		}
		int min = (this.courseBeginTime - (this.courseBeginTime/100)*100);
		String str = String.format("%02d", min);
		
		if(this.courseBeginTime > NOON)
		{	
			return ""+hour+":"+str+" p.m.";
		}else{
			return ""+hour+":"+str+" a.m.";
		}
	}
	public String getStdEndTime()
	{
		int hour = (this.courseEndTime/100)%12;
		if(hour == 0)
		{
			hour = 12;
		}
		int min = (this.courseEndTime - (this.courseEndTime/100)*100);
		String str = String.format("%02d", min);
		
		if(this.courseEndTime > NOON)
		{	
			return ""+hour+":"+str+" p.m.";
		}else{
			return ""+hour+":"+str+" a.m.";
		}
	}
	//toString
	public String toString()
	{
		String tempString = courseID.subject;
		return  courseName+courseInstructor+tempString+" "+courseID.num+"\t  "+courseCred+"\t   "+this.getStdBeginTime()+"   "+this.getStdEndTime();
	}
	public static void sortClasses(Course courses[],int courseSize)
	{
		Course temp;
		
		for(int i = 0; i < courseSize; i++)
		{
			for(int j = i + 1; j < courseSize; j++ )
			{
				
				if(courses[i].getCourseID().subject.equalsIgnoreCase(courses[j].getCourseID().subject))
				{
					if(courses[i].getCourseID().num < courses[j].getCourseID().num)
					{
						
					}else
					{
						temp = courses[i];
						courses[i] = courses[j];
						courses[j] = temp;
					}
				}else if(courses[i].getCourseID().subject.compareToIgnoreCase(courses[j].getCourseID().subject)==-1)
				{
					
				}else{
					temp = courses[i];
					courses[i] = courses[j];
					courses[j] = temp;
				}
			}
		}
	}
	
}
class CourseID{
	String subject;
	int num;
	public CourseID()
	{
		subject = "";
		num = 0;
	}
	
}
