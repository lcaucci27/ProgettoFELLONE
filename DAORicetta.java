package database2;

import java.sql.ResultSet;
import java.util.HashMap;

public class DAORicetta{ //Classe associativa
	//Attributi Ricetta
	private DAOPiatto piatto; //CHIAVE PRIMARIA
	private HashMap <DAOIngrediente, Integer> ingredientiConQuantita;
		
	/*
	 * PASTA AL SUGO
	 * Key: spaghetti		Value: 400g
	 * Key: pomodoro		Value: 0.3 L
	 */
	
	
	//Costruttori
	public DAORicetta() {
		super();
	}

	public DAORicetta(DAOPiatto piatto) {
		caricaDaDB();
		
	}
	
	public void caricaDaDB() {
		
	}
	
	//getter e setter
	public DAOPiatto getPiatto() {
		return piatto;
	}

	public void setPiatto(DAOPiatto piatto) {
		this.piatto = piatto;
	}

	public HashMap <DAOIngrediente, Integer> getIngredientiConQuantita() {
		return ingredientiConQuantita;
	}

	public void setIngredientiConQuantita(HashMap <DAOIngrediente, Integer> ingredientiConQuantita) {
		this.ingredientiConQuantita = ingredientiConQuantita;
	}

	
	


}