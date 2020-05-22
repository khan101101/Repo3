package ui.dashboard;

import javafx.scene.control.TreeItem;
import studyplan.Activity;
import studyplan.Course;
import studyplan.Task;

public  class CourseTreeItem extends TreeItem<String>{
	private Course course;
	public CourseTreeItem(Course c)
	{
		super(c.getName());
		course = c;
		
		for (Activity a : course.getActivities()) {
			super.getChildren().add(new ActivityTreeItem(a));
		}

		for (Task t : course.getTasks()) {
			super.getChildren().add(new TaskTreeItem(t));
		}
		
		
	}
	
	public Course getCourse()
	{
		return course;
	}
	

}


