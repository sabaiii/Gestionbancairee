import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

public abstract  class Compte implements Serializable {
	
	protected  int code;// commun a tout les objets de cette classe .
	protected  String nom;
	protected  double solde;
	protected int cin;
	protected ArrayList<Transaction> transaction=new ArrayList<>();
	

	public Compte(String nom, double solde, int cin) {
		Random r = new Random();
		this.code = r.nextInt(10000000);
		this.nom = nom;
		this.solde = solde;
		this.cin = cin;
	}
	
	
	public Compte(String nom, double solde) {
		Random r = new Random();
		this.code = r.nextInt(10000000);
		
		this.nom = nom;
		this.solde = solde;
		
	}
	
	public void déposer(double d){
		
		if(this.transaction.isEmpty())
			System.out.println("\t\tTransaction echoué");
		
		else{
			
			
			try {
				new CompteCourantDAO().deleted(this.code);
				this.solde+=d;
				new CompteCourantDAO().insertEtd((CompteCourant) this);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\t\tTransaction reussi");}
		
		
	}
	
	public void retirer(double d){
		if(this.transaction.isEmpty())
			System.out.println("\t\tTransaction echoué");
		
		else{
			
			try {
				new CompteCourantDAO().deleted(this.code);
				this.solde-=d;
				new CompteEpargneDAO().inserted((CompteEpargne) this);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\t\tTransaction reussi");}
		
	
			
	}
	
	public abstract String toString();
	
	public  int getCode() {
		return code;
	}

	public  void setCode(int code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}
	public int getCin(){
		return cin;
	}

	public ArrayList<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(ArrayList<Transaction> transaction) {
		this.transaction = transaction;
	}

	public void addTransaction(int type,double montant) throws SQLException{
		 
		 Date d=new Date();
		 //ON CREE UN OBJET TRANSACTION 
		 Transaction t=new Transaction(d, type, montant,this.code);
		 //ON AJOUTE NOTRE OBJET A LA COLLECTION TRANSACTION
		 new TransactionDAO().inserted(t);
		 this.transaction.add(t);
		 
	 }
	 
	public void afficher(){
		
		Iterator<Transaction> t=this.transaction.iterator(); 
		
		if(this.transaction.isEmpty())
			System.out.println("\t\t[code compte:"+this.code+"[user:"+this.nom+"] ==> Aucune transaction ]");
		else{
			while(t.hasNext())//TANT QUE LA COLLECTION N'est pas vide 
				 System.out.println("[ code compte:"+this.code+t.next().toString()+" ]");
		}	
	 }
	 
	
}
