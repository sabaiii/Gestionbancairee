import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

public class FenetreCompte extends JFrame {

	private int max=0;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtcourant;
	private JTextField txtepargne;
	private JTextField textField_1;

	public FenetreCompte(Client c) throws SQLException {
		ArrayList<Compte> com=new CompteCourantDAO().SelectAll(c.getCin());
		c.getComptes().addAll(0, new CompteCourantDAO().SelectAll(c.getCin()));
		for(Compte cc:c.getComptes()){
			cc.getTransaction().addAll(0, new TransactionDAO().SelectAll(cc.getCode()));
		}
		setTitle("gestion compte");
		setBounds(100, 100, 730, 482);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.PINK);
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("file");
		menuBar.add(mnFile);
		
		
		
		JMenuItem mntmRetourner = new JMenuItem("exit");
		mntmRetourner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnFile.add(mntmRetourner);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomDeClient = new JLabel("nom de client  : "+c.getNom());
		lblNomDeClient.setForeground(Color.MAGENTA);
		lblNomDeClient.setBounds(12, 13, 179, 16);
		contentPane.add(lblNomDeClient);
		
		JLabel lblNewLabel = new JLabel("son cin : "+c.getCin());
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setBounds(12, 31, 131, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("transaction");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=0;
				for(Compte cc:c.getComptes()){
					i++;
					if(cc.getCode()==Integer.parseInt(textField_1.getText())){
						try {
							new FenetreTransaction(cc).setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						break;
					}
					if(i==c.getComptes().size()){
						JOptionPane.showMessageDialog(null, "code erroné", "message", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
			}
		});
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setBounds(148, 363, 104, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnRetourner = new JButton("exit");
		btnRetourner.setForeground(Color.BLACK);
		btnRetourner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnRetourner.setBackground(Color.ORANGE);
		btnRetourner.setBounds(615, 371, 97, 25);
		contentPane.add(btnRetourner);
		
		JLabel lblAjouterCompte = new JLabel("ajouter compte");
		lblAjouterCompte.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblAjouterCompte.setBackground(Color.BLACK);
		lblAjouterCompte.setBounds(55, 71, 121, 16);
		contentPane.add(lblAjouterCompte);
		
		textField = new JTextField();
		textField.setBounds(123, 101, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		txtcourant = new JTextField();
		txtcourant.setText("(epargne)*");
		txtcourant.setToolTipText("pour un compte epargne");
		txtcourant.setColumns(10);
		txtcourant.setBounds(123, 136, 116, 22);
		contentPane.add(txtcourant);
		
		txtepargne = new JTextField();
		txtepargne.setText("(courant)*");
		txtepargne.setToolTipText("pour un compte courant");
		txtepargne.setColumns(10);
		txtepargne.setBounds(123, 171, 116, 22);
		contentPane.add(txtepargne);
		
		JLabel lblNewLabel_1 = new JLabel("solde");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(3, 100, 59, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblTauxInteret = new JLabel("taux interet");
		lblTauxInteret.setForeground(Color.BLUE);
		lblTauxInteret.setBounds(3, 138, 80, 16);
		contentPane.add(lblTauxInteret);
		
		JLabel lblNewLabel_2 = new JLabel("decouverte autoris\u00E9");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setBounds(3, 174, 121, 16);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("compte courant");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(com.size()<2){
						if(com.isEmpty()){
							c.addCompteCourant(Double.parseDouble(textField.getText()),Double.parseDouble(txtepargne.getText()));
							JOptionPane.showMessageDialog(null, "compte courant ajouter", "message", JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							if(com.get(0) instanceof CompteCourant){
								JOptionPane.showMessageDialog(null, "le client a deja un compte courant!!", "message", JOptionPane.INFORMATION_MESSAGE);
								}
							else{
								c.addCompteCourant(Double.parseDouble(textField.getText()),Double.parseDouble(txtepargne.getText()));
								JOptionPane.showMessageDialog(null, "compte courant ajouter", "message", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						}
				else{
					JOptionPane.showMessageDialog(null, "maximun deux comptes !!", "message", JOptionPane.INFORMATION_MESSAGE);
				}}
				catch(Exception e){
				}
		
		
				}
		});
		btnNewButton.setBounds(45, 206, 131, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_3 = new JButton("compte epargne");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(com.size()<2){
						if(com.isEmpty()){
							c.addCompteEpargne(Double.parseDouble(textField.getText()),Double.parseDouble(txtcourant.getText()));
							JOptionPane.showMessageDialog(null, "compte epargner ajouter", "message", JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							if(com.get(0) instanceof CompteEpargne){
								JOptionPane.showMessageDialog(null, "le client a deja un compte epargne!!", "message", JOptionPane.INFORMATION_MESSAGE);
								}
							else{
								c.addCompteEpargne(Double.parseDouble(textField.getText()),Double.parseDouble(txtcourant.getText()));
								JOptionPane.showMessageDialog(null, "compte epargner ajouter", "message", JOptionPane.INFORMATION_MESSAGE);
							}
						}}
				else{
						JOptionPane.showMessageDialog(null, "maximun deux comptes !!", "message", JOptionPane.INFORMATION_MESSAGE);
				}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.setBackground(Color.GREEN);
		btnNewButton_3.setBounds(45, 244, 131, 25);
		contentPane.add(btnNewButton_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(239, 41, 451, 277);
		contentPane.add(panel);
		
		JLabel lblListesDesComptes = new JLabel("listes des comptes ");
		lblListesDesComptes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblListesDesComptes.setForeground(Color.MAGENTA);
		lblListesDesComptes.setBounds(365, 13, 131, 16);
		contentPane.add(lblListesDesComptes);
		MyTableModel model=new MyTableModel(new CompteCourantDAO().SelectAll(c.getCin()));
		JTable tabel=new JTable(model);
		panel.add(new JScrollPane(tabel),BorderLayout.CENTER);
		
		JButton btnNewButton_4 = new JButton("actualiser");
		btnNewButton_4.setForeground(Color.BLACK);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					dispose();
					new FenetreCompte(c).setVisible(true);;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBackground(Color.CYAN);
		btnNewButton_4.setBounds(497, 363, 97, 25);
		contentPane.add(btnNewButton_4);
		
		JLabel lblCodeagence = new JLabel("codeCompte");
		lblCodeagence.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCodeagence.setForeground(Color.BLUE);
		lblCodeagence.setBounds(27, 335, 116, 16);
		contentPane.add(lblCodeagence);
		
		textField_1 = new JTextField();
		textField_1.setBounds(8, 364, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("supprimer");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=0;
				for(Compte cc:c.getComptes()){
					if(cc.getCode()==Integer.parseInt(textField_1.getText())){
						i=1;
						if(cc.getTransaction().isEmpty()){
							JOptionPane.showMessageDialog(null, "compte supprimer", "message", JOptionPane.INFORMATION_MESSAGE);
							try {
								c.getComptes().remove(cc);
								new CompteCourantDAO().deleted(cc.getCode());
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
						}
						else{
							JOptionPane.showMessageDialog(null, "on peut pas supprimer", "message", JOptionPane.INFORMATION_MESSAGE);
							break;
						}
					}
				}
				if(i==0){
					JOptionPane.showMessageDialog(null, "code erroné", "message", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setBackground(Color.PINK);
		btnNewButton_2.setBounds(257, 363, 104, 25);
		contentPane.add(btnNewButton_2);
		
		JButton btnListeTransaction = new JButton("liste transaction");
		btnListeTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
				MyTableModel_1	m = new MyTableModel_1(new TransactionDAO().SelectAll(Integer.parseInt(textField_1.getText())));
				JFrame j=new JFrame();
				j.setSize(600, 300);
				j.setTitle("liste des transaction");
				j.setLocationRelativeTo(null);
				JTable table=new JTable(m);
				j.add(new JScrollPane(table),BorderLayout.CENTER);
				j.setVisible(true);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
		});
		btnListeTransaction.setForeground(Color.BLACK);
		btnListeTransaction.setBackground(Color.MAGENTA);
		btnListeTransaction.setBounds(365, 363, 131, 25);
		contentPane.add(btnListeTransaction);
		
	}
	 class MyTableModel extends AbstractTableModel {

		private List<Compte> compte;
		private String[] name={"code","nom","cin","solde"};
		
		
		public MyTableModel(List<Compte> compte) {

			this.compte=compte;
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
			return compte.size();
		}
		


		@Override
		public Object getValueAt(int arg0, int arg1) {
			Compte t=compte.get(arg0);		
			switch(arg1){
			case 0:
				return t.getCode();
			case 1:
				return t.getNom();
			
			case 2:
				return t.getCin();
			
			case 3:
				return t.getSolde();
			
			}

			return null;
			
		}
		
	}
	 class MyTableModel_1 extends AbstractTableModel {

			private List<Transaction> transaction;
			private String[] name={"code","type","montant","date"};
			
			
			public MyTableModel_1(List<Transaction> transaction) {

				this.transaction=transaction;
			}
			public MyTableModel_1(){};

			
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
				return transaction.size();
			}
			


			@Override
			public Object getValueAt(int arg0, int arg1) {
				Transaction t=transaction.get(arg0);		
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
			
		}
	
}
