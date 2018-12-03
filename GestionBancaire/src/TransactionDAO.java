import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TransactionDAO {
	private Connection con;
	
	public TransactionDAO(){
		con=Connexion.instanciation();				
	}
	
	public void inserted(Transaction t) throws SQLException{
		 SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd" ); 
		 Statement st=con.createStatement(); 	 
		 String req="INSERT INTO transaction(type,montant,date,codeCompte) value('"+t.getType()+"','"+(int)t.getMontant()+"','"+formatter.format(t.getDate())+"','"+t.getCode()+"')";
		 st.execute(req);
		 
	 }
	
	public Transaction Select(int code) throws SQLException{
		Statement st=con.createStatement(); 
		String req="SELECT * FROM transaction where codeCompte="+code;
		ResultSet rs=st.executeQuery(req);
		Transaction t=null;
		while(rs.next()){
			t=new Transaction(rs.getDate("date"), rs.getInt("type"),rs.getInt("montant"),rs.getInt("codeCompte"));
		}
	   return t;
	}
	
	public  ArrayList<Transaction> SelectAll() throws SQLException{
		ArrayList<Transaction> tr=new ArrayList<>();
		Statement st=con.createStatement(); 
		String req="SELECT * FROM transaction";
		ResultSet rs=st.executeQuery(req);
		
		while(rs.next()){
			Transaction t =new Transaction(rs.getDate("date"), rs.getInt("type"), rs.getInt("montant"),rs.getInt("codeCompte"));
			tr.add(t);
		}
		return tr;
		
	}

	public  ArrayList<Transaction> SelectAll(int code) throws SQLException{
		ArrayList<Transaction> tr=new ArrayList<>();
		Statement st=con.createStatement(); 
		String req="SELECT * FROM transaction where codeCompte="+code;
		ResultSet rs=st.executeQuery(req);
		
		while(rs.next()){
			Transaction t =new Transaction(rs.getDate("date"), rs.getInt("type"), rs.getInt("montant"),rs.getInt("codeCompte"));
			tr.add(t);
		}
		return tr;
		
	}

	
	
	
	
	public void deleted(int code) throws SQLException{
		Statement sc=con.createStatement();
		String req="DELETE FROM transaction where codeCompte="+code;
		sc.execute(req); 
	}
}
