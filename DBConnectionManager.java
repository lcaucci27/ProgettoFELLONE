package database2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionManager {
	//Inizializzazione del driver JDBC
	public static String driver = "com.mysql.cj.jdbc.Driver";
	//verrà richiamato il metodo Class.forName all'interno del getConnection
	
	
//Per stabilire connessione al database, prima di eseguire una query	
	public static String url = "jdbc:mysql://localhost:3306/"; //Inserire
	public static String dbName = ""; //Inserire
	public static String userName = ""; //Inserire 
	public static String password = ""; //Inserire
//La Connection verrà restituita dal metodo getConnection	
	
	
	
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Connection conn = null;		
		Class.forName(driver); //com.mysql.cj.jdbc.Driver
		
		conn = DriverManager.getConnection(url+dbName,userName,password);
		
		return conn;
	}
	
	
	
	
	public static void closeConnection(Connection c) throws SQLException {
		
		c.close();
	}
	
	
	
	public static ResultSet selectQuery(String query) throws ClassNotFoundException, SQLException {
		//Mi connetto al DB
		Connection conn = getConnection();
		//Utilizzo l'oggetto Statement per eseguire query sul DB connesso nello step precedente		
		Statement statment = conn.createStatement();
		
		ResultSet ret = statment.executeQuery(query); 
		//Contiene i risultati di una query, esso può essere visto come una collezione di record.
		
		return ret;
	}

	
	
	
	
	public static int updateQuery(String query) throws ClassNotFoundException, SQLException {
		
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		int ret = statement.executeUpdate(query);
		conn.close();
		return ret;
	}
	
	
	
	public static Integer updateQueryReturnGeneratedKey(String query) throws ClassNotFoundException, SQLException {
		Integer ret = null;
		
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		
		ResultSet rs = statement.getGeneratedKeys();
		if (rs.next()){
		    ret = rs.getInt(1);
		}
		
		conn.close();
		
		return ret;
	}
}