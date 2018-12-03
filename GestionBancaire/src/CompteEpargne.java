import java.sql.SQLException;

public  class CompteEpargne  extends Compte implements Comparable<CompteEpargne>{
	
	
	private double tauxInteret;

	
	public double getTaux(){
		return tauxInteret;
	}
	
	
	
	public CompteEpargne(String nom, double solde, int cin, double tauxInteret) {
		super(nom, solde, cin);
		this.tauxInteret = tauxInteret;
	}



	public CompteEpargne(String nom, double solde, double tauxInteret,int code,int cin) {
		super(nom, solde);
		this.code=code;
		this.cin=cin;
		this.tauxInteret = tauxInteret;
	}



	public CompteEpargne(String nom, double solde, double t) {
		super(nom, solde);
		this.tauxInteret = t;
	}
	
	public void déposer(double d){
		

			try {
				new CompteEpargneDAO().deleted(this.code);
				this.solde+=d+d*tauxInteret;
				new CompteEpargneDAO().inserted(this);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void retirer(double d){
		
			
			try {
				new CompteCourantDAO().deleted(this.code);
				this.solde-=d;
				new CompteEpargneDAO().inserted(this);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	

	public String toString(){
		return "[ num du compte ==> "+this.code+" son nom ==> "+this.nom+" et son solde [ "+(int)this.solde+" ] - le taux d'interet est => "+this.tauxInteret+" ]";
	}

	@Override
	public int compareTo(CompteEpargne o) {
		// TODO Auto-generated method stub
		return (int) (this.solde -o.solde);
	}

	
	
	
}
