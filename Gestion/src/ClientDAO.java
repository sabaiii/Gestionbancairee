import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientDAO {
	
private Connection con;
	
	public ClientDAO(){
		con=Connexion.instanciation();				
	}
	
	public void inserted(Client t) throws SQLException{ 
		 Statement st=con.createStatement(); 	 
		 String req="INSERT INTO client(nom,cin,codeAgence) value('"+t.getNom()+"','"+t.getCin()+"','"+t.getCode()+"')";
		 st.execute(req);
		 
	 }
	
	public Client Select(int cin) throws SQLException{
		Statement st=con.createStatement(); 
		String req="SELECT * FROM client where cin="+cin;
		ResultSet rs=st.executeQuery(req);
		Client t=null;
		while(rs.next()){
			t =new Client(rs.getInt("cin"), rs.getString("nom"),rs.getInt("codeAgence"));
		}
	   return t;
	}
	
	public  ArrayList<Client> SelectAll() throws SQLException{
		ArrayList<Client> tr=new ArrayList<>();
		Statement st=con.createStatement(); 
		String req="SELECT * FROM client";
		ResultSet rs=st.executeQuery(req);
		
		while(rs.next()){
			Client t =new Client(rs.getInt("cin"), rs.getString("nom"),rs.getInt("codeAgence"));
			tr.add(t);
		}
		return tr;
		
	}
	public  ArrayList<Client> SelectAll(int codeagence) throws SQLException{
		ArrayList<Client> tr=new ArrayList<>();
		Statement st=con.createStatement(); 
		String req="SELECT * FROM client where codeAgence="+codeagence;
		ResultSet rs=st.executeQuery(req);
		
		while(rs.next()){
			Client t =new Client(rs.getInt("cin"), rs.getString("nom"),rs.getInt("codeAgence"));
			tr.add(t);
		}
		return tr;
		
	}
	
	
	public void deleted(int cin) throws SQLException{
		Statement sc=con.createStatement();
		String req="DELETE FROM client where cin="+cin;
		sc.execute(req); 
	}
	

	
	

}
