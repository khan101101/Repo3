package studyplan.schedule;
import java.time.*;
import java.time.temporal.*;
import java.util.*;

import studyplan.*;

/*
 * @author Bilen Yavuz
 * represents a weekly schedule from semester
 */
public class WeekPlanner {

	Semester semester;
	
	LocalDate startOfWeek;
	List<DailyPlanner> days;
	
	
	/*
	 * @param semester contains courses (activity, task, study items)
	 * @param startOfWeek the beginning day of week 
	 */
	public WeekPlanner(Semester semester, LocalDate startOfWeek)
	{
		this.semester = semester;
		createDays(startOfWeek);
		
	}
	
	/*
	 * refresh the schedule
	 */
	public void refreshDays()
	{
		createDays(this.startOfWeek);
	}

	/*
	 * (re)create the daily planners with startOfWeek
	 */
	private void createDays(LocalDate startOfWeek) {
		this.startOfWeek = startOfWeek;
		days = new ArrayList<DailyPlanner>(7);
		for (int i = 0; i < 7; i++) {
			DailyPlanner dp = new DailyPlanner(semester, startOfWeek.plusDays(i));
			days.add(dp);
		}
	}
	
	
	public Iterable<DailyPlanner> getDays(){
		return days;
	}
	
	public DailyPlanner getDay(int index) {
		return days.get(index);
				
	}
	
	public Semester getSemester() {
		return semester;
	}
	
	/*
	 * recreates the schedule for next week
	 */
	public void nextWeek() {
		createDays(startOfWeek.plusWeeks(1));
	}
	/*
	 * recreates the schedule of previous week
	 */
	public void prevWeek() {
		createDays(startOfWeek.minusWeeks(1));
	}
}
