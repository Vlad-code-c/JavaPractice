package application.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Succes {
	private TranslateTransition tt;										//Creez o variabila de tip TranslateTransition
	private Node nod;													//Si de tip Node
	
	public Succes(Node node) {											//Constructorul primeste ca parametru oiectul grafic node
		nod = node;
		nod.setEffect(new DropShadow(15, Color.LIMEGREEN));					//Setez efectul dorit
		tt = new TranslateTransition(Duration.millis(300), node);		//Ii atribui variabilei tt o copie a clasei TranslateTransition, careia ii ofer ca parametru durata si obiectul grafic
		tt.setFromX(0f);												//Setez distanta animatiei
		tt.setByX(0f);
	}
	
	public void playAnim() {
		tt.playFromStart();											//Animez obiectul
		tt.setOnFinished(Event ->{									//La sfaristul animatiei va disparea efectul de umbra
			nod.setEffect(new DropShadow(15, Color.TRANSPARENT));
		});
	}
}
