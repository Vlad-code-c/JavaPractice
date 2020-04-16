package controlers;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application.animation.Shake;
import application.animation.Succes;
import controlers.database.Const;
import controlers.database.DatabaseHandler;
import controlers.database.Turn;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class ControllerMain {

    //Obiectele grafice din care consta aplicatia
    @FXML
    private Button buttonMenuAddRecords;

    @FXML
    private Button buttonShowRecords;

    @FXML
    private Button maxRecordsButton;

    @FXML
    private Button buttonShowSortedRecords;

    @FXML
    private Button patrateSaveTab;

    @FXML
    private Button interschimba;

    @FXML
    private Button inaltimeaTurnuluiMaximala;

    @FXML
    private AnchorPane addRecords;

    @FXML
    private Button buttonAddRecords;

    @FXML
    private TextField height;

    @FXML
    private TextField long_;

    @FXML
    private TextField width;

    @FXML
    private AnchorPane showRecords;

    @FXML
    private Button doublePrev;

    @FXML
    private Button doubleNext;

    @FXML
    private Button prev;

    @FXML
    private Button next;

    @FXML
    private Label labelHeight;

    @FXML
    private Label labelLong;

    @FXML
    private Label labelWidth;

    @FXML
    private Label labelId;

    @FXML
    private Button showVisual;

    @FXML
    private Button buttonDelete;

    @FXML
    private Label labelVolum;

    @FXML
    private AnchorPane visualTabShow;

    @FXML
    private Label W;

    @FXML
    private Label H;

    @FXML
    private Label L;

    @FXML
    private Button visionClose;

    @FXML
    private ImageView LImage;

    @FXML
    private ImageView WImage;

    @FXML
    private ImageView HImage;

    @FXML
    private Label aria;

    @FXML
    private AnchorPane maximTab;

    @FXML
    private Button minPrev;

    @FXML
    private Button maxNext;

    @FXML
    private Label inaltimeaMaxLabel;

    @FXML
    private Label lungimeaMaxLabel;

    @FXML
    private Label latimeaMaxLabel;

    @FXML
    private Label labelHeightMax;

    @FXML
    private Label labelLongMax;

    @FXML
    private Label labelWidthMax;

    @FXML
    private Label labelVolumMax;

    @FXML
    private Label volumMax;

    @FXML
    private AnchorPane patrate;

    @FXML
    private Button buttonAddFile;

    @FXML
    private TextField path;

    @FXML
    private AnchorPane interschimbaTab;

    @FXML
    private Button interschimbaButton;

    @FXML
    private RadioButton HW;

    @FXML
    private ToggleGroup schimb;

    @FXML
    private RadioButton HL;

    @FXML
    private RadioButton WL;

    @FXML
    private AnchorPane inaltimeaMaximala;

    @FXML
    private Label inaltimeaMaximLabel;
    
    
    int idx = 0;
    ArrayList<Turn> turn;
    String m = "max";
    ResultSet patrateList;						//Obiect in care se va pastra rezultatul selectiei din baza de date
    enum commands {ADD, SHOW, MAXIM, SQUARE, INTERCH, MAX_HEIGHT};
    
    String musicFile = "src/libs/audio/buttonClick.mp3";     			//Sirul va contine calea spre fisierul audio, pe care il voi folosi in tot programul
    Media sound = new Media(new File(musicFile).toURI().toString());	//Obiectul sound va fi folosit pentru redarea sunetului

	MediaPlayer mediaPlayerHeart = new MediaPlayer(new Media(new File("src/libs/audio/heart.mp3").toURI().toString())); //Incarc alt fisier audio, oferindu-i parametrii direct, fara alte variabile
	
        
    @FXML
    void initialize() {													//Functia care va fi apelata la initierea aplicatiei grafice

    	DatabaseHandler dbHandler = new DatabaseHandler();				//Un exemplar al clasei, care va fi folosit pentru a accesa baza de date
    	
    	buttonAddRecords.setOnAction(Event -> {  						//Adaugarea unui eveniment la apasarea butonului bottonAddRecords, ce primeste ca parametru o functie 
    		if(isValidInput()) {															//Daca toate valorile sunt introduse corect
    			Turn turn = new Turn(height.getText(), width.getText(), long_.getText());	//Se va crea un obiect de tip Turn, 
    			try {
					dbHandler.addTurn(turn);												//si se va incerca inserarea acestuia in baza de date
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();}
    			System.out.println("Datele au fost introduse cu succes in baza de date");
    		   		
    			height.setText("");									//Resetez valoarea casetelor text
    			width.setText("");
    			long_.setText("");
    			
    			Succes suc1 = new Succes(width);					//Creez cate un exemplar al clasei Succes, iar in constructor ii transmit obiectul fx
    			Succes suc2 = new Succes(height);
    			Succes suc3 = new Succes(long_);
    			suc1.playAnim();									//Apelez metoda playAnim() care va crea un efect de neon verde, aratand utilizatorului ca datele au fost introduse cu succes				
    			suc2.playAnim();
    			suc3.playAnim();
    			
    		}
    		else {													//Daca datele introduse nu sunt corecte, se va crea cate un exemlar al clasei Shake,
    			Shake shake = new Shake(width);						//care va evidentia cu rosu casetele text, aratand utilizatorului ca datele sunt incorecte
    			Shake shake2 = new Shake(height);
    			Shake shake3 = new Shake(long_);
    			shake.playAnim();
    			shake2.playAnim();
    			shake3.playAnim();
    			width.setText("");									//Apoi valorile casetelor sunt resetate
    			height.setText("");
    			long_.setText("");
    			
    		}

    	});
    	
    	buttonMenuAddRecords.setOnAction(Event ->{					//Adaug un eveniment la apasarea butonului buttonMenuAddRecords
    	    MediaPlayer mediaPlayer = new MediaPlayer(sound);		
    		mediaPlayer.play();						//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		setVisibility(commands.ADD);			//Apelez functia setVisibility care va scimba visibilitatea meniului actual si a celui dorit
    	});
    	buttonShowRecords.setOnAction(Event -> {				//Adaug un eveniment la apasarea butonului buttonShowRecord
    	    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    		mediaPlayer.play();						//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		setVisibility(commands.SHOW);			//Apelez functia setVisibility care va scimba visibilitatea meniului actual si a celui dorit
    		idx = 0;

    		try {
				turn = readFromBd();				//Incerc sa citesc datele din baza de date folosind functia readFromBd()
				
				update(turn.get(idx));				//Setez valorile ce vor fi afisate la ecran folosind functia update()
				
				
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();}				//In caz de esec, se va afisa sursa problemei
    	});
    	
    	buttonShowSortedRecords.setOnAction(Event -> {				//Adaug un eveniment la apasarea butonului buttonShowSortedRecords
    	    MediaPlayer mediaPlayer = new MediaPlayer(sound);	
    		mediaPlayer.play();						//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		setVisibility(commands.SHOW);			//Apelez functia setVisibility care va scimba visibilitatea meniului actual si a celui dorit
    		idx = 0;
    	
    		try {
				turn = readFromBdDESC();			//Citesc datele din baza de date in mod descrescator
				
				update(turn.get(idx));				//Setez valorile ce vor fi afisate la ecran folosind functia update()
				
				
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();}				//In caz de esec, se va afisa sursa problemei
    	});
    	
    	
    	buttonDelete.setOnAction(Event ->{						//Adaug un eveniment la apasarea butonului buttonDelete
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);
    		mediaPlayer.play();						//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		
    		try {
				dbHandler.deleteData(Integer.parseInt(labelId.getText()));		//Apelez functia deleteDate din clasa DatabaseHandler, dandu-i ca parametru valoarea actuala afisata pe ecran pentru ca aceasta sa fie eliminata
				Succes del = new Succes(labelId);								//Creez un exemplar al clasei Succes
				del.playAnim();													//Apelez metoda playAnim a obiectului del, care va confirma visual ca datele au fost sterse din baza de date
				labelId.setText("0");											//Setez casetei id valoarea 0 pentru a anunta utilizatorul da a fost stearsa
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();	
				Shake del = new Shake(labelId);
				del.playAnim();								//In caz de esec se afiseaza in consola sursa erorii si se animeaza corespunzator obiecul del
			}
    	});
    	
    	maxRecordsButton.setOnAction(Event -> {					//Adaug un eveniment la apasarea butonului
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);	//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		mediaPlayer.play();
    		
    		setVisibility(commands.MAXIM);						//Setez meniul Maxim ca vizibil
    		
    		updateMaxime();										//Afisez la ecran valorile citite din bd
    	});
    	minPrev.setOnAction(Event -> {							//Adaug un eveniment la apasarea butonului 
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);	//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		mediaPlayer.play();
    		m = "max";											//Pentru a evita erorile de tipul IndexOutBoundException utilizez o varibabila ce va servi ca starea actuala
    		updateMaxime();											
    	});
    	maxNext.setOnAction(Event -> {							//Adaug un eveniment la apasarea butonului 
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);	//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		mediaPlayer.play();
    		m = "min";											//Pentru a evita erorile de tipul IndexOutBoundException utilizez o varibabila ce va servi ca starea actuala
    		updateMaxime();										//Afisez la ecran valorile citite din bd
    	});
    	
    	
    	doublePrev.setOnAction(Event -> {						//Adaug un eveniment la apasarea butonului 
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);	//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		mediaPlayer.play();
    		idx = 0;											//Pentru a evita erorile de tipul IndexOutBoundException utilizez o varibabila ce va servi ca indexul acutal
    		update(turn.get(idx));								//Afisez la ecran valorile citite din bd							
    	});
    	doubleNext.setOnAction(Event -> {						//Adaug un eveniment la apasarea butonului 
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);	//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		mediaPlayer.play();
    		idx = turn.size() - 1;								//Setez valoarea maxima a variabilei idx
    		update(turn.get(idx));								//Afisez la ecran valorile citite din bd
    	});
    	prev.setOnAction(Event -> {								//Adaug un eveniment la apasarea butonului 
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);	//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		mediaPlayer.play();
    		if(idx > 0) {										//Verific daca e posibila decrementarea
    			idx--;
    			update(turn.get(idx));							//Afisez la ecran valorile citite din bd
    		}
    	});	
    	next.setOnAction(Event -> {							//Adaug un eveniment la apasarea butonului 
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);	//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		mediaPlayer.play();
    		if(idx < turn.size()-1) {							//Verific daca e posibila incrementarea 
    			idx++;
    			update(turn.get(idx));							//Afisez la ecran valorile citite din bd
    		}
    	});
    	
    	showVisual.setOnAction(Event -> {					//Adaug un eveniment la apasarea butonului 
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);	//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		mediaPlayer.play();
    		visualTabShow.setVisible(true);						//Afisez deasupra meniului showRecords meniul visualTabShow
    		W.setText(Integer.toString(turn.get(idx).getW()));	//Setez cele 3 dimensiuni
    		H.setText(Integer.toString(turn.get(idx).getH()));
    		L.setText(Integer.toString(turn.get(idx).getL()));
    		
    	});
    	
    	visionClose.setOnAction(Event -> {					//Adaug un eveniment la apasarea butonului 
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);	//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		mediaPlayer.play();
    		visualTabShow.setVisible(false);					//Ascund meniul visualTabShow
    	});
    	

    	patrateSaveTab.setOnAction(Event -> {					//Adaug un eveniment la apasarea butonului 
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);	//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		mediaPlayer.play();
    		setVisibility(commands.SQUARE);						//Afisez meniul Square
    	});
    	
    	buttonAddFile.setOnAction(Event -> {					//Adaug un eveniment la apasarea butonului 
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);	//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		mediaPlayer.play();
    		try {
    			patrateList = dbHandler.patrate();				//Incerc sa citesc datele despre patratele aflate in fisier

    			ArrayList<Turn> patrateArrayList = new ArrayList<Turn>(); //Declar un ArrayList in care voi adauga elementele din variabila patrateList, care este de tip ResultSet
    			
    			while(patrateList.next()) {
    				Turn t = new Turn(patrateList.getString(Const.TURN_H), patrateList.getString(Const.TURN_W), patrateList.getString(Const.TURN_L));
    				patrateArrayList.add(t); 					//Adaug in patrateArrayList cate un turn nou
    			}
    			
    			
				FileWriteRead.fileWrite(path.getText(), patrateArrayList);	//Apelez functia fileWrite din clasa FileWriteRead
				Succes s = new Succes(path);								//Adaug o animatie casetei text path
				s.playAnim();
				path.setText("");											//Resetez valoarea casetei
				
			} catch (IOException | SQLException | ClassNotFoundException e) { //In caz de esec
				e.printStackTrace();										//Afisez sursa problemei
				Shake s = new Shake(path);									//Adaug o animatie casetei text path
				s.playAnim();
			}
    	});
    	
    	interschimba.setOnAction(Event -> {							//Adaug un eveniment la apasarea butonului 
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);	//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		mediaPlayer.play();
    		setVisibility(commands.INTERCH);					//Setez meniul interschimbaTab visibil
    	});
    	
    	interschimbaButton.setOnAction(Event -> {					//Adaug un eveniment la apasarea butonului 
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);	//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		mediaPlayer.play();
    		String what;
    		if(HW.isSelected())									//Folosind obiecte de tip radioBox, determin care coloane vor fi interschimbate
    			what = "HW";
    		else if(HL.isSelected())
    			what = "HL";
    		else
    			what = "WL";
    		
    		try {
    			dbHandler.schimba(what);						//Incerc sa schimb cele 2 coloane
    			Succes s = new Succes(interschimbaButton);		//Adaug o animatie butonului
    			s.playAnim();
			} catch (ClassNotFoundException | SQLException e) { //In caz de esec
				e.printStackTrace();							//Afisez in consola sursa problemei
				Shake s = new Shake(interschimbaButton);		//Adaug o animatie butonului
				s.playAnim();
			}
    	});
    	
    	
    	
    	
    	inaltimeaTurnuluiMaximala.setOnAction(Event -> {						//Adaug un eveniment la apasarea butonului 
    		MediaPlayer mediaPlayer = new MediaPlayer(sound);	//La apasarea butonului se va reproduce fisierul audio, adresa caruia e indicata in variabila music	
    		mediaPlayer.play();
    		setVisibility(commands.MAX_HEIGHT);					//Setez visibilitatea meniului MaxHeight ca fiind true
    		
    		
    		
    		try {
				ArrayList<Turn> t = readFromBdDESC();			//Incerc sa citesca datele din baza de date in ordine descrescatoare
				int count = 1;									//Setez nr de turnur ca fiind 1

				for(int i = 1; i < t.size(); i++) {				//Determin daca e posibil suprapunerea unui turn asupra altuia precedent
					if((t.get(i-1).getL() >= t.get(i).getL() || t.get(i-1).getW() >= t.get(i).getL()) && (t.get(i-1).getL() >= t.get(i).getW() || t.get(i-1).getL() >= t.get(i).getL())) {
						count++;
					}
					else
						break;								//Daca operatia nu mai poate fi efectuata, nu are sens sa irosim resursele calculatorului
				}
				
				inaltimeaMaximLabel.setText(Integer.toString(count));	//Setez casetei label variabila count
			} catch (ClassNotFoundException | SQLException e) {		//In caz de esec
				e.printStackTrace();}								//Afisez sursa erorii
    	});
    	
    	
    	
   		mediaPlayerHeart.setOnEndOfMedia(new Runnable() {			//Creez un nou thread pentru a opri sunetul
   			public void run() {
   				mediaPlayerHeart.seek(Duration.ZERO);
    		}
   		});
    	LImage.addEventHandler(MouseEvent.MOUSE_ENTERED, Event -> {		//Adaug un event, care se va activa cand sageata mouse-ului 
    		mediaPlayerHeart.play();									//se va afla pe imaginea cu fxid-ul LImage
    		

    		Image imageObject = new Image("/libs/img/cub/W_BLACK.png");
    		LImage.setImage(imageObject);
    		
    		if(!L.getText().equals("L"))								//Determin daca caseta label contine date reale
    		aria.setText("Aria: " + String.valueOf(Integer.parseInt(L.getText()) * Integer.parseInt(H.getText()))); //Afisez datele despre aria intre lungime si inaltime
    	});
    	LImage.addEventHandler(MouseEvent.MOUSE_EXITED, Event -> {		//Adaug un event, care se va activa cand sageata mouse-ului 
    		mediaPlayerHeart.stop();									//va iesi de pe imaginea cu fxid-ul LImage
    		aria.setText("");											//Resetez valoarea casetei aria, creand efectul de disparitie

    		Image imageObject = new Image("/libs/img/cub/W_WHITE.png");
    		LImage.setImage(imageObject);

    	});	
    	
    	HImage.addEventHandler(MouseEvent.MOUSE_ENTERED, Event -> {		//Adaug un event, care se va activa cand sageata mouse-ului 
    		mediaPlayerHeart.play();									//se va afla pe imaginea cu fxid-ul HImage
    		
    		if(!H.getText().equals("H"))								//Determin daca caseta label contine date reale
    		aria.setText("Aria: " + String.valueOf(Integer.parseInt(H.getText()) * Integer.parseInt(W.getText()))); //Afisez datele despre aria intaltime si latime

    		Image imageObject = new Image("/libs/img/cub/H_BLACK.png");
    		HImage.setImage(imageObject);

    	});
    	HImage.addEventHandler(MouseEvent.MOUSE_EXITED, Event -> {		//Adaug un event, care se va activa cand sageata mouse-ului 
    		mediaPlayerHeart.stop();									//va iesi de pe imaginea cu fxid-ul HImage
    		aria.setText("");											//Resetez valoarea casetei aria, creand efectul de disparitie						

    		Image imageObject = new Image("/libs/img/cub/H_WHITE.png");
    		HImage.setImage(imageObject);

    	});
    	
    	WImage.addEventHandler(MouseEvent.MOUSE_ENTERED, Event -> {		//Adaug un event, care se va activa cand sageata mouse-ului 
    		mediaPlayerHeart.play();									//se va afla pe imaginea cu fxid-ul WImage
    		
    		if(!W.getText().equals("W"))								//Determin daca caseta label contine date reale
    		aria.setText("Aria: " + String.valueOf(Integer.parseInt(W.getText()) * Integer.parseInt(L.getText()))); //Afisez datele despre aria intre lungime si latime

    		Image imageObject = new Image("/libs/img/cub/L_BLACK.png");
    		WImage.setImage(imageObject);

    	});
    	WImage.addEventHandler(MouseEvent.MOUSE_EXITED, Event -> {		//Adaug un event, care se va activa cand sageata mouse-ului 
    		mediaPlayerHeart.stop();									//va iesi de pe imaginea cu fxid-ul WImage
    		aria.setText("");											//Resetez valoarea casetei aria, creand efectul de disparitie

    		Image imageObject = new Image("/libs/img/cub/L_WHITE.png");
    		WImage.setImage(imageObject);

    	});
    }
    
    void setVisibility(commands wh) {									//Primesc ca parametru una din valorile declarate in enumeratia commands	
		addRecords.setVisible(false);									//Setez tuturol meniurilor visibilitatea 0
		showRecords.setVisible(false);
		maximTab.setVisible(false);
		patrate.setVisible(false);
		interschimbaTab.setVisible(false);
		inaltimeaMaximala.setVisible(false);

		switch(wh) {													//Apoi setez meniului necesar visibilitatea true
			case ADD:
				addRecords.setVisible(true);
				break;
			case SHOW:
				showRecords.setVisible(true);
				break;
			case MAXIM:
				maximTab.setVisible(true);
				break;
			case SQUARE:
				patrate.setVisible(true);
				break;
			case INTERCH:
				interschimbaTab.setVisible(true);
				break;
			case MAX_HEIGHT:
				inaltimeaMaximala.setVisible(true);
		}
    }
    
    ArrayList<Turn> readFromBd() throws ClassNotFoundException, SQLException {			//Pentru a evita oprirea aplicatiei, capturez erorile ce pot aparea
    	DatabaseHandler dbHandler = new DatabaseHandler();								//Creez un obiect de tip DatabaseHandler, care raspunde de citirea datelor din fisier
		ResultSet resSet = dbHandler.getTurn();											//Primesc toate datele din baza de date
    	
		ArrayList<Turn> turns = new ArrayList<Turn>();									//Creez un ArrayList de tip Turn
		
		while(resSet.next()) {															//Convertesc din ResultSet(resSet) in ArrayList<Turn> (turns)
			turns.add(new Turn(Integer.parseInt(resSet.getString(Const.TURN_ID)), 
									Integer.parseInt(resSet.getString(Const.TURN_H)), 
									Integer.parseInt(resSet.getString(Const.TURN_W)), 
									Integer.parseInt(resSet.getString(Const.TURN_L))
							  )
					);
		}
    	
    	return turns;																	//Returnez tabloul turns
    }
    
    ArrayList<Turn> readFromBdDESC() throws ClassNotFoundException, SQLException{		//Pentru a evita oprirea aplicatiei, capturez erorile ce pot aparea
    	DatabaseHandler dbHandler = new DatabaseHandler();								//Creez un obiect de tip DatabaseHandler, care raspunde de citirea datelor din fisier
		ResultSet resSet = dbHandler.getTurnSorted();									//Primesc toate datele din baza de date in ordine descrescatoare
		
		ArrayList<Turn> turns = new ArrayList<Turn>();									//Creez un ArrayList de tip Turn
		
		while(resSet.next()) {															//Convertesc din ResultSet(resSet) in ArrayList<Turn> (turns)
			turns.add(new Turn(Integer.parseInt(resSet.getString(Const.TURN_ID)), 
									Integer.parseInt(resSet.getString(Const.TURN_H)), 
									Integer.parseInt(resSet.getString(Const.TURN_W)), 
									Integer.parseInt(resSet.getString(Const.TURN_L))
							  )
					);
		}
    	
    	return turns;																	//Returnez tabloul turns
    }
    
    void updateMaxime() {
    	if(m == "max") {												//Verific starea meniului
    		String[] max = maxime();									//Primesc valorile maxime din functia maxime()
    		
    		labelHeightMax.setText(max[0]);								//Setez valorile maxime
    		labelWidthMax.setText(max[1]);
    		labelLongMax.setText(max[2]);
    		volumMax.setText(max[3]);
    		
    		inaltimeaMaxLabel.setText("Inaltimea max");					//Setez inscriptiile pentru fiecare label
    		lungimeaMaxLabel.setText("Lungimea max");
    		latimeaMaxLabel.setText("Latimea max");
    		labelVolumMax.setText("Volumul max");
		}
		else if(m == "min") {											//Verific starea meniului
    		String[] min = minime();									//Primesc valorile maxime din functia minime()
    		
    		labelHeightMax.setText(min[0]);								//Setez valorile maxime
    		labelWidthMax.setText(min[1]);
    		labelLongMax.setText(min[2]);
    		volumMax.setText(min[3]);
    		
    		inaltimeaMaxLabel.setText("Inaltimea min");					//Setez inscriptiile pentru fiecare label
    		lungimeaMaxLabel.setText("Lungimea min");
    		latimeaMaxLabel.setText("Latimea min");
    		labelVolumMax.setText("Volumul min");
		}
    }
    
    String[] maxime() {
    	
    	try {
			ArrayList<Turn> data = readFromBd();				//Incerc sa citesc toate datele din baza de date
			
			int maxH = data.get(0).getH();						//Setez valorile initiale pentru fiecare variabila
			int maxW = data.get(0).getW();
			int maxL = data.get(0).getL();
			int maxV = data.get(0).getH() * data.get(0).getW() * data.get(0).getL();
			
			for(int i = 1; i < data.size(); i++) {				//Determin valorile maxime reale
				if(maxH < data.get(i).getH())
					maxH = data.get(i).getH();
				if(maxW < data.get(i).getW())
					maxW = data.get(i).getW();
				if(maxL < data.get(i).getL())
					maxL = data.get(i).getL();	
				if(maxV < data.get(i).getH() * data.get(i).getW() * data.get(i).getL())
					maxV = data.get(i).getH() * data.get(i).getW() * data.get(i).getL();
			}
			
			String[] res = {Integer.toString(maxH), Integer.toString(maxW), Integer.toString(maxL), Integer.toString(maxV)}; //Adaug toate datele intr=un tablou
			return res;											//Returne tabloul
			
		} catch (ClassNotFoundException | SQLException e) { 	//In caz de esec
			e.printStackTrace();}								//Se va afisa sursa problemei
    	
    	return null;											//Si se va returna null
    }
    
    String[] minime() {
    	
    	try {
			ArrayList<Turn> data = readFromBd();				//Incerc sa citesc toate datele din baza de date
			
			int minH = data.get(0).getH();						//Setez valorile initiale pentru fiecare variabila
			int minW = data.get(0).getW();
			int minL = data.get(0).getL();
			int minV = data.get(0).getH() * data.get(0).getW() * data.get(0).getL();
			
			for(int i = 1; i < data.size(); i++) {				//Determin valorile minime reale
				if(minH > data.get(i).getH())
					minH = data.get(i).getH();
				if(minW > data.get(i).getW())
					minW = data.get(i).getW();
				if(minL > data.get(i).getL())
					minL = data.get(i).getL();
				if(minV > data.get(i).getH() * data.get(i).getW() * data.get(i).getL())
					minV = data.get(i).getH() * data.get(i).getW() * data.get(i).getL();
			}
			
			String[] res = {Integer.toString(minH), Integer.toString(minW), Integer.toString(minL), Integer.toString(minV)};  //Adaug toate datele intr=un tablou
			return res;											//Returne tabloul
			
		} catch (ClassNotFoundException | SQLException e) { 	//In caz de esec
			e.printStackTrace();}								//Se va afisa sursa problemei
    	
    	return null;											//Si se va returna null
    }
    
    void update(Turn t) {										//Primesc ca parametru un obiect de tip Turn
    	labelId.setText(Integer.toString(t.getId()));			//Si setez casetelor de tip label valorile citite din obiect
    	labelHeight.setText(Integer.toString(t.getH()));
    	labelWidth.setText(Integer.toString(t.getW()));
    	labelLong.setText(Integer.toString(t.getL()));
    	labelVolum.setText(Integer.toString(t.volume()));
    	
    }
    
   
    
    boolean isValidInput() {									//Verific daca fiecare valoare a fost introdusa corect
		if(isInteger(height.getText()) && isInteger(width.getText()) && isInteger(long_.getText()))
			return true;
    	return false;
    }

	public boolean isInteger(String str) {						//Verific daca valorile contine date si sunt alfa-numerice
		if(str.trim().isEmpty())
			return false;
		
	    for (int i = 0; i < str.length(); i++) {
	        if(!Character.isDigit(str.charAt(i))) {
	            return false;
	        } 
	    }
	    return true;
	}

}