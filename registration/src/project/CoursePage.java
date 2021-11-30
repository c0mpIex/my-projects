package project;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CoursePage extends BorderPane {
	
	
	//list view for courses IDs
	private ListView<String> courses = new ListView<>(FXCollections.observableArrayList());

	
	
	//list view for IDs
	private ListView<String> ids = new ListView<>(FXCollections.observableArrayList());;
	
	
	//ID TextField
	TextField ID = new TextField();
	
	//Name TextField
	TextField Name = new TextField();
	
	//Days TextField
	TextField Days = new TextField();
	
	//Location TextField
	TextField Location = new TextField();
	
	//Time TextField
	TextField time = new TextField();
	
	//adding index tracking and -1 for starting from the beginning and it just works :)
	private int index = -1;
	
	
	
	
	public CoursePage() {

		//adding courses name to the list
		for (int i =0; i<CommonClass.courseList.size();i++)
			courses.getItems().add(CommonClass.courseList.get(i).getCourseID());  //setting courses name to the List View--------------------------------important
		

		
		//some modification
		courses.setPrefSize(200, 0);
		
		
		// grid pane for display information
		GridPane gp = new GridPane();
		
		//ID label
		gp.add(new Label("ID"), 0, 1);
		
		//adding ID textField to the grid
		ID.setEditable(true);
		gp.add(ID, 1, 1);
		
		//Name label
		gp.add(new Label("Name"), 0, 2);
		
		//adding Name TextField to the grid
		Name.setEditable(false);
		gp.add(Name, 1, 2);
		
		//Days label
		gp.add(new Label("Days"), 0, 3);
		
		//adding Days TextField to the grid
		Days.setEditable(false);
		gp.add(Days, 1, 3);
		
		//Location label
		gp.add(new Label("Location"), 0, 4);
		
		//adding Location TextField to the grid
		Location.setEditable(false);
		gp.add(Location, 1, 4);
		
		//time label
		gp.add(new Label("time"), 0, 5);
		
		//adding time TextField the grid
		time.setEditable(false);
		gp.add(time, 1, 5);
		
		//time status label
		gp.add(new Label("status"), 0, 6);
		
		//adding time combo box to the grid
		ArrayList<String> status = new ArrayList<>();
		status.add("closed");
		status.add("opened");
		ComboBox<String> comboBox = new ComboBox<String>(FXCollections.observableArrayList(status));
		gp.add(comboBox, 1, 6);
		
		//grid pane modification
		gp.setPadding(new Insets(20));
		gp.setVgap(12);
		gp.setHgap(40);
		gp.setAlignment(Pos.CENTER);
		
		
		//adding 4 buttons and HBox
		Button back = new Button("Back");
		Button previous = new Button("< Previous");
		Button next = new Button("Next >");
		Button search = new Button("Search");
		HBox hb = new HBox(20);
		
		//adding 4 buttons to HBox
		hb.getChildren().addAll(back, previous, next, search);
		hb.setAlignment(Pos.CENTER);
		
		//Modifying buttons
		
											//back button
		back.setOnAction(e->{
			startPage.scene.setRoot(startPage.root);
		});
		
											//previous button
		previous.setOnAction(e->{
			if (index>0)
				index--;
			else
				index = CommonClass.courseList.size() - 1;
			
			ids.getItems().clear();         //removing the previous items in listView
			courses.getSelectionModel().select(index); 			//select the first item in list view
			ID.setText(CommonClass.courseList.get(index).getCourseID());
			Name.setText(CommonClass.courseList.get(index).getCourseName());
			Days.setText(CommonClass.courseList.get(index).getCourseDays());
			Location.setText(CommonClass.courseList.get(index).getCourseLocation());
			time.setText(CommonClass.courseList.get(index).getCourseTime());
			for (int i = 0; i<CommonClass.studentList.size(); i++) 
				if (CommonClass.studentList.get(i).getCourses().contains(CommonClass.courseList.get(index)))
					ids.getItems().add(CommonClass.studentList.get(i).getStudID());
			
			//label for the number of student registered
			Label numberRegistered = new Label("There are " + ids.getItems().size() + " registered in " + CommonClass.courseList.get(index).getCourseID());
			setTop(numberRegistered);
			
			if (CommonClass.courseList.get(index).getAvailableSeats() == 0) 
				comboBox.getSelectionModel().select(0);
			else
				comboBox.getSelectionModel().select(1);
		});
		
		
		
											//next button
		next.setOnAction(e->{
			if (index<CommonClass.courseList.size()-1)
				index++;
			else
				index = 0;
			
			ids.getItems().clear();         //removing the previous items in listView
			courses.getSelectionModel().select(index); 			//select the first item in list view
			ID.setText(CommonClass.courseList.get(index).getCourseID());
			Name.setText(CommonClass.courseList.get(index).getCourseName());
			Days.setText(CommonClass.courseList.get(index).getCourseDays());
			Location.setText(CommonClass.courseList.get(index).getCourseLocation());
			time.setText(CommonClass.courseList.get(index).getCourseTime());
			for (int i = 0; i<CommonClass.studentList.size(); i++) 
				if (CommonClass.studentList.get(i).getCourses().contains(CommonClass.courseList.get(index)))
					ids.getItems().add(CommonClass.studentList.get(i).getStudID());
			
			//label for the number of student registered
			Label numberRegistered = new Label("There are " + ids.getItems().size() + " registered in " + CommonClass.courseList.get(index).getCourseID());
			setTop(numberRegistered);
			
			if (CommonClass.courseList.get(index).getAvailableSeats() == 0)
				comboBox.getSelectionModel().select(0);
			else
				comboBox.getSelectionModel().select(1);
			
			

		});

										//search button
		search.setOnAction(e->{
			ids.getItems().clear();         //removing the previous items in listView
			if (courses.getItems().contains(ID.getText().toUpperCase())) {
				for (int k = 0; k<CommonClass.courseList.size(); k++) 
					if (ID.getText().toUpperCase().equals(CommonClass.courseList.get(k).getCourseID())) {
						index = k; //just to organize things
						courses.getSelectionModel().select(k); 			//select the first item in list view
						Name.setText(CommonClass.courseList.get(k).getCourseName());
						Days.setText(CommonClass.courseList.get(k).getCourseDays());
						Location.setText(CommonClass.courseList.get(k).getCourseLocation());
						time.setText(CommonClass.courseList.get(k).getCourseTime());
						
						for (int i = 0; i<CommonClass.studentList.size(); i++) 
							if (CommonClass.studentList.get(i).getCourses().contains(CommonClass.courseList.get(k)))
								ids.getItems().add(CommonClass.studentList.get(i).getStudID());
						
						//label for the number of student registered
						Label numberRegistered = new Label("There are " + ids.getItems().size() + " registered in " + CommonClass.courseList.get(k).getCourseID());
						setTop(numberRegistered);
						
						if (CommonClass.courseList.get(k).getAvailableSeats() == 0)
							comboBox.getSelectionModel().select(0);
						else
							comboBox.getSelectionModel().select(1);
						break;
					}
				if (gp.getChildren().size() == 13) // to check if the pane ("course not found") is exist
					gp.getChildren().remove(gp.getChildren().size() - 1); //removing course not found label
					
			}
			
			else
				if (gp.getChildren().size() == 12) // to check if the pane ("course not found") is not exist
					gp.add(new Label("course not found"), 1, 0); //display course not found label
				
		});
		
		

		//some modification
		setPadding(new Insets(20));
		
		
		//set everything to border pane
		setLeft(courses);
		setRight(ids);
		setCenter(gp);
		setBottom(hb);

	}
	
		

		
}
	
