import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeDAO {
	private Connection con;
	
	public EmployeDAO(){
		con=Connexion.instanciation();				
	}
	
	public void inserted(Employe t) throws SQLException{
		
		 Statement st=con.createStatement(); 	 
		 String req="INSERT INTO employe(codeAgence,cin,nom,password) value('"+t.getCode()+"','"+t.getCin()+"','"+t.getNom()+"','"+t.getMotdepasse()+"')";
		 st.execute(req);
		 
	 }
	
	public Employe Select(int cin) throws SQLException{
		Statement st=con.createStatement(); 
		String req="SELECT * FROM employe where cin="+cin;
		ResultSet rs=st.executeQuery(req);
		Employe t=null;
		while(rs.next()){
			if(rs.getInt("codeAgence")==0){
				t=new EmployeSiege(rs.getString("nom"), rs.getString("password"), rs.getInt("cin"),0);
			}
			else 
				t=new EmployeAgence(rs.getString("nom"), rs.getString("password"), rs.getInt("cin"),rs.getInt("codeAgence"));
				
			
		}
	   return t;
	}
	
	public  ArrayList<Employe> SelectAll() throws SQLException{
		ArrayList<Employe> tr=new ArrayList<>();
		Statement st=con.createStatement(); 
		String req="SELECT * FROM employe";
		ResultSet rs=st.executeQuery(req);
		
		while(rs.next()){
			if(rs.getInt("codeAgence")==0){
				tr.add(new EmployeSiege(rs.getString("nom"), rs.getString("password"), rs.getInt("cin"),0));
			}
			else 
				tr.add(new EmployeAgence(rs.getString("nom"), rs.getString("password"), rs.getInt("cin"),rs.getInt("codeAgence")));
			
		}
		return tr;
		
	}
	
	
	public void deleted(int cin) throws SQLException{
		Statement sc=con.createStatement();
		String req="DELETE FROM employe where cin="+cin;
		sc.execute(req); 
	}
	
}
