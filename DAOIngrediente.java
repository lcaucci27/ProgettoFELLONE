package database2;

public class DAOIngrediente{
	private int idIngrediente;
    private String nome;
   
    
    
  //Costruttori
	
  	//Costruttore di default.
  	public  DAOIngrediente() {
  		super(); //richiama il costruttore del genitore.
  	}
  	
  	//Costruttore con chiav e primaria.
  	public DAOIngrediente(int idIngrediente) {
  		this.setIdIngrediente(idIngrediente);
  		caricaDaDB();
  	}
      
  	
  	   
    public void caricaDaDB() { 
  		
      
    	
    }

	
      
      
      
      
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(int idIngrediente) {
		this.idIngrediente = idIngrediente;
	}
      	
    
    
}
