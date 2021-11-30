package project;
	
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;


public class startPage extends Application {
	
	public static Stage pps;
	public static Scene scene;
	public static BorderPane root = new BorderPane();

	
	
	@Override
	public void start(Stage primaryStage) {
		pps = primaryStage;
		CommonClass.loadBinaryData();
		Label label = new Label("Registration System");
		Font font = new Font(40);
		label.setFont(font);
		
		Button b1 = new Button("View Course");
		Button b2 = new Button("View Students Details");
		Button b3 = new Button("Save");
		HBox hb = new HBox(20);
		hb.getChildren().addAll(b1, b2, b3);
		hb.setAlignment(Pos.CENTER);
		
		
		root.setCenter(label);
		root.setBottom(hb);
		root.setPadding(new Insets(20));
		
		//modifying buttons
		
												//View Courses Button
		b1.setOnAction(e -> {
			scene.setRoot(new CoursePage());
		});
												//View Students Details Button
		b2.setOnAction(e ->{
			scene.setRoot(new StudentPage());
		});
		
												//Save Button
		
		b3.setOnAction(e->{
										
				try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("res\\Registration.dat"))){
					
					//saving courses array list
					output.writeObject(CommonClass.courseList);
						
					//saving students array list
					output.writeObject(CommonClass.studentList);
					
				}catch (FileNotFoundException e1) {e1.printStackTrace();} 
				catch (IOException e1) {e1.printStackTrace();}
												
		});
		
		
		scene = new Scene(root,800,600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
}
