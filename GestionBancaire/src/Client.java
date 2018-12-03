import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

public class Client implements Comparable<Client>,Serializable{
	
	private int cin;
	private String nom;
	private int code;
	private ArrayList<Compte> comptes=new ArrayList<>() ;
	
	
	public Client(int cin, String nom, int code) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.code = code;
		
	}


	//constructeur
	public Client(int cin, String nom) {
		
		this.cin = cin;
		this.nom = nom;
		
	}
	
	
	//getter et setter
	public int getCin() {
		return cin;
	}
	public int getCode(){
		return code;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public  ArrayList<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(ArrayList<Compte> comptes) {

		this.comptes = comptes;
	}
	
	
	//les methodes
	
	public void printCompte(String nom){
		if(this.comptes.isEmpty())
			System.out.println("\t\t[user:"+nom+" n'a pas de compte]");
		
		else{
		for(Compte p: comptes){
			
			if(p.nom.equals(nom))
				System.out.println(p.toString());
		}}
		
	}

	public void  printReleve(String nom) {
		
		for(Compte p: comptes){
			
				if(p.nom.equals(nom))
					p.afficher();
		}
	}
	public void addCompte(double solde){
		char choix=' ';
		Scanner sc=new Scanner(System.in);
		do{
			
			System.out.println("\t\t 1-un compte courant.\n\t\t 2-un compte épargne.");
			choix=sc.nextLine().charAt(0);
			//sc.nextLine();
		}while(choix!='1' && choix!='2');
		
		switch(choix){
		
		case '1':{
			double d;
			Scanner sc2=new  Scanner(System.in);
			System.out.println("\t\t SVP entrez la decouvert autorisé :");
			d=sc2.nextDouble();
			CompteCourant c=new CompteCourant(this.nom, solde, d);
			comptes.add(c);
			break;
		}
		case '2':{
			double d;
			Scanner sc2=new  Scanner(System.in);
			System.out.println("\t\t SVP entrez le taux d'intéret :");
			d=sc2.nextDouble();
			CompteEpargne c=new CompteEpargne(this.nom, solde, d);
			comptes.add(c);
			break;
		}
		}
		//sc.close();
	}
	public double somme(Client cl) {
		// TODO Auto-generated method stub
		double som=0;
		for(Compte c:comptes){
			if(cl.nom.equals(c.nom)){
				som+=c.solde;
			}
			
		}	
		
		return som;
	}
	
	public String toString(){
		return "[ nom==>"+this.nom+" cin==> "+this.cin +"]";
	}

	@Override
	public int compareTo(Client o) {
		// TODO Auto-generated method stub
		return (int)(this.somme(this)-o.somme(o));
	}
	
	public CompteCourant addCompteCourant(double solde,double decouverte) throws SQLException{
		
		CompteCourant c=new CompteCourant(this.nom, solde, this.cin, decouverte);
		comptes.add(c);
		new CompteCourantDAO().insertEtd(c);
		return c;
	}
	
	public CompteEpargne addCompteEpargne(double solde,double taux) throws SQLException{
		
		
		CompteEpargne c=new CompteEpargne(this.nom, solde, this.cin, taux);
		comptes.add(c);
		new CompteEpargneDAO().inserted(c);
		return c;
	}
	
	
	

}
