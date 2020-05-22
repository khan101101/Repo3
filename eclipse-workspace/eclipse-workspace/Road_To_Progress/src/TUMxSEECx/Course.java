package TUMxSEECx;
import java.util.List;
import java.util.Date;

public class Course {
	  public String title;
	    public String lecturer;
	   List<Date> numbers = new List<> ();
	   List<Student> numbers = new List<> ();
	}

	//course constructor
	public Course(String title){
	    this.title = title;
	}

	void public printCourseTitle(){
	    System.out.println("Course title :" + title);
}
