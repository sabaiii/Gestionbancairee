import java.sql.SQLException;

public class CompteCourant extends Compte implements Comparable<CompteCourant>{
	
	
    
	private double decouvertAutorisé;
	
	public double getDecouverte(){
		return decouvertAutorisé;
	}
	

	
	
	public CompteCourant(String nom, double solde, int cin, double decouvertAutorisé) {
		super(nom, solde, cin);
		this.decouvertAutorisé = decouvertAutorisé;
	}



	public CompteCourant(String nom, double solde, double decouvertAutorisé,int code,int cin) {
		super(nom, solde);
		this.code=code;
		this.cin=cin;
		this.decouvertAutorisé = decouvertAutorisé;
	}



	public CompteCourant(String nom, double solde, double d) {
		super(nom, solde);
		this.decouvertAutorisé = d;
	}
	
	public void retirer(double d){
		if(solde+decouvertAutorisé>d){
			try {
				new CompteCourantDAO().deleted(this.code);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.solde-=d;
			
			try {
				new CompteCourantDAO().insertEtd(this);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		
		
		
						
	}
public void déposer(double d){
		
		
			
			try {
				new CompteCourantDAO().deleted(this.code);
				this.solde+=d;
				new CompteCourantDAO().insertEtd(this);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
	
	
	public String toString(){
		return "[ num du compte ==> "+this.code+" son nom ==> "+this.nom+" et son solde[ "+(int)this.solde+" ] - la decouvert autorisé est  "+this.decouvertAutorisé+" ]" ;
	}

	@Override
	public int compareTo(CompteCourant o) {
		// TODO Auto-generated method stub
		return (int) (this.solde -o.solde);
	}
	
	
	
	

}
