
import java.sql.SQLException;
import java.util.ArrayList;

public class Administrateur {
	
	private final String password="1234go";
	
	private ArrayList<Employe> employees=new ArrayList<>();
	private ArrayList<AgenceBancaire> agences=new ArrayList<>();
	
	public ArrayList<Employe> getEmployees(){
		return employees;
	}
	public String getPassword(){
		return password;
	}
	public ArrayList<AgenceBancaire> getAgences(){
		return agences;
	}
	
	public void addemployeSiege(String nom,String pass,int cin) throws SQLException{
		Employe e=new EmployeSiege(nom, pass,cin,0);
		employees.add(e);	
	}
	public void addEmployeAgence(String nom, String pass,int cin) throws SQLException{
		Employe  e=new  EmployeAgence(nom, pass,cin,0);
		employees.add(e);
	}
	
	public void remove(int cin){
		for(Employe e:employees){
			if(e.getCin()==cin){
				employees.remove(e);
				break;
			}
		}
	}
		
	public void addAgence(String adresse, String ville, int code, String nom){
		this.agences.add(new AgenceBancaire(adresse, ville, code, nom));
	}
	
	public void removeAgence(int code){
		for(AgenceBancaire ag:agences){
			if(ag.getCode()==code)
				agences.remove(ag);
		}
	}
	 
	public void modifierAgence(int code){
		for(AgenceBancaire ag:agences){
			if(ag.getCode()==code)
				agences.remove(ag);
		}
	}
	
	public AgenceBancaire verification(int code){
		
		for(AgenceBancaire ag:agences){
			if(ag.getCode()==code){
				return ag;
			}
		}
		return null;
	}
	
	
	public Administrateur initilaze() throws SQLException{
		Administrateur a=new Administrateur();
		
		a.agences=new AgenceBancaireDAO().SelectAll();
		a.employees=new EmployeDAO().SelectAll();
		
		for(AgenceBancaire ag:a.agences){
			ag.getClients().addAll(0,new ClientDAO().SelectAll(ag.getCode()));
				for(Client c:ag.getClients()){
					c.getComptes().addAll(0, new CompteCourantDAO().SelectAll(c.getCin()));
					for(Compte cc:c.getComptes()){
						cc.getTransaction().addAll(0, new TransactionDAO().SelectAll(cc.getCode()));
					}
				}
		}
		return a;
	}

	
		
}
