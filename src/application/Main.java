package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Parent principal;
		try {
			principal = FXMLLoader.load(getClass().getResource("/views/principal.fxml"));
			Scene scene = new Scene(principal, 640, 400);
			scene.getStylesheets().add("/application/application.css");
			
			primaryStage.setTitle("Jogo");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
