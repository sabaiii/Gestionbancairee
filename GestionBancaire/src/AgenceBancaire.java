

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;  

public class AgenceBancaire {
	
	
	private String adresse;
	private String ville;	
	private Date dateCreation;
	private int code;
	private String nom;
	private ArrayList<Client> clients=new ArrayList<>();
	public AgenceBancaire(){}
	public AgenceBancaire(String adresse, String ville, Date dateCreation, int code, String nom) {
		super();
		this.adresse = adresse;
		this.ville = ville;
		this.dateCreation = dateCreation;
		this.code = code;
		this.nom = nom;
	
	}

	public AgenceBancaire(int code, String nom) {
		super();
		this.code = code;
		this.nom = nom;
	}

	public AgenceBancaire(String adresse, String ville, int code, String nom) {
		super();
		this.adresse = adresse;
		this.ville = ville;
		this.dateCreation = new Date();
		this.code = code;
		this.nom = nom;
		
	}

	//les  getters
	public String getAdresse() {
		return adresse;
	}

	public String getVille() {
		return ville;
	}

	public Date getDateCreation() {
		return dateCreation;
	}
	public int getCode() {
			return code;
		}
	public String getNom() {
			return nom;
		}
	public ArrayList<Client> getClients(){
			return clients;
		}
	//les methodes
	public void addClient(int cin,String nom){
		//on verifi d'abord si le client existe deja dans l'agence
		Iterator< Client> it=clients.iterator();
    	while(it.hasNext()){
    		
    		if( it.next().getCin()==cin){
    			System.out.println("le client existe deja dans l'agence");
    			break;
    		}
    			}
		
		Client c=new Client(cin,nom);
		clients.add(c);
			
	}
	
	
	public void addclients(int cin,String nom) throws SQLException{
		Client c=new Client(cin, nom, this.code);
		new ClientDAO().inserted(c);
		clients.add(c);
	}
	
	
    public void removeClient(int cin){

    	Iterator< Client> it=this.clients.iterator();
    	
    	while(it.hasNext()){
    		
    		if( it.next().getCin()==cin)
    			it.remove();}
    	
    }
	public void printclients(){
		int i;
		Collections.sort(clients);
		System.out.println("\t                   |la liste des clients| ");
		for(i=0;i<this.clients.size();i++){
			System.out.println("\t\t        [ nom==>"+this.clients.get(i).getNom()+" son cin ==> "+this.clients.get(i).getCin()+" ]");
		}
    }
    
	
	
    
   
  
}