import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CompteCourantDAO extends CompteDAO {

	public CompteCourantDAO() {
		super();
	}
	
	
	public void insertEtd(CompteCourant e) throws SQLException{
		 Statement st=con.createStatement(); 	 
		 String req="INSERT INTO compte(code,nom,solde,decouvertAutorisé,tauxInteret,cin) value('"+e.getCode()+"','"+e.getNom()+"','"+e.getSolde()+"','"+e.getDecouverte()+"',NULL,'"+e.getCin()+"')";
		 st.execute(req); 
	 }
	
	
	
	
	

}
