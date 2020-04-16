package application.animation;

import java.io.File;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Shake {
	private TranslateTransition tt;										//Creez o variabila de tip TranslateTransition
	private Node nod;													//Si de tip Node
	
	public Shake(Node node) {											//Constructorul primeste ca parametru oiectul grafic node
		nod = node;	
		nod.setEffect(new DropShadow(15, Color.RED));					//Setez efectul dorit
		tt = new TranslateTransition(Duration.millis(100), node);		//Ii atribui variabilei tt o copie a clasei TranslateTransition, careia ii ofer ca parametru durata si obiectul grafic
		tt.setFromX(-5f);												//Setez distanta animatiei
		tt.setByX(5f);
		tt.setCycleCount(3);											//Setez nr de repetari
		tt.setAutoReverse(true);										//Setez ca animatia sa fie reversibila

		String musicFile = "src/libs/audio/no.mp3";     				// Adaug un sunten specific
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setVolume(0.15);									//Setez volumul
		mediaPlayer.play();
	}
	
	public void playAnim() {

		tt.playFromStart();												//Animez obiectul
		tt.setOnFinished(Event ->{										//La sfaristul animatiei va disparea efectul de umbra
			nod.setEffect(new DropShadow(15, Color.TRANSPARENT));
		});
		
	}
}