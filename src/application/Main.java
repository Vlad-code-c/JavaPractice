package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {										
	@Override
	public void start(Stage primaryStage) throws IOException {					//Creez functia supraincarcata din clasa Application
		Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));		//Indic fisierul fxml,
		primaryStage.setTitle("Hello");											//titlul,
		primaryStage.setScene(new Scene(root, 600, 400));						//Dimensiunile
		primaryStage.show();													//Si afisez aplicatia creata
	}
	
	public static void main(String[] args) {
		launch(args);															//Pornesc aplicatia
	}
}
