package controlers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import controlers.database.Turn;

public class FileWriteRead {
	static void fileWrite(String path, ArrayList<Turn> text) throws IOException {	//Capturez orice eroare legata de scrierea sau citirea datelor

		List<String> lines = new ArrayList<String>(); 								//Declar o Lista<String> si ii atribui o copie a clasei ArrayList<>
		
		for(int i = 0; i < text.size(); i++){										//Adaug din ArrayList-ul text in Lista lines datele
			lines.add(text.get(i).getH() + " " + text.get(i).getW() + " " + text.get(i).getL());
		}
		
		
		Path file = Paths.get(path);												//Creez variabila de tip Path ce va contine adresa relativa a fisierului
		Files.write(file, lines, StandardCharsets.UTF_8);							//Inscriu datele in fisier
		
		File file_ = new File (path);												//Creez o variabila de tip File
		Desktop desktop = Desktop.getDesktop();										//Si deschid aplicatia Explorer.exe la adresa necesara
		desktop.open(file_);
	}
}
