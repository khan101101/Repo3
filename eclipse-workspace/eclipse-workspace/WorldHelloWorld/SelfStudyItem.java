package studyplan.schedule;
import java.time.LocalTime;
import studyplan.*;

/*
 * @author Bilen Yavuz
 * represents the scheduled SelfStudy
 */
public class SelfStudyItem extends ScheduleItem {

	private SelfStudy selfStudy;
	private LocalTime time;
	public SelfStudyItem(SelfStudy selfStudy) {
		super(selfStudy.getCourse());
		this.selfStudy = selfStudy;
		time = selfStudy.getStart().toLocalTime(); 
	}
	
	@Override
	public LocalTime getTime() {
		return this.time;
	}
	
	
	public SelfStudy getSelfStudy() {
		return selfStudy;
	}
}
