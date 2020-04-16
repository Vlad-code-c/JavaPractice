package controlers.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler extends Configs {			//Mostenesc clasa Confings pentru a-i putea folosi campurile	
	Connection dbConnection;							//Creez o variabila de tip Connection				
		
	
	public Connection getDBConnection() throws ClassNotFoundException, SQLException { //Capturez orice eroare posibila
		//Compun String-ul conectiunii de forma "jdbc:mysql:/127.0.0.1:3306/turnuri
		String connectionString = "jdbc:mysql://" + dbHost + ":" +
				dbPort + "/" + dbName;
		
		Class.forName("com.mysql.cj.jdbc.Driver"); 	//Gasesc clasa Driver
		
		dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);	//Creez conexiunea cu baza de date
		
		return dbConnection;						//Returnez conexiunea
		
	}
	
	public void addTurn(Turn turn) throws ClassNotFoundException, SQLException {
		//Creez comanda sql pentru a insera datele
		String insert = "INSERT INTO " + Const.TURN_TABLE + "(" + Const.TURN_H + ", " +
						Const.TURN_W + ", " + Const.TURN_L + ") VALUES(?, ?, ?)";
		
		PreparedStatement prSt = getDBConnection().prepareStatement(insert);	//Folosesc clasa PerparedStatement pentru a utiliza conexiunea cu baza de date
		prSt.setInt(1, turn.getH());											//Setez valorile indicate cu ? in variabila insert
		prSt.setInt(2, turn.getW());
		prSt.setInt(3, turn.getL());
		
		prSt.executeUpdate();				//Trimit comanda catre executie spre serverul mySQL
	}
	
	public ResultSet getTurn() throws ClassNotFoundException, SQLException {
		ResultSet resSet = null;				//Creez o variabila de tip ResultSet
		
		//Compun sirul pentru a extrage toate datele din tabel
		String select = "SELECT * FROM " + Const.TURN_TABLE;	
		
		//Utilizez PreparedStatement pentru a prepara comanda select
		PreparedStatement prSt = getDBConnection().prepareStatement(select);
		
		//Trimit comanda catre executie, si atribui rezultatul primit variabilei resSet
		resSet = prSt.executeQuery(); 
		
		return resSet;  //returnez variabila resSet, indiferent daca contine sau nu date
	}
	
	public ResultSet getTurnSorted() throws ClassNotFoundException, SQLException {
		ResultSet resSet = null;				//Creez o variabila de tip ResultSet
		
		//Compun sirul pentru a extrage toate datele din tabel ordonate dupa volum (care e calculat direct in instructiune) in ordine Descrescatoare
		String select = "SELECT * FROM " + Const.TURN_TABLE + " ORDER BY(" + Const.TURN_H + "*" + Const.TURN_W + "*" + Const.TURN_L + ") DESC";
		
		//Utilizez PreparedStatement pentru a prepara comanda select
		PreparedStatement prSt = getDBConnection().prepareStatement(select);
		
		//Trimit comanda catre executie, si atribui rezultatul primit variabilei resSet
		resSet = prSt.executeQuery();
		
		return resSet;  //returnez variabila resSet, indiferent daca contine sau nu date
	}
	
	public void deleteData(int id) throws ClassNotFoundException, SQLException {
		//Compun comanda delete, care va sterge cate un tuplu dupa id-ul indicat
		String delete = "DELETE FROM " + Const.TURN_TABLE + " WHERE " + Const.TURN_ID + " =?";

		//Utilizez PreparedStatement pentru a prepara comanda delete
		PreparedStatement prSt = getDBConnection().prepareStatement(delete);
		prSt.setInt(1, id);					//Atribui parametrului '?' valoare id
		
		//Trimit comanda catre executie, si atribui rezultatul primit variabilei resSet
		prSt.executeUpdate();
	}
	
	public ResultSet patrate() throws ClassNotFoundException, SQLException {
		ResultSet resSet = null;

		//Selectez toate inregistrarile in care inaltimea este egala cu latimea, creand astfel un patrat
		String select = "SELECT * FROM " + Const.TURN_TABLE + " WHERE " + Const.TURN_H + " = " + Const.TURN_W;
		
		//Utilizez PreparedStatement pentru a prepara comanda select
		PreparedStatement prSt = getDBConnection().prepareStatement(select);
		
		//Trimit comanda catre executie, si atribui rezultatul primit variabilei resSet
		resSet = prSt.executeQuery();
		
		//Returnez datele primite
		return resSet;
	}
	
	public void schimba(String what) throws SQLException, ClassNotFoundException {
		switch(what) {					//Determin care coloane trebuie interschimbate
			case "HW":					//Pentru a schimba datele intre ele folosesc o coloana aux, care, datorita faptului ca baza de date nu va contine un nr de date mare, exista tot timpul
				getDBConnection().prepareStatement("UPDATE " + Const.TURN_TABLE + " SET aux = " + Const.TURN_W + ";").executeUpdate();  
				getDBConnection().prepareStatement("UPDATE " + Const.TURN_TABLE + " SET " + Const.TURN_W + " = " + Const.TURN_H + ";").executeUpdate(); 
				getDBConnection().prepareStatement("UPDATE " + Const.TURN_TABLE + " SET " + Const.TURN_H + " = aux;\n").executeUpdate();
				break;
			case "HL":
				getDBConnection().prepareStatement("UPDATE " + Const.TURN_TABLE + " SET aux = " + Const.TURN_L + ";").executeUpdate(); 
				getDBConnection().prepareStatement("UPDATE " + Const.TURN_TABLE + " SET " + Const.TURN_L + " = " + Const.TURN_H + ";").executeUpdate(); 
				getDBConnection().prepareStatement("UPDATE " + Const.TURN_TABLE + " SET " + Const.TURN_H + " = aux;").executeUpdate();
				break;
			case "WL":
				getDBConnection().prepareStatement("UPDATE " + Const.TURN_TABLE + " SET aux = " + Const.TURN_L + ";").executeUpdate();
				getDBConnection().prepareStatement("UPDATE " + Const.TURN_TABLE + " SET " + Const.TURN_L + " = " + Const.TURN_W + ";").executeUpdate(); 
				getDBConnection().prepareStatement("UPDATE " + Const.TURN_TABLE + " SET " + Const.TURN_W + " = aux;").executeUpdate();
				break;
		}
		
		
	}
	
}
