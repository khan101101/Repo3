package studyplan.schedule;
import java.time.LocalTime;

import studyplan.*;

/*
 * @author Bilen Yavuz
 * represents scheduled task
 */
public class TaskItem extends ScheduleItem {

	private Task task;
	
	public TaskItem(Task t) {
		super(t.getCourse());
		task = t;
	}
	
	public Task getTask()
	{
		return task;
	}

	/*
	 * Task is scheduled at Midnight by default
	 * (non-Javadoc)
	 * @see studyplan.schedule.ScheduleItem#getTime()
	 */
	@Override
	public LocalTime getTime() {
		return LocalTime.MIDNIGHT; 
	}
}
