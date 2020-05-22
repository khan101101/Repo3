package ui.dashboard;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import studyplan.*;
import studyplan.schedule.ActivityItem;
import studyplan.schedule.ScheduleItem;
import studyplan.schedule.SelfStudyItem;
import studyplan.schedule.TaskItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
/*
 * @author Bilen Yavuz
 * Controller class for dashboard view
 */
public class DashboardController implements Initializable {

	@FXML
	private MenuBar menuBar;

	@FXML
	private TreeView<String> courseTree;
	
	@FXML
	private VBox todayBox;

	private ContextMenu courseTreeContextMenu;
	
	private Semester semester;
	private studyplan.schedule.DailyPlanner todayPlanner;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		courseTree.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue,
					TreeItem<String> newValue) {

			}
		});
		
		// initialize context menu
		courseTreeContextMenu = new ContextMenu();

		MenuItem newCourseMenuItem = new MenuItem("New Course");
		newCourseMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				newCourse(event);
			}
		});
		courseTreeContextMenu.getItems().add(newCourseMenuItem);

		MenuItem newActivityMenuItem = new MenuItem("New Activity");
		newActivityMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				newActivity(event);
			}
		});

		courseTreeContextMenu.getItems().add(newActivityMenuItem);

		MenuItem newTaskMenuItem = new MenuItem("New Task");
		newTaskMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				newTask(event);
			}
		});
		courseTreeContextMenu.getItems().add(newTaskMenuItem);

		MenuItem editTreeItemMenuItem = new MenuItem("Edit");
		editTreeItemMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				editCourseTreeItem(event);
			}
		});
		courseTreeContextMenu.getItems().add(editTreeItemMenuItem);

		MenuItem deleteTreeItemMenuItem = new MenuItem("Delete");
		deleteTreeItemMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				deleteCourseTreeItem(event);
			}
		});
		courseTreeContextMenu.getItems().add(deleteTreeItemMenuItem);

		courseTree.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
			@Override
			public void handle(ContextMenuEvent event) {
				TreeItem<String> selectedItem = getSelectedCourseTreeItem();

				
				if (CourseTreeItem.class.isInstance(selectedItem)) {
					newCourseMenuItem.setDisable(true);
					newActivityMenuItem.setDisable(false);
					newTaskMenuItem.setDisable(false);
					newTaskMenuItem.setText("New Task");
					editTreeItemMenuItem.setDisable(false);
					deleteTreeItemMenuItem.setDisable(false);
				} else if (TaskTreeItem.class.isInstance(selectedItem)) {
					newCourseMenuItem.setDisable(true);
					newActivityMenuItem.setDisable(true);
					newTaskMenuItem.setDisable(false);
					newTaskMenuItem.setText("Sub Task");
					editTreeItemMenuItem.setDisable(false);
					deleteTreeItemMenuItem.setDisable(false);
				}  else if (ActivityTreeItem.class.isInstance(selectedItem)) {
					newCourseMenuItem.setDisable(true);
					newActivityMenuItem.setDisable(true);
					newTaskMenuItem.setDisable(true);
					editTreeItemMenuItem.setDisable(false);
					deleteTreeItemMenuItem.setDisable(false);
				} else {
					newCourseMenuItem.setDisable(false);
					newActivityMenuItem.setDisable(true);
					newTaskMenuItem.setDisable(true);
					editTreeItemMenuItem.setDisable(true);
					deleteTreeItemMenuItem.setDisable(true);
				}

				

				courseTreeContextMenu.show(courseTree, event.getScreenX(), event.getScreenY());

			}
		});
	}

	private void bindMenu() {

		menuBar.getMenus().clear();

		Main app = studyplan.Main.getCurrentApplication();

		Menu fileMenu = new Menu("File");
		MenuItem editProfileMenuItem = new MenuItem("Edit Profile");
		editProfileMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				app.showEditProfileView();
			}
		});
		fileMenu.getItems().add(editProfileMenuItem);

		MenuItem saveMenuItem = new MenuItem("Save");
		saveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				app.save();
			}
		});
		fileMenu.getItems().add(saveMenuItem);

		menuBar.getMenus().add(fileMenu);

		Menu semestersMenu = new Menu("Semesters");

		MenuItem newSemesterMenuItem = new MenuItem("New");
		newSemesterMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Semester s = ui.semester.SemesterViewController.showWindow(null);
				if (s != null) {
					Main.getCurrentApplication().getProfile().getSemesters().add(s);
					bindSemester(s);
				}
			}
		});
		semestersMenu.getItems().add(newSemesterMenuItem);

		MenuItem editSemesterMenuItem = new MenuItem("Edit");
		editSemesterMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ui.semester.SemesterViewController.showWindow(semester);
				bindSemester(semester);
			}
		});
		semestersMenu.getItems().add(editSemesterMenuItem);

		for (Semester s : app.getProfile().getSemesters()) {

			MenuItem semesterMenuItem = new MenuItem(s.getName());
			semesterMenuItem.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					bindSemester(s);
				}
			});
			semestersMenu.getItems().add(semesterMenuItem);
		}

		menuBar.getMenus().add(semestersMenu);

		Menu planMenu = new Menu("Plan");

		MenuItem weekPlanMenuItem = new MenuItem("Week");
		weekPlanMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				app.showWeek(semester);

			}
		});
		planMenu.getItems().add(weekPlanMenuItem);

		menuBar.getMenus().add(planMenu);

	}

	

	public void bindSemester(Semester semester) {
		this.semester = semester;
		Stage stage = (Stage) menuBar.getScene().getWindow();
		stage.setTitle(semester.getName());

		bindMenu();
		bindCourseTree();
		
		bindToday();

	}
	
	String dayBoxCssLayout = "-fx-background-color: white;\n" + "-fx-border-color: gray;\n" + "-fx-padding: 10;\n"
			+ "-fx-spacing: 10;\n" + "-fx-border-insets: 10;\n" + "-fx-border-width: 1;\n"
			+ "-fx-border-style: dashed;\n";
	
	String dayBoxHighlightCssLayout = "-fx-background-color: white;\n" + "-fx-border-color: gray;\n" + "-fx-padding: 10;\n"
			+ "-fx-spacing: 10;\n" + "-fx-border-insets: 10;\n" + "-fx-border-width: 1;\n"
			+ "-fx-border-style: solid;\n";

	String itemBoxCss = "-fx-background-color: white;\n" + "-fx-border-color: gray;\n" + "-fx-padding: 3;\n"
			+ "-fx-spacing: 3;\n" + "-fx-border-insets: 3;\n" + "-fx-border-width: 1;\n" + "-fx-border-style: solid;\n";
	
	String courseLabelCss = "-fx-background-color: green;\n" + "-fx-border-color: gray;\n" + "-fx-padding: 3;\n"
			+ "-fx-spacing: 3;\n" + "-fx-border-insets: 3;\n" + "-fx-border-width: 1;\n" + "-fx-border-style: solid;\n";
	
	void bindToday()
	{
		this.todayPlanner = new studyplan.schedule.DailyPlanner(this.semester, LocalDate.now());
	
		todayBox.getChildren().clear();

		// day name & date label
		todayBox.getChildren().add(new Label( "TODAY : " + todayPlanner.getDate().getDayOfWeek().toString()));
		todayBox.getChildren().add(new Label(todayPlanner.getDate().toString()));


		// process scheduled items within day
		for (ScheduleItem item : todayPlanner.getItems()) {
			VBox itemBox = new VBox();
			itemBox.setStyle(itemBoxCss);
			todayBox.getChildren().add(itemBox);
			
			HBox titleBox = new HBox();
			itemBox.getChildren().add(titleBox);
			
			if (TaskItem.class.isInstance(item)) { // bind the task to user interface
			
				Task task = ((TaskItem) item).getTask();

				titleBox.getChildren().add(new Label(task.getName()));// name of task
				titleBox.getChildren().add(new Label("    "));
				titleBox.getChildren().add(new Label(task.getIsCompleted() ? "Completed" : "Incomplete"));// status of task
				titleBox.getChildren().add(new Label("    "));
				titleBox.getChildren().add(new Label(task.getCourse().getName())); // course name
			
				itemBox.getChildren().add(new Label(task.getDetails()));
				
				
			} else if(ActivityItem.class.isInstance(item)) { // bind activity instance to user interface
				ActivityInstance instance = ((ActivityItem)item).getActivityInstance();
				Activity activity = instance.getActivity();
				titleBox.getChildren().add(new Label(activity.getStart().format(DateTimeFormatter.ofPattern("HH:mm")))); // time of activity
				titleBox.getChildren().add(new Label("    "));
				titleBox.getChildren().add(new Label(activity.getName())); // name of activity
				titleBox.getChildren().add(new Label("    "));
				titleBox.getChildren().add(new Label(activity.getLocation())); // location of activity
				titleBox.getChildren().add(new Label("    "));
				titleBox.getChildren().add(new Label(activity.getCourse().getName())); // name of course
				titleBox.getChildren().add(new Label("    "));
				
				itemBox.getChildren().add(new Label(instance.getGoal()));
			
			} else if(SelfStudyItem.class.isInstance(item)) { // bind self study to user interface
				
				SelfStudy selfStudy = ((SelfStudyItem)item).getSelfStudy();
				
				titleBox.getChildren().add(new Label(selfStudy.getStart().format(DateTimeFormatter.ofPattern("HH:mm"))));// start time
				titleBox.getChildren().add(new Label("    "));
				titleBox.getChildren().add(new Label("Self Study"));
				titleBox.getChildren().add(new Label("    "));
				titleBox.getChildren().add(new Label(selfStudy.getLocation()));// location
				titleBox.getChildren().add(new Label("    "));
				titleBox.getChildren().add(new Label(selfStudy.getCourse().getName())); // name of course
				
				itemBox.getChildren().add(new Label(selfStudy.getGoal()));
				
			
			}
		
			
		
			
		}
	}

	void bindCourseTree() {
		TreeItem<String> rootItem = new TreeItem<String>("Courses");

		for (Course c : semester.getCourses()) {
			rootItem.getChildren().add(new CourseTreeItem(c));
		}
		rootItem.setExpanded(true);
		courseTree.setRoot(rootItem);
	}

	/*
	 * create a new course
	 */
	public void newCourse(ActionEvent event) {
		try {
			Course newCourse = new Course();
			newCourse.setSemester(semester);
			newCourse.setName("New Course");
			newCourse = ui.course.CourseViewController.showWindow(newCourse);
			if (newCourse != null) {
				semester.addCourse(newCourse);
				courseTree.getRoot().getChildren().add(new CourseTreeItem(newCourse));
			}
			
			
		} catch (Exception e) {
		}

	}

	/*
	 * creates a new activity for selected course
	 */
	public void newActivity(ActionEvent event) {

		TreeItem<String> selectedItem = getSelectedCourseTreeItem();

		if (CourseTreeItem.class.isInstance(selectedItem)) {
			Course course = ((CourseTreeItem) selectedItem).getCourse();

			try {

				Activity newActivity = new Activity();
				newActivity.setCourse(course);
				newActivity.setName("New Activity");
				newActivity.setStart(LocalDateTime.now());
				newActivity.setDuration(Duration.ZERO);
				newActivity.setRepeat(0);
				newActivity.setRepeatTimes(0);
				newActivity = ui.activity.ActivityViewController.showWindow(newActivity);
				if (newActivity != null) {
					course.addActivity(newActivity);
					selectedItem.getChildren().add(new ActivityTreeItem(newActivity));
					selectedItem.setExpanded(true);
				
				}

			} catch (Exception e) {
			}

		}
		
		bindToday();
	}

	/*
	 * creates a task for selected course or a subtask for selected task
	 */
	public void newTask(ActionEvent event) {

		TreeItem<String> selectedItem = getSelectedCourseTreeItem();

		Task newTask = new Task();
		newTask.setName("New Task");
		newTask.setDueDate(LocalDate.now());
		newTask.setIsCompleted(false);
		
		if (TaskTreeItem.class.isInstance(selectedItem)) {
			Task parentTask = ((TaskTreeItem) selectedItem).getTask();
			newTask.setParentTask(parentTask);
			
		} else if (CourseTreeItem.class.isInstance(selectedItem)) {
			Course course = ((CourseTreeItem) selectedItem).getCourse();
			newTask.setCourse(course);
		}

		try {

			
			newTask = ui.task.TaskViewController.showWindow(newTask);

		} catch (Exception e) {
			e.printStackTrace();
			newTask = null;
		}

		if (newTask != null) {
			if (TaskTreeItem.class.isInstance(selectedItem)) {
				Task parentTask = ((TaskTreeItem) selectedItem).getTask();
				parentTask.addTask(newTask);
				selectedItem.getChildren().add(new TaskTreeItem(newTask));
				selectedItem.setExpanded(true);
			} else if (CourseTreeItem.class.isInstance(selectedItem)) {
				Course course = ((CourseTreeItem) selectedItem).getCourse();
				course.addTask(newTask);
				selectedItem.getChildren().add(new TaskTreeItem(newTask));
				selectedItem.setExpanded(true);
			}
		}
		
		bindToday();

	}

	/*
	 * event handler for edit menu
	 */
	public void editCourseTreeItem(ActionEvent event) {

		TreeItem<String> selectedItem = getSelectedCourseTreeItem();

		if (CourseTreeItem.class.isInstance(selectedItem)) { // edit course
			Course course = ((CourseTreeItem) selectedItem).getCourse();
			try {
				ui.course.CourseViewController.showWindow(course);
				selectedItem.setValue(course.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (ActivityTreeItem.class.isInstance(selectedItem)) { // edit activity
			Activity activity = ((ActivityTreeItem) selectedItem).getActivity();
			try {

				ui.activity.ActivityViewController.showWindow(activity);
				selectedItem.setValue(activity.getName());

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (TaskTreeItem.class.isInstance(selectedItem)) { // edit task
			Task task = ((TaskTreeItem) selectedItem).getTask();

			try {

				ui.task.TaskViewController.showWindow(task);

				selectedItem.setValue(task.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		bindToday();

	}

	public void deleteCourseTreeItem(ActionEvent event) {

		TreeItem<String> selectedItem = getSelectedCourseTreeItem();

		if (CourseTreeItem.class.isInstance(selectedItem)) {
			Course course = ((CourseTreeItem) selectedItem).getCourse();
			semester.removeCourse(course);
		} else if (ActivityTreeItem.class.isInstance(selectedItem)) {
			Activity activity = ((ActivityTreeItem) selectedItem).getActivity();
			Course course = ((CourseTreeItem) selectedItem.getParent()).getCourse();
			course.removeActivity(activity);
		} else if (TaskTreeItem.class.isInstance(selectedItem)) {
			Task task = ((TaskTreeItem) selectedItem).getTask();
			if (CourseTreeItem.class.isInstance(selectedItem.getParent())) {
				Course course = ((CourseTreeItem) selectedItem.getParent()).getCourse();
				course.removeTask(task);
			} else if (TaskTreeItem.class.isInstance(selectedItem.getParent())) {
				Task parentTask = ((TaskTreeItem) selectedItem.getParent()).getTask();
				parentTask.removeSubTask(task);
			}
		}

		if (selectedItem.getParent() != null) {
			selectedItem.getParent().getChildren().remove(selectedItem);
		}
		bindToday();
	}

	TreeItem<String> getSelectedCourseTreeItem() {
		if (!courseTree.getSelectionModel().isEmpty()) {
			return courseTree.getSelectionModel().getSelectedItem();

		} else
			return null;
	}

	public static DashboardController createView(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(DashboardController.class.getResource("DashboardView.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		DashboardController controller = loader.getController();
		return controller;
	}
}
