
public abstract class Employe {
	
	
	protected String nom;
	protected int CIN;
	protected int code;
	protected String motdepasse;
	
	
	public String getNom() {
		return nom;
	}
	public String getMotdepasse() {
		return motdepasse;
	}
	public int getCin(){
		return CIN;
	}
	public int getCode(){
		return code;
	}
	
	
	public Employe(String nom, String motdepasse,int cin) {
		
		this.nom = nom;
		this.motdepasse = motdepasse;
		this.CIN=cin;
	}
	
	
	

}
