
import java.awt.BorderLayout;
import java.awt.color.CMMException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;



public class MyTableModel extends AbstractTableModel {

	private List<Transaction> transactions;
	private String[] name={"code","type","montant","date"};
	private Connection con=Connexion.instanciation();
	
	public MyTableModel(List<Transaction> transactions) {

		this.transactions = transactions;
	}
	public MyTableModel(){};

	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return name[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return transactions.size();
	}
	


	@Override
	public Object getValueAt(int arg0, int arg1) {
		Transaction t=transactions.get(arg0);		
		switch(arg1){
		case 0:
			return t.getCode();
		case 1:
			return t.getType();
		
		case 2:
			return t.getMontant();
		
		case 3:
			return t.getDate();
		
		}

		return null;
		
	}
	
	
	
	/*public static void main(String[] args) throws SQLException {
		
		Compte c=new CompteCourant("aissa ouarhim", 20000, 0);
		c.addTransaction(1, 1000);
		c.addTransaction(1, 2000);
		c.addTransaction(0, 1000);
		for(Transaction t:c.getTransaction()){
			new TransactionDAO().inserted(t);
		}
		
		MyTableModel m=new MyTableModel(new TransactionDAO().SelectAll());
		JFrame j=new JFrame();
		j.setLocationRelativeTo(null);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setSize(600, 300);
		JTable table=new JTable(m);
		j.add(new JScrollPane(table),BorderLayout.CENTER);
		j.setVisible(true);
		 

	}*/
	
}
