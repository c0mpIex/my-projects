package project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class StudentPage extends BorderPane {
	
	//Registered courses list View
	ListView<String> Rcourses = new ListView<>();
			
	//student ID TextField
	TextField sID = new TextField();
	
	//adding index tracking and -1 for starting from the beginning and it just works :)
	private int index = -1;

	
	public StudentPage() {
		
		// grid pane for display information
		GridPane gp = new GridPane();
	
		//student ID label
		gp.add(new Label("student ID"),0,0);
		
		//adding student ID text field to grid pane
		gp.add(sID, 1, 0);
		
		//Registered courses label
		gp.add(new Label("Registered courses"), 0, 2);
		
		//adding list view to grid pane
		gp.add(Rcourses, 1, 2);
		
		//not Registered courses Label
		gp.add(new Label("not Registered courses"), 0, 3);
		
		//adding not Registered courses combo box
		ComboBox<String> NRcourses = new ComboBox<>();
		gp.add(NRcourses, 1, 3);
		
		//Modifying grid pane
		gp.setPadding(new Insets(20));
		gp.setVgap(12);
		gp.setHgap(40);
		gp.setAlignment(Pos.CENTER);
		
		
		//adding 6 buttons to HBox
		Button back = new Button("Back");
		Button previous = new Button("< Previous");
		Button next = new Button("Next >");
		Button register = new Button("Register");
		Button drop = new Button("Drop");
		Button search = new Button("Search");
		HBox hb = new HBox(20);
		hb.getChildren().addAll(back, previous, next, register, drop, search);
		
		//Modifying HBox
		hb.setAlignment(Pos.CENTER);
		
		
		//Modifying buttons
		
											//back button
		back.setOnAction(e->{
			startPage.scene.setRoot(startPage.root);
		});
		
											//previous button
		previous.setOnAction(e->{
			
			Rcourses.getItems().clear(); // to clear all items before adding new ones
			NRcourses.getItems().clear(); // to clear all items before adding new ones
			
			if (0 < index)
				index--;
			else 
				index = CommonClass.studentList.size()-1;
				
			
			sID.setText(CommonClass.studentList.get(index).getStudID());
			
			//adding courses to list view
			for (Course course: CommonClass.studentList.get(index).getCourses())
				Rcourses.getItems().add(course.getCourseID());
			
			for (Course course: CommonClass.courseList) 
				if (!Rcourses.getItems().contains(course.getCourseID()) && course.getAvailableSeats() > 0)
					NRcourses.getItems().add(course.getCourseID());
		});
		
											//next button
		next.setOnAction(e->{
			
			Rcourses.getItems().clear(); // to clear all items before adding new ones
			NRcourses.getItems().clear(); // to clear all items before adding new ones
			if (index<CommonClass.studentList.size()-1)
				index++;
			else 
				index = 0;
				
			
			sID.setText(CommonClass.studentList.get(index).getStudID());
			
			//adding courses to list view
			for (Course course: CommonClass.studentList.get(index).getCourses())
				Rcourses.getItems().add(course.getCourseID());
			
			for (Course course: CommonClass.courseList)  // to add courses that are not registered and have available seats to combo box
				if (!Rcourses.getItems().contains(course.getCourseID()) && course.getAvailableSeats() > 0)
					NRcourses.getItems().add(course.getCourseID());
			
				
				
			
			
		});
											//register button
		register.setOnAction(e->{
			for (int i = 0; i<CommonClass.studentList.size(); i++) //searching for the student by IDs
				if (CommonClass.studentList.get(i).getStudID().equals(sID.getText())) 
					for (int j=0; j<CommonClass.courseList.size(); j++)  //searching for the wanted course to register
						
						if (CommonClass.courseList.get(j).getCourseID().equals(NRcourses.getValue())) {
							
							CommonClass.studentList.get(i).getCourses().add(CommonClass.courseList.get(j)); // to register the course in courses Array list
							Rcourses.getItems().add(CommonClass.courseList.get(j).getCourseID()); // to add the registered course in the list view
							
							CommonClass.courseList.get(j).register(); //to decremented the available seats by one
							
							break;
							}
			
			NRcourses.getItems().clear(); // to clear all items before adding new ones
			for (Course course: CommonClass.courseList)  // to add courses that are not registered and have available seats to combo box
				if (!Rcourses.getItems().contains(course.getCourseID()) && course.getAvailableSeats() > 0)
					NRcourses.getItems().add(course.getCourseID());
			
		});
		
											//drop button
		drop.setOnAction(e->{
			
			for (int i = 0; i<CommonClass.studentList.size(); i++) //searching for the student by IDs
				if (CommonClass.studentList.get(i).getStudID().equals(sID.getText())) {
					for (int j=0; j<CommonClass.courseList.size(); j++) //searching for the wanted course to drop
						
						if (CommonClass.courseList.get(j).getCourseID().equals(Rcourses.getSelectionModel().getSelectedItem())) 
							CommonClass.courseList.get(j).drop(); //increment the dropped course by one
					
			
					CommonClass.studentList.get(i).getCourses().remove(Rcourses.getSelectionModel().getSelectedIndex()); //dropping the course from the student
					Rcourses.getItems().remove(Rcourses.getSelectionModel().getSelectedIndex()); //dropping the course from the list view
					break;
					}
							
			
			NRcourses.getItems().clear(); // to clear all items before adding new ones
			for (Course course: CommonClass.courseList)  // to add courses that are not registered and have available seats to combo box
				if (!Rcourses.getItems().contains(course.getCourseID()) && course.getAvailableSeats() > 0)
					NRcourses.getItems().add(course.getCourseID());
			
		});
		
											//search button
		search.setOnAction(e->{
			Rcourses.getItems().clear(); // to clear all items before adding new ones
			NRcourses.getItems().clear(); // to clear all items before adding new ones
			for (int i =0; i<CommonClass.studentList.size(); i++)
				if (CommonClass.studentList.get(i).getStudID().equals(sID.getText())) {
					for (Course course: CommonClass.studentList.get(i).getCourses())
						Rcourses.getItems().add(course.getCourseID());
					
					for (Course course: CommonClass.courseList)  // to add courses that are not registered and have available seats to combo box
						if (!Rcourses.getItems().contains(course.getCourseID()) && course.getAvailableSeats() > 0)
							NRcourses.getItems().add(course.getCourseID());
					
					if (gp.getChildren().size() == 7) // to check if the pane ("course not found") is exist
						gp.getChildren().remove(gp.getChildren().size() - 1); //removing course not found label
					break;
				}
				else
					if (gp.getChildren().size() == 6) // to check if the pane ("ID not found") is not exist
						gp.add(new Label("ID not found"), 1, 1); //display course not found label
			
		});
		
		//some modification
		setPadding(new Insets(20));
		
		//adding everything to Border Pane
		setCenter(gp);
		setBottom(hb);
		
		
		}

}
