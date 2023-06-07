package database2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOOrdine {
	//Attributi di Ordine
	private int idOrdine; //Chiave Primaria
	private int numeroCoperti;

	
	//Chiave Esterna derivante dall'associazione 1:N con la DAOTavolo.
	private DAOTavolo tavolo; //Derivante dalla traduzione dell'associazione 1:N tra Tavolo e Ordine
	
	//Traduzione associazione N:N tra Piatto e Ordine.
	private ArrayList<DAOPiatto> piattiOrdinati;
	
	//Traduzione associazione N:N tra Piatto e MenuPrezzoFisso
	private ArrayList<DAOMenuPrezzoFisso> MenuPrezzoFissoOrdinati;
	
	
	
	//Costruttori
	
	//Costruttore di default
	public DAOOrdine() {
		super();
		this.piattiOrdinati=new ArrayList<DAOPiatto>();
		this.MenuPrezzoFissoOrdinati=new ArrayList<DAOMenuPrezzoFisso>();
	}
	
	//Costruttore con chiave primaria
	public DAOOrdine(int idOrdine) {
		this.setIdOrdine(idOrdine);
		this.piattiOrdinati=new ArrayList<DAOPiatto>();
		this.MenuPrezzoFissoOrdinati=new ArrayList<DAOMenuPrezzoFisso>();
		caricaDaDB();
	}
	
	
	public void caricaDaDB() { 
		
		String query = new String("select * from ordine where idordine="+this.idOrdine);
		
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			if(rs.next()) {
				this.setNumeroCoperti(rs.getInt("numero_coperti"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	

public void caricaPiattiOrdinatiDaDB() {
		/*
		 * Devo caricare i Piatti ordinati..
		 * Per farlo faccio la query con la FK
		 */
		
	String query = new String("select * from piatto where idpiatto IN (select piatti_idpiatto from ordine_has_piatto where ordine_idordine="+this.idOrdine+")" );

	
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			while(rs.next()) {
				
				DAOPiatto piatto = new DAOPiatto();				
				piatto.setIdPiatto(rs.getInt("idpiatto"));
				piatto.setNome(rs.getString("nome"));
				piatto.setPrezzo(rs.getFloat("prezzo"));
				piatto.setPortata(rs.getString("portata"));
				this.piattiOrdinati.add(piatto);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


public void caricaMenuOrdinatiDaDB() {
	/*
	 * Devo caricare i menu ordinati..
	 * Per farlo faccio la query con la FK
	 */
	
String query = new String("select * from menu where idmenu IN (select menuprezzofisso_idMenuPrezzoFisso from ordine_has_menuprezzofisso where ordine_idordine="+this.idOrdine+")" );


	try {
		ResultSet rs = DBConnectionManager.selectQuery(query);
		
		while(rs.next()) {
			
			DAOMenuPrezzoFisso menu = new DAOMenuPrezzoFisso();				
			menu.setIdMenu(rs.getInt("idmenu"));
			menu.setNome(rs.getString("nome"));
			menu.setPrezzo(rs.getFloat("prezzo"));
		
			this.MenuPrezzoFissoOrdinati.add(menu);
		}
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	
	
	
	//SET E GET
	//Codifica Associazione tra Tavolo e Ordine 1:N
	public void associaTavolo(DAOTavolo t) {
		this.setTavolo(t);
	}

	public void rimuoviTavolo() {
		this.setTavolo(null);
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public int getNumeroCoperti() {
		return numeroCoperti;
	}

	public void setNumeroCoperti(int numeroCoperti) {
		this.numeroCoperti = numeroCoperti;
	}

	public DAOTavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(DAOTavolo tavolo) {
		this.tavolo = tavolo;
	}

	public ArrayList<DAOPiatto> getPiattiOrdinati() {
		return piattiOrdinati;
	}

	public void setPiattiOrdinati(ArrayList<DAOPiatto> piattiOrdinati) {
		this.piattiOrdinati = piattiOrdinati;
	}

	public ArrayList<DAOMenuPrezzoFisso> getMenuPrezzoFissoOrdinati() {
		return MenuPrezzoFissoOrdinati;
	}

	public void setMenuPrezzoFissoOrdinati(ArrayList<DAOMenuPrezzoFisso> menuPrezzoFissoOrdinati) {
		MenuPrezzoFissoOrdinati = menuPrezzoFissoOrdinati;
	}
	

	

}
