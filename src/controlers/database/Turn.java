package controlers.database;

public class Turn {										//Creez o clasa simpla Turn	
	private int id;										//Declar e dimensiuni si id-ul acestora
	private int h;
	private int w;
	private int l;
	
	public Turn(String h, String w, String l) {			//Declar un constructor ce primeste valori de tip String
		this.h = Integer.parseInt(h);					//Le converteste in Integer si atribuie rezultatul valiabilelor corespunzatoare
		this.w = Integer.parseInt(w);
		this.l = Integer.parseInt(l);
	}
	
	public Turn(int id, int h, int w, int l) {			//Declar al constructor ce primeste valori de tip int, inclusiv si int
		this.id = id;									//Si le atribuie campurilor corespunzatoare
		this.h = h;
		this.w = w;
		this.l = l;
	}
	
	public Turn(int h, int w, int l) {					//Declar al constructor ce primeste valori de tip int
		this.h = h;										//Si le atribuie campurilor corespunzatoare
		this.w = w;
		this.l = l;
	}
	
	public int volume() {								//Determin si returnez volumul
		return this.h * this.w * this.l;
	}
	
	public int getId() {return id;}						//Returnez variabilele existente
	public int getH() {return h;}
	public int getW() {return w;}
	public int getL() {return l;}

	
}
