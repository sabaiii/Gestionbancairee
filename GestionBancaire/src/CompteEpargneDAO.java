import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CompteEpargneDAO extends CompteDAO{

	

	public CompteEpargneDAO() {
		super();
	}
	
	
	public void inserted(CompteEpargne e) throws SQLException{
		 Statement st=con.createStatement(); 
		 String req="INSERT INTO compte(code,nom,solde,decouvertAutorisé,tauxInteret,cin) value('"+e.getCode()+"','"+e.getNom()+"','"+(int)e.getSolde()+"',NULL,'"+(int)e.getTaux()+"','"+e.getCin()+"')";
		 st.execute(req); 
	 }
	
	
}
