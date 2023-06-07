package database2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOPiatto {
	//Attributi Piatto
	private int idPiatto; //Chiave primaria	
	private String nome;
	private float prezzo;
	private String portata;
	
	private DAORicetta ricetta;
	
	
	//Costruttori
	
	//Costruttore di default.
	public  DAOPiatto() {
		super(); //richiama il costruttore del genitore.
	}
	
	
	//Costruttore che prende in ingresso la PK.
	public DAOPiatto(int idPiatto){
		this.setIdPiatto(idPiatto);
		caricaDaDB();
		ricetta.caricaDaDB();
		
	}
	
	//Controllare le eccezioni.
	public void caricaDaDB() { 
	
		String query = new String("SELECT * FROM piatto WHERE idpiatto="+this.idPiatto);
		
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if(rs.next()) { //se ho un risultato, dunque ho spostato il cursore e di conseguenza ottengo una bool true
				//mi vado a prendere i dati, accedendo tramite il nome dell'attributo-colonna
				this.setNome(rs.getString("nome"));
				this.setPrezzo(rs.getFloat("prezzo"));
				this.setPortata(rs.getString("portata"));				
			}
		
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	

public void caricaRicettaDaDB() {
		
	String query = new String( );

		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if(rs.next()) {
				
				DAOPiatto piatto = new DAOPiatto();				
				piatto.setIdPiatto(rs.getInt("idpiatto"));
				piatto.setNome(rs.getString("nome"));
			//	piatto.set(rs.getFloat("prezzo")); ma in realtà non serve nel contesto dei menu
				piatto.setPortata(rs.getString("portata"));
			
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getIdPiatto() {
		return idPiatto;
	}

	public void setIdPiatto(int idPiatto) {
		this.idPiatto = idPiatto;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public String getPortata() {
		return portata;
	}
	public void setPortata(String portata) {
		this.portata = portata;
	}



	
}
