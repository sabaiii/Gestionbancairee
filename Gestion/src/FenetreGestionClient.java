import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class FenetreGestionClient extends JFrame {

	private int i=0;
	private JLabel lblNewLabel_5 = new JLabel("");
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private DefaultTableModel model_1=new DefaultTableModel();
	private DefaultTableModel model;
	
	
	private JTable table_1;
	private JTextField txtCode_1;
	private JTextField textField_3;
	private JTextField textField_4;
	
	
	
	public FenetreGestionClient(Employe e) throws SQLException {
		if(e.getCode()!=0){
			((EmployeAgence)e).getAgence().getClients().addAll(0, new ClientDAO().SelectAll(((EmployeAgence)e).getAgence().getCode()));
			}
		else{
			((EmployeSiege)e).getagences().addAll(0, new AgenceBancaireDAO().SelectAll());
			for(AgenceBancaire a:((EmployeSiege)e).getagences()){
				a.getClients().addAll(0, new ClientDAO().SelectAll(a.getCode()));
				for(Client c:a.getClients()){
					c.getComptes().addAll(0, new CompteCourantDAO().SelectAll(c.getCin()));
					for(Compte cc:c.getComptes() ){
						cc.getTransaction().addAll(0, new TransactionDAO().SelectAll(cc.getCode()));
					}
				}
			}
		}
		
		
		setBackground(Color.GRAY);
		setTitle("gestion clients");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 648);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.PINK);
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("file");
		menuBar.add(mnFile);
		
	
		
		JMenuItem mntmExit = new JMenuItem("quitter");
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setForeground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("nom complet");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(178, 45, 92, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("cin");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(178, 77, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField("nom du client");
		textField.setBounds(330, 42, 155, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField("son cin");
		textField_1.setColumns(10);
		textField_1.setBounds(330, 74, 155, 22);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("ajouter un client =>");
		lblNewLabel_2.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_2.setForeground(Color.MAGENTA);
		lblNewLabel_2.setBounds(15, 67, 166, 35);
		contentPane.add(lblNewLabel_2);
		
		JButton btnValider = new JButton("valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(e.getCode()!=0){
					int i=0;
					for(Client c:((EmployeAgence)e).getAgence().getClients()){
						i++;
						if(textField.equals("nom du client") || textField_1.getText().equals("son cin")){
							JOptionPane.showMessageDialog(null, "info erroné", "message", JOptionPane.INFORMATION_MESSAGE);;
							break;
						}
						if(c.getCin()==Integer.parseInt(textField_1.getText())){
							JOptionPane.showMessageDialog(null, "le client existe déja", "message", JOptionPane.INFORMATION_MESSAGE);;
							break;
						}
						
					}
					if(i==((EmployeAgence)e).getAgence().getClients().size()){
						try {
							((EmployeAgence)e).getAgence().addclients(Integer.parseInt(textField_1.getText()), textField.getText());
							JOptionPane.showMessageDialog(null, "le client ajouter", "message", JOptionPane.INFORMATION_MESSAGE);;
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					}
				else{
					int i=0;
					for(AgenceBancaire a: ((EmployeSiege)e).getagences()){
						if(a.getCode()==Integer.parseInt(txtCode_1.getText())){
								for(Client c :a.getClients()){
			
									if(c.getCin()==Integer.parseInt(textField_1.getText())){
										i=1;
										lblNewLabel_5.setForeground(Color.red);
										
										break;
									}
									
								}
								if(i==0){
									try {
										a.addclients(Integer.parseInt(textField_1.getText()), textField.getText());
										model.addRow(new Object[]{Integer.parseInt(textField_1.getText()), textField.getText(),Integer.parseInt(txtCode_1.getText())});
									} catch (NumberFormatException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									JOptionPane.showMessageDialog(null, "le client ajouter", "message", JOptionPane.INFORMATION_MESSAGE);
									break;
								}
						}
						
						
						
					}
					
					
					
					
				}
				
			}
		});
		btnValider.setBackground(Color.GREEN);
		btnValider.setBounds(552, 73, 97, 25);
		contentPane.add(btnValider);
		
		JButton btnNewButton = new JButton("supprimer le client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=0;
				if(e.getCode()!=0){
					for(Client c:((EmployeAgence)e).getAgence().getClients()){
						i++;
							if(c.getCin()==Integer.parseInt(textField_3.getText())){
								try {
									c.getComptes().addAll(0,new CompteCourantDAO().SelectAll(c.getCin()));
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								if(c.getComptes().isEmpty()){
									
								((EmployeAgence)e).getAgence().removeClient(c.getCin());
								try {
									new ClientDAO().deleted(c.getCin());
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "le client a ete supprimer", "message", JOptionPane.INFORMATION_MESSAGE);}
								else{
									JOptionPane.showMessageDialog(null, "le client ne peut pas etre supprimer", "message", JOptionPane.INFORMATION_MESSAGE);
								}
								break;
								
							}
							if(i==((EmployeAgence)e).getAgence().getClients().size()){
							JOptionPane.showMessageDialog(null, "le client n'exixte pas", "message", JOptionPane.INFORMATION_MESSAGE);}
						
					}
				}
				else{
					
					for(AgenceBancaire a1:((EmployeSiege)e).getagences()){
						
						for(Client c:a1.getClients()){
							
								if(c.getCin()==Integer.parseInt(textField_3.getText())){
									i=1;
									try {
										c.getComptes().addAll(0,new CompteCourantDAO().SelectAll(c.getCin()));
									} catch (SQLException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
									if(c.getComptes().isEmpty()){
										
									a1.removeClient(c.getCin());
									try {
										new ClientDAO().deleted(c.getCin());
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									JOptionPane.showMessageDialog(null, "le client a ete supprimer", "message", JOptionPane.INFORMATION_MESSAGE);
									break;}
									else{
										JOptionPane.showMessageDialog(null, "le client ne peut pas etre supprimer", "message", JOptionPane.INFORMATION_MESSAGE);
										break;
									}
									
									
								}
								
							
						}
						
					}
					if(i==0){
						JOptionPane.showMessageDialog(null, "le client n'exixte pas", "message", JOptionPane.INFORMATION_MESSAGE);}
							}
		}});
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.setBounds(330, 215, 151, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("chercher le client");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				
				if(e.getCode()!=0){
					int i=0;
					for(Client c:((EmployeAgence)e).getAgence().getClients()){
						i++;
						if(c.getCin()==Integer.parseInt(textField_3.getText())){
							try {
								new FenetreCompte(c).setVisible(true);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;}
						if(i==((EmployeAgence)e).getAgence().getClients().size()){
							JOptionPane.showMessageDialog(null, "le client n'exixte pas", "message", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
					}
				else{
					int i=0;
					for(AgenceBancaire a1:((EmployeSiege)e).getagences()){
						
						for(Client c:a1.getClients()){
							
							if(c.getCin()==Integer.parseInt(textField_3.getText())){
								i=1;
								try {
									new FenetreCompte(c).setVisible(true);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								break;}
							
							
						}
						
					}
					if(i==0){
						JOptionPane.showMessageDialog(null, "le client n'exixte pas", "message", JOptionPane.INFORMATION_MESSAGE);
					}
					
					
				
				
				
					}}});
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.setBounds(330, 175, 151, 25);
		contentPane.add(btnNewButton_1);
		
		JLabel lblListesDesClients = new JLabel("listes des clients :");
		lblListesDesClients.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 16));
		lblListesDesClients.setForeground(Color.MAGENTA);
		lblListesDesClients.setBounds(509, 242, 140, 20);
		contentPane.add(lblListesDesClients);
		
		JPanel panel = new JPanel();
		panel.setBounds(469, 275, 473, 263);
		contentPane.add(panel);
		if(e.getCode()!=0){
			MyTableModel_1 model_2=new MyTableModel_1(new ClientDAO().SelectAll(((EmployeAgence)e).getAgence().getCode()));
			JTable table_2=new JTable(model_2);
			panel.add(new JScrollPane(table_2),BorderLayout.CENTER);}
		else{
			model=new DefaultTableModel();
			model.addColumn("cin");
			model.addColumn("nom");
			model.addColumn("codeAgence");
			JTable table_2=new JTable(model);
			panel.add(new JScrollPane(table_2),BorderLayout.CENTER);
			
				for(Client c:new ClientDAO().SelectAll()){
					model.addRow(new Object[]{c.getCin(),c.getNom(),c.getCode()});
				}
			
			
			
		}
	

		
		JButton btnFaireUneTransaction = new JButton("faire une transaction");
		btnFaireUneTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				
				if(e.getCode()!=0)
					try {
						{
							int i=0;
							for(Client c:((EmployeAgence)e).getAgence().getClients()){
								
								try {
									c.getComptes().addAll(0, new CompteCourantDAO().SelectAll(c.getCin()));
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								for(Compte cc:c.getComptes()){
									
									if(cc.getCode()==Integer.parseInt(textField_4.getText())){
										i=1;
										try {
											new FenetreTransaction(cc).setVisible(true);
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										break;
										}
										}
								if(i==1){
									break;
								}
								
							}
							if(i==0){
								JOptionPane.showMessageDialog(null, "code erroné", "message", JOptionPane.INFORMATION_MESSAGE);
							}
							}
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				else{int i=0;
					for(AgenceBancaire a: ((EmployeSiege)e).getagences()){
						
						for(Client c:a.getClients()){
							
							try {
								c.getComptes().addAll(0, new CompteCourantDAO().SelectAll(c.getCin()));
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							for(Compte cc:c.getComptes()){
								
								if(cc.getCode()==Integer.parseInt(textField_4.getText())){
									i=1;
									try {
										new FenetreTransaction(cc).setVisible(true);
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									break;
									}
									}
							if(i==1){
								break;
							}
							
						}
					}
					if(i==0){
						JOptionPane.showMessageDialog(null, "code erroné", "message", JOptionPane.INFORMATION_MESSAGE);
					}
				}
		}});
		
		btnFaireUneTransaction.setBackground(Color.LIGHT_GRAY);
		btnFaireUneTransaction.setBounds(751, 179, 161, 25);
		contentPane.add(btnFaireUneTransaction);
		
		
		
		JLabel lblLi = new JLabel("listes des agences");
		lblLi.setForeground(Color.MAGENTA);
		lblLi.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 16));
		lblLi.setBounds(15, 240, 151, 25);
		contentPane.add(lblLi);
		
		JButton btnNewButton_2 = new JButton("quitter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new Fenetre().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBackground(Color.PINK);
		btnNewButton_2.setBounds(821, 537, 97, 25);
		contentPane.add(btnNewButton_2);
		
		JLabel lblInfoEmploye = new JLabel("info employe =>");
		lblInfoEmploye.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblInfoEmploye.setForeground(Color.BLUE);
		lblInfoEmploye.setBounds(204, 0, 108, 16);
		contentPane.add(lblInfoEmploye);
		
		JLabel lblNewLabel_3 = new JLabel(" name : "+e.getNom());
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_3.setForeground(Color.MAGENTA);
		lblNewLabel_3.setBounds(340, 0, 184, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("cin : "+e.getCin());
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_4.setForeground(Color.MAGENTA);
		lblNewLabel_4.setBounds(536, 0, 118, 16);
		contentPane.add(lblNewLabel_4);
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		
		
		lblNewLabel_5.setBounds(639, 77, 234, 16);
		contentPane.add(lblNewLabel_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 275, 453, 263);
		contentPane.add(panel_1);
		panel_1.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_7 = new JLabel("cin");
		lblNewLabel_7.setForeground(Color.BLACK);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_7.setBounds(178, 182, 56, 16);
		contentPane.add(lblNewLabel_7);
		
		textField_3 = new JTextField();
		textField_3.setBounds(202, 180, 116, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("codeAGENCE");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setBounds(515, 183, 85, 16);
		contentPane.add(lblNewLabel_8);
		
		textField_4 = new JTextField();
		textField_4.setBounds(602, 180, 116, 22);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblOperation = new JLabel("operation =>");
		lblOperation.setFont(new Font("Segoe Print", Font.BOLD | Font.ITALIC, 16));
		lblOperation.setForeground(Color.MAGENTA);
		lblOperation.setBounds(12, 174, 130, 25);
		contentPane.add(lblOperation);
		if(e.getCode()!=0){
		JButton btnActualiser = new JButton("actualiser");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dispose();
					new FenetreGestionClient(e).setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnActualiser.setBackground(Color.CYAN);
		btnActualiser.setBounds(725, 537, 97, 25);
		contentPane.add(btnActualiser);}
		if(e.getCode()==0){
		
		
		JLabel lblNewLabel_6 = new JLabel("code agence");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_6.setForeground(Color.BLUE);
		lblNewLabel_6.setBounds(178, 112, 92, 16);
		contentPane.add(lblNewLabel_6);
		
		txtCode_1 = new JTextField();
		txtCode_1.setText("code*");
		txtCode_1.setBounds(330, 109, 155, 22);
		contentPane.add(txtCode_1);
		txtCode_1.setColumns(10);}
		
		
		if(e.getCode()==0){
		table_1 = new JTable(new MyTableModel( new AgenceBancaireDAO().SelectAll()));
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(0, 270, 485, 268);
		panel_1.add(scrollPane_1);}
		else{
			table_1 = new JTable();
			model_1.addColumn("code");
			model_1.addColumn("nom");
			model_1.addColumn("adresse");
			model_1.addColumn("ville");
			model_1.addColumn("datecreation");
			model_1.addRow(new Object[]{((EmployeAgence)e).getAgence().getCode(),((EmployeAgence)e).getAgence().getNom(),((EmployeAgence)e).getAgence().getAdresse(),((EmployeAgence)e).getAgence().getVille(),((EmployeAgence)e).getAgence().getDateCreation()});

			table_1.setModel(model_1);
			
			JScrollPane scrollPane_1 = new JScrollPane(table_1);
			scrollPane_1.setEnabled(false);
			scrollPane_1.setBounds(0, 270, 485, 268);
			panel_1.add(scrollPane_1);
			
		}
		
	}
	class Authentification extends JDialog {
		
		private JPanel contentPanel=new JPanel();
		private JPasswordField passwordField;
		private JLabel label = new JLabel("");
		
		public Authentification(String s){
			Administrateur admi=new Administrateur();
			setTitle("verification");
			setSize(316,150);
			setLocationRelativeTo(null);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(new GridLayout(2,1));
			{
				JPanel panel = new JPanel();
				contentPanel.add(panel);
				panel.setLayout(new GridLayout(1,2));
				{
					JPanel panel_1 = new JPanel();
					panel.add(panel_1);
					{
						JLabel lblPassword = new JLabel(s);
						lblPassword.setForeground(Color.BLUE);
						panel_1.add(lblPassword);
					}
				}
				{
					JPanel panel_1 = new JPanel();
					panel.add(panel_1);
					{
						passwordField = new JPasswordField();
						passwordField.setColumns(10);
						panel_1.add(passwordField);
					}
				}
			}
			{
				JPanel panel = new JPanel();
				contentPanel.add(panel);
				{
					
					panel.add(label);
				}
			}
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("OK");
					okButton.setActionCommand("OK");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(i==0){
								dispose();
								//new FenetreTransaction().setVisible(true);
								i=0;}
							if(i==1){
								dispose();
								
								i=0;
							}
							if(i==2){
								
								i=0;
							}
							if(i==3){
								dispose();
								i=0;
							}
						}
					});
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.setActionCommand("Cancel");
					cancelButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							dispose();
							
						}
					});
					buttonPane.add(cancelButton);
				}
			}
			setVisible(true);
		}}
		
		
	
	
	class MyTableModel extends AbstractTableModel {

		private List<AgenceBancaire> agenceBancaire;
		private String[] name={"code","nom","adresse","ville","datecreation"};
		
		
		public MyTableModel(List<AgenceBancaire> agenceBancaire) {

			this.agenceBancaire = agenceBancaire;
		}
		public MyTableModel(){};

		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 5;
		}
		
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			return name[column];
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return agenceBancaire.size();
		}
		


		@Override
		public Object getValueAt(int arg0, int arg1) {
			AgenceBancaire t=agenceBancaire.get(arg0);		
			switch(arg1){
			case 0:
				return t.getCode();
			case 1:
				return t.getNom();
			
			case 2:
				return t.getAdresse();
			
			case 3:
				return t.getVille();
			case 4:
				return t.getDateCreation();
			
			}

			return null;
			
		}
}
	class MyTableModel_1 extends AbstractTableModel {

		private List<Client> client;
		private String[] name={"cin","nom","codeAgence"};
		
		
		public MyTableModel_1(List<Client> client) {

			this.client=client;
		}
		public MyTableModel_1(){};

		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 3;
		}
		
		public String getColumnName(int column) {
			// TODO Auto-generated method stub
			return name[column];
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return client.size();
		}
		


		@Override
		public Object getValueAt(int arg0, int arg1) {
			Client t=client.get(arg0);		
			switch(arg1){
			case 0:
				return t.getCin();
			case 1:
				return t.getNom();
			case 2:
				return t.getCode();
			
			
			}

			return null;
			
		}
}
}
