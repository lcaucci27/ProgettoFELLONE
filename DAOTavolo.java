package database2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOTavolo {
	//Attributi Tavolo
	private int numero; //Chiave primaria
	private int num_Max_Posti;
	
	//Associazione
	private ArrayList<DAOOrdine> ordini ;
	
	
	
	//Costruttori
	
	//Costruttore di default
	public DAOTavolo() {
		super();
	}
	//Costruttore con Carica da DB
	public DAOTavolo(int numero) {
		this.setNumero(numero);
		caricaDaDB();
	}
	
	public void caricaDaDB() { 
		String query = new String("select * from tavolo where numero="+this.numero);		
	
		
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if(rs.next()) {				
				this.setNumero(rs.getInt("numero"));
				this.setNum_Max_Posti(rs.getInt("numero_max_posti"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getNum_Max_Posti() {
		return num_Max_Posti;
	}
	public void setNum_Max_Posti(int num_Max_Posti) {
		this.num_Max_Posti = num_Max_Posti;
	}  
	
	
	
	
	//Metodi per l'associazione
	public void associaOrdine(DAOOrdine x) {
		this.ordini.add(x);
	}
	
	public void rimuoviOrdine(DAOOrdine x) {
		this.ordini.remove(x);
	}
	
	//Ritorna la lista degli ordini relativi ad un tavolo
	public ArrayList<DAOOrdine> getOrdini(){
		return ordini;
	}
	
	
}
