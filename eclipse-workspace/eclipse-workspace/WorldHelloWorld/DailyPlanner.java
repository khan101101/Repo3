package studyplan.schedule;

import java.time.LocalDate;
import java.util.*;

import studyplan.*;

/*
 * @author Bilen Yavuz
 * represent daily schedule for semester
 */
public class DailyPlanner {
	/*
	 * semester that schedule is created from
	 */
	private Semester semester;
	/*
	 * date of schedule
	 */
	private LocalDate date;
	/*
	 * items of schedule (Activity, Task, Study)
	 */
	private List<ScheduleItem> items = new ArrayList<ScheduleItem>();

	
	public DailyPlanner(Semester semester, LocalDate date) {
		this.semester = semester;
		this.date = date;
		for (Course course : semester.getCourses()) {
			for (Task task : course.getTasks()) {
				handleTask(task);
			}
			for (Activity activity : course.getActivities()) {
				
				ActivityInstance  instance = activity.getInstance(date);
				if(instance != null) {
					items.add(new ActivityItem(instance));
				}
			}
			
			for (SelfStudy selfStudy : course.getSelfStudies()) {
				
				if(selfStudy.getStart().toLocalDate().isEqual(date)) {
					items.add(new SelfStudyItem(selfStudy));
				}
			}
		}
		
		items.sort(new Comparator<ScheduleItem>() {
			 @Override
			    public int compare(ScheduleItem o1, ScheduleItem o2) {
			        return o1.getTime().compareTo(o2.getTime());
			    }
		});
	}
	
	private void handleTask(Task task) {
		if(task.getDueDate().isEqual(date)){
			items.add(new TaskItem(task));
		}
		
		for (Task t : task.getSubTasks()) {
			handleTask(t);
		}
	}

	public LocalDate getDate() {
		return date;
	}
	
	public Iterable<ScheduleItem> getItems(){
		return items;
	}
}
