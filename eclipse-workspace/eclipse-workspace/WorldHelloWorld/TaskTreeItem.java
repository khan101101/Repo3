package ui.dashboard;
import javafx.scene.control.TreeItem;
import studyplan.*;

public class TaskTreeItem  extends TreeItem<String>{
	
	public TaskTreeItem(Task t)
	{
		super(t.getName());
		
		task = t;
		for (Task st : t.getSubTasks()) {
			super.getChildren().add(new TaskTreeItem(st));
		}
		
	}
	Task task;

	public Task getTask() {
		return task;
	}


	
}
