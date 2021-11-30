module registration {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	
	opens project to javafx.graphics, javafx.fxml;
}
