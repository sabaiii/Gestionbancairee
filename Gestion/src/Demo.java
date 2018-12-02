import java.lang.Thread.State;
import java.sql.SQLException;
import java.util.Scanner;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

public class Demo {
	
	public static Client client(String nom,AgenceBancaire g){
		for(Client c:g.getClients()){
			if(c.getNom().equals(nom))
				return c;
		}
		return null;
	}
	public static void menuAgence(AgenceBancaire a){

		System.out.println("\n\t\t"+a.getNom()+" code:"+a.getCode());
		System.out.println("\t\t°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
		System.out.println("\t\t°°              MENU_AGENCE                °°");
		System.out.println("\t\t°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
		System.out.println("\t\t°°       1-Ajouter un client.              °°");
		System.out.println("\t\t°°       2-Supprimer un client.            °°");
		System.out.println("\t\t°°       3-Chercher un client.             °°");
		System.out.println("\t\t°°       4-Afficher la liste des clients.  °°");
		System.out.println("\t\t°°       5-Faire une transaction.          °°");
		System.out.println("\t\t°°       0-Quitter.                        °°");
		System.out.println("\t\t°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
	}
	public static void menuClient(String nom,int cin){
		System.out.println("\n\t\tuser: "+nom+" cin: "+cin);
		System.out.println("\t\t°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
		System.out.println("\t\t°°             MENU_CLIENT                 °°");
		System.out.println("\t\t°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
		System.out.println("\t\t°°       1-Créer un compte.                °°");
		System.out.println("\t\t°°       2-afficher le relevé.             °°");
		System.out.println("\t\t°°       3-Afficher la liste des comptes.  °°");
		System.out.println("\t\t°°       4-Faire une transaction.          °°");
		System.out.println("\t\t°°       0-Retour.                         °°");
		System.out.println("\t\t°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
		
	}
	public static void menuCompte(String nom,int code){
		System.out.println("\n\t\tuser: "+nom+" code compte: "+code);
		System.out.println("\t\t°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
		System.out.println("\t\t°°             MENU_COMPTE                 °°");
		System.out.println("\t\t°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
		System.out.println("\t\t°°             1-Retirer.                  °°");
		System.out.println("\t\t°°             2-Déposer.                  °°");
		System.out.println("\t\t°°             0-Retour.                   °°");
		System.out.println("\t\t°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
		
	}
	public static AgenceBancaire createAgence(){
		String nomAgence;
		int code;
		
		Scanner sc=new  Scanner(System.in);
		System.out.println("\t\tEntrez le nom de l'agence ==>");
		nomAgence=sc.nextLine();
		System.out.println("\t\tEntrez son code ==>");
		code=sc.nextInt();
		
		AgenceBancaire g=new  AgenceBancaire(code, nomAgence);
		return g;
	}
	public static void transaction_client(String nom,AgenceBancaire a) throws SQLException{

		if(client(nom,a).getComptes().isEmpty()){
			System.out.println("\t\tLe client n'a pas de compte");}
		else{
		char choix3=' ';
		int code;
		Compte co=null;
		Scanner sc5=new Scanner(System.in);
		System.out.println("\t\tSVP entrez le code de votre compte :");
		code=sc5.nextInt();
		for(Compte c:client(nom,a).getComptes()){
			if(c.getCode()==code){
				co=c;
			}	
		}
		if(co==null){
			System.out.println("\t\tLe code ne correspond à aucun compte.");
			
		}
		else{
		while(choix3!='0'){
			do{
				Scanner s=new Scanner(System.in);
				menuCompte(client(nom, a).getNom(),co.code);
				choix3=s.nextLine().charAt(0);
			}while(choix3!='1' && choix3!='2' && choix3!='0');
			
			switch(choix3){
			case '1':{
				double solde;
				Scanner sc2=new  Scanner(System.in);
				System.out.println("\t\tEntrez le montant à retirer==>");
				solde=sc2.nextDouble();
				co.addTransaction(1,solde);
				co.retirer(solde);
				
				break;
			}
			case '2':{
				double solde;
				Scanner sc2=new  Scanner(System.in);
				System.out.println("\t\tEntrez le montant à déposer ==>");
				solde=sc2.nextDouble();
				co.addTransaction(0, solde);
				co.déposer(solde);
				
				break;
			}
			}
		}}}
	}
	public static void transaction_agence(AgenceBancaire a) throws SQLException{
		
		String nom1;
		int cin1;
		int code;
		Scanner s=new Scanner(System.in);
		System.out.println("\t\tEntrez le nom de client:");
		nom1=s.nextLine();
		System.out.println("\t\tEntrez son CIN:");
		cin1=s.nextInt();
		if(a.getClients().isEmpty())
			System.out.println("\t\tLe client n'existe pas.");
		else{
		if(client(nom1,a).getComptes().isEmpty()){
			System.out.println("\t\tVeuillez ouvrir d'abord un compte dans notre agence");
			
		}
		else{
			if(client(nom1,a).getCin()==cin1)
			{   
				Scanner s2=new Scanner(System.in);
				System.out.println("\t\tSVP entrez le code de votre compte");
				code=s2.nextInt();
				char choix3=' ';
				int compteur=0;
				for(Compte c:client(nom1,a).getComptes()){
					
					if(c.getCode()==code){
						compteur++;
						while(choix3!='0'){
							do{
								Scanner s3=new Scanner(System.in);
								menuCompte(client(nom1, a).getNom(),c.code);
								choix3=s3.nextLine().charAt(0);
							}while(choix3!='1' && choix3!='2' && choix3!='0');
							
							switch(choix3){
							case '1':{
								double solde;
								Scanner s5=new  Scanner(System.in);
								System.out.println("\t\tEntrez le solde à retirer==>");
								solde=s5.nextDouble();
								c.addTransaction(1, solde);
								c.retirer(solde);
								
								break;
							}
							case '2':{
								double solde;
								Scanner s5=new  Scanner(System.in);
								System.out.println("\t\tEntrez le solde à déposer ==>");
								solde=s5.nextDouble();
								c.addTransaction(0, solde);
								c.déposer(solde);
								break;
							}
							case '0':{
								System.out.println("\t\tOn revient au MENU_AGENCE");
								break;
							}
							}
						}
						break;
					}
					
				}
				if(compteur==0){
					System.out.println("\t\tLe code ne correspond à aucun compte.");
				}
			
			}
		}
		}
	}
	public static void chercher(AgenceBancaire a) throws SQLException{

		String nom1;
		int cin1;
		Scanner sc4=new  Scanner(System.in);
		System.out.println("\t\tEntrez le nom de client :");
		nom1=sc4.nextLine();
		System.out.println("\t\tEntrez son cin :");
		cin1=sc4.nextInt();
		if(a.getClients().isEmpty()){
			System.out.println("\t\tLes données son incorrectes");
		}
		else{
		if(client(nom1, a).getCin()==cin1 && client(nom1, a).getNom().equals(nom1)){
			char choix2=' ';
			while(choix2!='0'){
				do{
					Scanner sc=new Scanner(System.in);
					menuClient(nom1, cin1);
					choix2=sc.nextLine().charAt(0);
				}while(choix2!='1' && choix2!='2' && choix2!='3' && choix2!='0' && choix2!='4' );
			
			
				switch(choix2){
					case '1':{
						double solde;
						Scanner sc2=new  Scanner(System.in);
						System.out.println("\t\tEntrez le solde pour alimenter le compte ==>");
						solde=sc2.nextDouble();
						client(nom1, a).addCompte(solde);
						}
						break;
				
					case '2':{
						if(client(nom1, a).getComptes().isEmpty())
							System.out.println("\t\tCréer d'abord un compte");
						else
							client(nom1, a).printReleve(nom1);
						break;
					}
					
					case '3':{
						client(nom1, a).printCompte(nom1);
						break;
						}
					case '4':{
						transaction_client(nom1, a);
						break;
					}
					case '0':{
						System.out.println("\t\t on revient au MENU_AGENCE");
						break;
					}
						
					}
		}	
	}
		
		else
			System.out.println("\t\tLes données son incorrectes");
		}
		
	}
	public static void ajouter_client(AgenceBancaire a) throws SQLException{
		
		char choix1=' ';
		String nom = null;
		int cin;
		

		Scanner sc1=new  Scanner(System.in);
		System.out.println("\t\tEntrez le nom de client :");
		nom=sc1.nextLine();

		System.out.println("\t\tEntrez son cin :");
		cin=sc1.nextInt();
		a.addClient(cin, nom);
		while(choix1!='0'){
			do{
				Scanner sc=new Scanner(System.in);
				menuClient(nom, cin);
				choix1=sc.nextLine().charAt(0);
			}while(choix1!='1' && choix1!='2' && choix1!='3' && choix1!='0' && choix1!='4' );
		
		
			switch(choix1){
				case '1':{
					double solde;
					Scanner sc2=new  Scanner(System.in);
					System.out.println("\t\tEntrez le solde pour alimenter le compte ==>");
					solde=sc2.nextDouble();
					client(nom, a).addCompte(solde);
					}
					break;
			
				case '2':{
					if(client(nom, a).getComptes().isEmpty())
						System.out.println("\t\tCréer d'abord un compte");
					else
						client(nom, a).printReleve(nom);
					break;
				}
				case '3':{
					client(nom, a).printCompte(nom);
					break;
					}
				case '4':{
					transaction_client(nom, a);
					break;
				}
				case '0':{
					System.out.println("\t\tOn revient au MENU_CLIENT");
					break;
				}
					
				}//fin switch(choix1)
			
			}
	}
	public static void menu(AgenceBancaire a) throws SQLException{
		char choix=' ';
		if(a!=null){
			
			Scanner sc=new Scanner(System.in);
			while(choix!='0'){
			do{
				menuAgence(a);
				choix=sc.nextLine().charAt(0);
			}while(choix!='1' && choix!='2' && choix!='3' && choix!='0' && choix!='4' && choix!='5' );
			
			switch(choix){
				case '1':{
						ajouter_client(a);
						break;
				}
				case '2':{
					remove(a);
					break;
				}
				case '3':{
					chercher(a);
					break;
				}
				case '4':{
					a.printclients();
					break;
					}
				case '5':{
					transaction_agence(a);
					break;
				}
				case '0':{
					System.out.println("\t\t          °°°°°°°°°Au revoir°°°°°°°°°");
					System.exit(0);
				}
				
			
			}
				
			}
				
		}
			
	}
	public static void remove(AgenceBancaire a){
		int cin;
		int i;
		Scanner sc=new Scanner(System.in);
		System.out.println("\t\tEntrez le CIN du client à supprimer.");
		cin=sc.nextInt();
		for(i=0;i<a.getClients().size();i++){
			if(a.getClients().get(i).getCin()==cin)
				a.removeClient(cin);
		}
		if(i==a.getClients().size())
			System.out.println("\t\tCIN incorrectes");
			
		
	}
	
	/*public static void main(String[] args) throws SQLException {
		menu(createAgence());	
	}*/
}
