package ui.dashboard;
import javafx.scene.control.TreeItem;
import studyplan.Activity;
public class ActivityTreeItem extends TreeItem<String> {
	
	public ActivityTreeItem(Activity a)
	{
		super(a.getName());
		activity = a;
		
		
	}
	private Activity activity;

	public Activity getActivity() {
		return activity;
	}



	
}
