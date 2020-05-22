package ui.week;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import studyplan.*;
import studyplan.schedule.*;

/*
 * @author Bilen Yavuz
 * controller class for Weekly Planning view
 */
public class WeekViewController implements Initializable {

	@FXML
	private GridPane mainGrid;
	@FXML
	private Button prevButton;
	@FXML
	private Button nextButton;
	@FXML
	private HBox courseListHBox;

	String dayBoxCssLayout = "-fx-background-color: white;\n" + "-fx-border-color: gray;\n" + "-fx-padding: 10;\n"
			+ "-fx-spacing: 10;\n" + "-fx-border-insets: 10;\n" + "-fx-border-width: 1;\n"
			+ "-fx-border-style: dashed;\n";
	
	String dayBoxHighlightCssLayout = "-fx-background-color: white;\n" + "-fx-border-color: gray;\n" + "-fx-padding: 10;\n"
			+ "-fx-spacing: 10;\n" + "-fx-border-insets: 10;\n" + "-fx-border-width: 1;\n"
			+ "-fx-border-style: solid;\n";

	String itemBoxCss = "-fx-background-color: white;\n" + "-fx-border-color: gray;\n" + "-fx-padding: 3;\n"
			+ "-fx-spacing: 3;\n" + "-fx-border-insets: 3;\n" + "-fx-border-width: 1;\n" + "-fx-border-style: solid;\n";
	
	String courseLabelCss = "-fx-background-color: white;\n" + "-fx-border-color: gray;\n" + "-fx-padding: 3;\n"
			+ "-fx-spacing: 3;\n" + "-fx-border-insets: 3;\n" + "-fx-border-width: 1;\n" + "-fx-border-style: solid;\n";
	
	// vertical box containers for week days
	ArrayList<VBox> dayBoxes = new ArrayList<VBox>();
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// initialize a vertcial box for a day 
		for (int i = 0; i < 7; i++) {
			
			VBox dayBox = new VBox();
			dayBox.setStyle(dayBoxCssLayout);
			dayBox.setFillWidth(true);
			
			GridPane.setRowIndex(dayBox, 1);
			GridPane.setColumnIndex(dayBox, i);
			
			setAsDragAndDropTarget(dayBox,i);

			mainGrid.getChildren().add(dayBox);
			dayBoxes.add(dayBox);
			
		}
	}

	WeekPlanner planner;

	public void backToDashboard(ActionEvent event) {
		Main.getCurrentApplication().showDashboard(planner.getSemester());
	}

	public void previousWeek(ActionEvent event) {
		planner.prevWeek();
		bind(planner);
	}

	public void nextWeek(ActionEvent event) {
		planner.nextWeek();
		bind(planner);
	}
	

	Course draggedCourse;
	
	void setAsDragAndDropTarget(VBox target, int i)
	{
		 target.setOnDragOver(new EventHandler <DragEvent>() {
	            public void handle(DragEvent event) {
	                /* data is dragged over the target */
	                /* accept it only if it is  not dragged from the same node 
	                 * and if it has a string data */
	                if (event.getGestureSource() != target &&
	                        event.getDragboard().hasString()) {
	                    /* allow for both copying and moving, whatever user chooses */
	                    event.acceptTransferModes(TransferMode.COPY);
	                }
	                
	                event.consume();
	            }
	        });
		 
		 target.setOnDragEntered(new EventHandler <DragEvent>() {
	            public void handle(DragEvent event) {
	                /* the drag-and-drop gesture entered the target */
	             
	                /* show to the user that it is an actual gesture target */
	                if (event.getGestureSource() != target &&
	                        event.getDragboard().hasString()) {
	                	target.setStyle(dayBoxHighlightCssLayout);
	                }
	                
	                event.consume();
	            }
	        });
		 
		 target.setOnDragExited(new EventHandler <DragEvent>() {
	            public void handle(DragEvent event) {
	                /* mouse moved away, remove the graphical cues */
	            	target.setStyle(dayBoxCssLayout);
	                
	                event.consume();
	            }
	        });
		 
		   target.setOnDragDropped(new EventHandler <DragEvent>() {
	            public void handle(DragEvent event) {
	                /* data dropped */
	       
	            	Dragboard db = event.getDragboard();
	                boolean success = false;
	                if (draggedCourse != null && db.hasString()) {
	                	/* add self study to course */
	                	DailyPlanner dayPlanner = planner.getDay(i);
	                    try {
	                    	
	                    	SelfStudy selfStudy = new SelfStudy();
	                    	selfStudy.setStart(dayPlanner.getDate().atStartOfDay());
	                    	selfStudy.setDuration(Duration.ZERO);
	                    	selfStudy.setCourse(draggedCourse);
	                    	
	                    	selfStudy = ui.selfstudy.SelfStudyViewController.showWindow(selfStudy);
	                    	
	                    	if(selfStudy.getLocation() != null)
	                    		draggedCourse.addSelfStudy(selfStudy);
	                    	
	                    	planner.refreshDays();
	                    	bind(planner);
	                    	
	                    	//System.out.println("Schedule a self study on " + dayPlanner.getDate().toString() + " for course " + draggedCourse.getName());
	                    }
	                    catch(Exception ex)
	                    {
	                    	
	                    }
	                    success = true;
	                }
	                /* let the source know whether the string was successfully 
	                 * transferred and used */
	                event.setDropCompleted(success);
	                
	                event.consume();
	            }
	        });
	}
	
	void setAsDragAndDropSource(Label source, Course course) {
		 source.setOnDragDetected(new EventHandler <MouseEvent>() {
	            public void handle(MouseEvent event) {
	                /* drag was detected, start drag-and-drop gesture*/
	            
	                
	                /* allow any transfer mode */
	                Dragboard db = source.startDragAndDrop(TransferMode.COPY);
	                
	                /* put a string on dragboard */
	                ClipboardContent content = new ClipboardContent();
	                content.putString(source.getText());
	                db.setContent(content);
	                
	                draggedCourse = course;
	                
	                event.consume();
	            }
	        });
		 
		 source.setOnDragDone(new EventHandler <DragEvent>() {
	            public void handle(DragEvent event) {
	                /* the drag-and-drop gesture ended */
	                /* if the data was successfully moved, clear it */
	                draggedCourse = null;
	                Dragboard db = event.getDragboard();
	                db.clear();
	                
	                event.consume();
	            }
	        });
	}
	/*
	 * bind whole week
	 */
	public void bind(WeekPlanner planner) {
		this.planner = planner;

		int i = 0;
		for (studyplan.schedule.DailyPlanner dayPlanner : planner.getDays()) {
			bindDay(i, dayPlanner);
			i++;
		}
		
		courseListHBox.getChildren().clear();
		courseListHBox.getChildren().add(new Label("Please drag a course to day box to schedule a slef study"));
		for (Course course : planner.getSemester().getCourses()) {
			Label courseLabel = new Label(course.getName());
			courseLabel.setStyle(courseLabelCss);
			setAsDragAndDropSource(courseLabel, course);
			
			courseListHBox.getChildren().add(courseLabel);
		}

	}

	/*
	 * bind a day
	 */
	private void bindDay(int i, studyplan.schedule.DailyPlanner dayPlanner) {
		VBox dayBox = dayBoxes.get(i);
		dayBox.getChildren().clear();

		// day name & date label
		dayBox.getChildren().add(new Label(dayPlanner.getDate().getDayOfWeek().toString()));
		dayBox.getChildren().add(new Label(dayPlanner.getDate().toString()));

	

		// process scheduled items within day
		for (ScheduleItem item : dayPlanner.getItems()) {
			VBox itemBox = new VBox();
			itemBox.setStyle(itemBoxCss);
			
			if (TaskItem.class.isInstance(item)) { // bind the task to user interface
			
				Task task = ((TaskItem) item).getTask();

				Label nameLabel = new Label(task.getName()); // name of task
				itemBox.getChildren().add(nameLabel);
				Label isCompletedLabel = new Label(task.getIsCompleted() ? "Completed" : "Incomplete"); // status of task
				itemBox.getChildren().add(isCompletedLabel);
				itemBox.getChildren().add(new Label(task.getCourse().getName())); // course name
				// button for editing task
				Button editButton = new Button();
				editButton.setText("Edit");
				editButton.setOnAction(new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
		            	try {
		                ui.task.TaskViewController.showWindow(task);
		                if(task.getDueDate() != dayPlanner.getDate()) {
		                	planner.refreshDays();
		                	bind(planner);
		                }
		                
		                nameLabel.setText(task.getName());
		                isCompletedLabel.setText(task.getIsCompleted() ? "Completed" : "Incomplete");
		            	}
		            	catch(Exception ex) {
		            		
		            	}
		            }
		        });
				itemBox.getChildren().add(editButton);
				
			} else if(ActivityItem.class.isInstance(item)) { // bind activity instance to user interface
				ActivityInstance instance = ((ActivityItem)item).getActivityInstance();
				Activity activity = instance.getActivity();
				itemBox.getChildren().add(new Label(activity.getStart().format(DateTimeFormatter.ofPattern("HH:mm")))); // time of activity
				itemBox.getChildren().add(new Label(activity.getName())); // name of activity
				itemBox.getChildren().add(new Label(activity.getLocation())); // location of activity
				itemBox.getChildren().add(new Label(activity.getCourse().getName())); // name of course
				// button for editing activity instance 
				Button editButton = new Button();
				editButton.setText("Edit");
				editButton.setOnAction(new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
		            	try {
		                ui.activity.ActivityInstanceViewController.showWindow(instance);
		            	}
		            	catch(Exception ex) {
		            		
		            	}
		            }
		        });
				itemBox.getChildren().add(editButton);
			} else if(SelfStudyItem.class.isInstance(item)) { // bind self study to user interface
				
				SelfStudy selfStudy = ((SelfStudyItem)item).getSelfStudy();
				
				Label startLabel = new Label(selfStudy.getStart().format(DateTimeFormatter.ofPattern("HH:mm"))); // start time
				itemBox.getChildren().add(startLabel);
				itemBox.getChildren().add(new Label("Self Study"));
				Label locationLabel = new Label(selfStudy.getLocation()); // location
				itemBox.getChildren().add(locationLabel);
				itemBox.getChildren().add(new Label(selfStudy.getCourse().getName())); // name of course
				// button for editing self study
				Button editButton = new Button();
				editButton.setText("Edit");
				editButton.setOnAction(new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
		            	try {
		                ui.selfstudy.SelfStudyViewController.showWindow(selfStudy);
		                startLabel.setText(selfStudy.getStart().format(DateTimeFormatter.ofPattern("HH:mm")));
		                locationLabel.setText(selfStudy.getLocation());
		                
		            	}
		            	catch(Exception ex) {
		            		
		            	}
		            }
		        });
				itemBox.getChildren().add(editButton);
				// button for deleting self study
				Button deleteButton = new Button();
				deleteButton.setText("Delete");
				deleteButton.setOnAction(new EventHandler<ActionEvent>() {
		            @Override public void handle(ActionEvent e) {
		            	selfStudy.getCourse().removeSelfStudy(selfStudy);
		            	selfStudy.setCourse(null);
		            	
		            	dayBox.getChildren().remove(itemBox);
		            }
		        });
				itemBox.getChildren().add(deleteButton);
			}
			
			
			dayBox.getChildren().add(itemBox);
			
			
		}
		
		
	}

	/*
	 * creates a scene view 
	 */
	public static WeekViewController createView(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(WeekViewController.class.getResource("WeekView.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		WeekViewController controller = loader.getController();
		return controller;
	}
}
