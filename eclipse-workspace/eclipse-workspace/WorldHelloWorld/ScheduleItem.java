package studyplan.schedule;
import java.time.LocalTime;

import studyplan.*;
/*
 * @author Bilen Yavuz
 * abstract base class for items within schedule
 */
public abstract class ScheduleItem {
	/*
	 * the course of item
	 */
	Course course;
	public ScheduleItem(Course c) {
		course = c;
	}
	/*
	 * start time of item
	 */
	public abstract LocalTime getTime();

	public Course getCourse() {
		return course;
	}
}
