import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeSiege extends Employe {
	

	private ArrayList<AgenceBancaire> agences;

	
	

	public ArrayList<AgenceBancaire> getagences() {
		return agences;
	}

	public EmployeSiege(String nom, String motdepasse,int cin,int code) throws SQLException {
		super(nom, motdepasse,cin);
		this.code=code;
		if(code==0){
			this.agences=new AgenceBancaireDAO().SelectAll();
		}
	}

}
