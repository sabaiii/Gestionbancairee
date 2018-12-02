import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
	
	
	private Date date;
	private int type;
	private double montant;
	private int code;
	
	
	public Transaction(Date date, int type, double montant, int code) {
		super();
		this.date = date;
		this.type = type;
		this.montant = montant;
		this.code = code;
	}

	public Transaction(Date date, int type, double montant) {
		
		this.date = date;
		this.type = type;
		this.montant = montant;
	}

	public String toString(){
		return "- date: "+this.date.toString()+"- type:"+this.type+"- montant:"+this.montant;
	}

	public Date getDate() {
		return date;
	}

	public int getType() {
		return type;
	}
	public int getCode(){
		return code;
	}

	public double getMontant() {
		return montant;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	
	
	
	
}
