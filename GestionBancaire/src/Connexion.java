import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {

	
	private static Connection con;
	
	
	private Connexion(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionbancaire", "root", null);
		}catch(Exception e){
			System.out.println("error"+e.getMessage());
		}
	}
	
	
	public static Connection instanciation(){
		
		if(con==null){
			new Connexion();
		}
		
		return con;
	}
}
