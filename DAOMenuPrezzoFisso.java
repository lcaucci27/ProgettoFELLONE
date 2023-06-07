package database2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOMenuPrezzoFisso {
	//Attributi MenuPrezzoFisso
	private int idMenu; //Chiave Primaria
	private String nome;
	private float prezzo;

	
	//Traduzione associazione N:N tra MenuPrezzoFisso e Piatto.
	private ArrayList<DAOPiatto> piatti;
	
	
	
	
	//Costruttori
	
	//Costruttore di default
	public DAOMenuPrezzoFisso(){
		super();
		this.piatti=new ArrayList<DAOPiatto>();
	}
	
	//Costruttore che prende in ingresso la PK
	public DAOMenuPrezzoFisso(int idMenuPrezzoFisso) {
		this.setIdMenu(idMenuPrezzoFisso);
		this.piatti=new ArrayList<DAOPiatto>();
		caricaDaDB();
	}
	
	
	
	//Da controllare le eccezioni
	public void caricaDaDB() {
		
	String query = new String("select * from menuprezzofisso where idmenu="+this.idMenu);
	
	try {
		ResultSet rs = DBConnectionManager.selectQuery(query);
		if(rs.next()) { //Se ho un risultato
			//Prendo i dati accedendo tramite il nome dell'attributo-colonna.
			this.setIdMenu(rs.getInt("idmenu"));
			this.setNome(rs.getString("nome"));
			this.setPrezzo(rs.getFloat("prezzo"));
		}
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
	
public void caricaPiattiMenuDaDB() {
		/*
		 * Devo caricare i PIATTI (che a loro volta sono un oggetto
		 * contenuti all'interno di un menù.
		 * Per farlo faccio la query con la FK
		 */
		
	String query = new String("select * from piatti where idpiatto IN (select piatti_idCorsi from menuprezzofisso_has_piatto where menuprezzofisso_idMenuPrezzoFisso="+this.idMenu+")" );
/*La sottoquery interna select piatto_idpiatto from MenuPrezzoFisso_has_Piatto where menuprezzofisso_idMenuPrezzoFisso=\'"+this.idMenu+"\' 
 * seleziona gli ID dei piatti dalla tabella di associazione MenuPrezzoFisso_has_Piatto. La condizione where menuprezzofisso_idMenuPrezzoFisso=\'"+this.idMenu+"\' 
 * filtra solo le righe che corrispondono all'ID del menù specificato.
 * La clausola IN nella query esterna select * from piatti where idpiatto IN (...) confronta l'attributo idpiatto della tabella piatti con i risultati della sottoquery interna. 
 * In altre parole, restituisce tutti i piatti della tabella piatti il cui ID si trova nell'elenco degli ID dei piatti associati al menù specificato.
*/
	
		try {
			ResultSet rs = DBConnectionManager.selectQuery(query);
			
			while(rs.next()) {
				
				DAOPiatto piatto = new DAOPiatto();				
				piatto.setIdPiatto(rs.getInt("idpiatto"));
				piatto.setNome(rs.getString("nome"));
			//	piatto.set(rs.getFloat("prezzo")); ma in realtà non serve nel contesto dei menu
				piatto.setPortata(rs.getString("portata"));
				this.piatti.add(piatto);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//getter e Settare
	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public ArrayList<DAOPiatto> getPiatti() {
		return piatti;
	}

	public void setPiatti(ArrayList<DAOPiatto> piatti) {
		this.piatti = piatti;
	}
	
	


}
