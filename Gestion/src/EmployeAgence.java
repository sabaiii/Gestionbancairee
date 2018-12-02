import java.sql.SQLException;

public class EmployeAgence extends Employe {

	private AgenceBancaire agence;

	
	
	public AgenceBancaire getAgence() {
		return agence;
	}
	

	public EmployeAgence(String nom, String motdepasse, int cin,int code) throws SQLException {
		super(nom, motdepasse,cin);
		this.code=code;
		if(code!=0){
			this.agence=new AgenceBancaireDAO().Select(code);
		}
	}


	



	
	
}
