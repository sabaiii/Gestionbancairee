import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AgenceBancaireDAO {
	private Connection con;
	
	public AgenceBancaireDAO() {
		con=Connexion.instanciation();				
	}
	
	public void inserted(AgenceBancaire a) throws SQLException{
		 SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd" ); 
		 Statement st=con.createStatement(); 	 
		 String req="INSERT INTO agencebancaire(nom,code,ville,adresse,datecreation) value('"+a.getNom()+"','"+a.getCode()+"','"+a.getVille()+"','"+a.getAdresse()+"','"+formatter.format(a.getDateCreation())+"')";
		 st.execute(req);
		 
	 }
	
	public AgenceBancaire Select(int code) throws SQLException{
		Statement st=con.createStatement(); 
		String req="SELECT * FROM agencebancaire where code="+code;
		ResultSet rs=st.executeQuery(req);
		AgenceBancaire t=null;
		while(rs.next()){
			t=new AgenceBancaire(rs.getString("adresse"), rs.getString("ville"),rs.getDate("datecreation"),  rs.getInt("code"), rs.getString("nom"));
			
		}
	   return t;
	}
	
	public  ArrayList<AgenceBancaire> SelectAll() throws SQLException{
		ArrayList<AgenceBancaire> tr=new ArrayList<>();
		Statement st=con.createStatement(); 
		String req="SELECT * FROM agencebancaire";
		ResultSet rs=st.executeQuery(req);
		
		while(rs.next()){
			AgenceBancaire t=new AgenceBancaire(rs.getString("adresse"), rs.getString("ville"),rs.getDate("datecreation"), rs.getInt("code"), rs.getString("nom"));
			tr.add(t);
		}
		return tr;
		
	}
	public  ArrayList<AgenceBancaire> SelectAll(int code) throws SQLException{
		ArrayList<AgenceBancaire> tr=new ArrayList<>();
		Statement st=con.createStatement(); 
		String req="SELECT * FROM agencebancaire code="+code;
		ResultSet rs=st.executeQuery(req);
		
		while(rs.next()){
			AgenceBancaire t=new AgenceBancaire(rs.getString("adresse"), rs.getString("ville"),rs.getDate("datecreation"), rs.getInt("code"), rs.getString("nom"));
			tr.add(t);
		}
		return tr;
		
	}
	
	public void deleted(int code) throws SQLException{
		Statement sc=con.createStatement();
		String req="DELETE FROM agencebancaire where code="+code;
		sc.execute(req); 
	}

}
