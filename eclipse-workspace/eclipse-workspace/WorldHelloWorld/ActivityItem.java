package studyplan.schedule;

import java.time.LocalTime;

import studyplan.*;
/*
 * represents the activity (instance) that scheduled 
 */
public class ActivityItem extends ScheduleItem {

	private ActivityInstance activityInstance;
	private LocalTime time;
	public  ActivityItem(ActivityInstance activityInstance) {
		super(activityInstance.getActivity().getCourse());
		this.activityInstance = activityInstance;
		this.time =activityInstance.getActivity().getStart().toLocalTime();
	}
	@Override
	public LocalTime getTime() {
		return this.time;
	}
	
	public ActivityInstance getActivityInstance() {
		return activityInstance;
	}

}
