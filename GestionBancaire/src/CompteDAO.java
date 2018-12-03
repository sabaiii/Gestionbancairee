import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class CompteDAO {
	protected  Connection con;
	public CompteDAO(){
		con=Connexion.instanciation();
	}
	
	
	public Compte Select(int code) throws SQLException{
		Statement st=con.createStatement(); 
		String req="SELECT * FROM compte where code="+code;
		ResultSet rs=st.executeQuery(req);
		Compte t=null;
		while(rs.next()){
			if(rs.getString("tauxInteret")==null)
				t=new CompteCourant(rs.getString("nom"), rs.getInt("solde"), (double)rs.getInt("decouvertAutorisé"),rs.getInt("code"),rs.getInt("cin"));
			else
				t=new CompteEpargne(rs.getString("nom"), rs.getInt("solde"),(double) rs.getInt("tauxInteret"),rs.getInt("code"),rs.getInt("cin"));
		}
	   return t;
	}
	
	public   ArrayList<Compte> SelectAll() throws SQLException{
		ArrayList<Compte> tr=new ArrayList<>();
		Statement st=con.createStatement(); 
		String req="SELECT * FROM compte";
		ResultSet rs=st.executeQuery(req);
		
		while(rs.next()){
			if(rs.getString("tauxInteret")==null)
				tr.add(new CompteCourant(rs.getString("nom"), rs.getInt("solde"), (double)rs.getInt("decouvertAutorisé"),rs.getInt("code"),rs.getInt("cin")));
				
			else
				tr.add(new CompteEpargne(rs.getString("nom"), rs.getInt("solde"), (double)rs.getInt("tauxInteret"),rs.getInt("code"),rs.getInt("cin")));
				
			
		}
		return tr;
		
	}
	public   ArrayList<Compte> SelectAll(int cin) throws SQLException{
		ArrayList<Compte> tr=new ArrayList<>();
		Statement st=con.createStatement(); 
		String req="SELECT * FROM compte where cin="+cin;
		ResultSet rs=st.executeQuery(req);
		
		while(rs.next()){
			if(rs.getString("tauxInteret")==null)
				tr.add(new CompteCourant(rs.getString("nom"), rs.getInt("solde"), (double)rs.getInt("decouvertAutorisé"),rs.getInt("code"),rs.getInt("cin")));
				
			else
				tr.add(new CompteEpargne(rs.getString("nom"), rs.getInt("solde"), (double)rs.getInt("tauxInteret"),rs.getInt("code"),rs.getInt("cin")));
				
			
		}
		return tr;
		
	}
	
	
	public void deleted(int code) throws SQLException{
		Statement sc=con.createStatement();
		String req="DELETE FROM compte where code="+code;
		sc.execute(req); 
	}
	
	
		

}
