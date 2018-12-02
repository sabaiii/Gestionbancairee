import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.DefaultTableModel;



public class FenetreGestionEmployee extends JFrame {

	
	private JPasswordField passwordField;
	private JTextField textField1;
	private JTextField textField1_3;
	private JTextField textField_1;
	private JTable table;
	private DefaultTableModel model=new DefaultTableModel();
	
	
	

	public FenetreGestionEmployee() throws SQLException{
		this.setContentPane(new JPanel());
		setTitle("gestion employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.PINK);
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmAffectation = new JMenuItem("liste agences");
		mnFile.add(mntmAffectation);
		mntmAffectation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
				try {
					new FenetreGestionAgence().setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		JMenuItem mntmNewMenuItem = new JMenuItem("RETOUR ");
		mnFile.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			
			@Override
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
		
		JMenuItem mntmExit = new JMenuItem("EXIT");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		getContentPane().setLayout(null);
		
		JLabel lblAjouterEmployee = new JLabel("ajouter employee");
		lblAjouterEmployee.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		lblAjouterEmployee.setBounds(40, 13, 203, 52);
		getContentPane().add(lblAjouterEmployee);
		
		JLabel lblNewLabel = new JLabel("type :");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(12, 96, 56, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("name :");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(12, 139, 56, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("cin :");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setBounds(12, 181, 56, 16);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("password :");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(12, 218, 73, 24);
		getContentPane().add(lblNewLabel_3);
		JLabel lblNewLabel_5 = new JLabel("codeAgence :");
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setBounds(12, 260, 80, 24);
		getContentPane().add(lblNewLabel_5);
		
		String[] tab = {"employee_siege","employee_agence"};
		JComboBox comboBox = new JComboBox(tab);
		comboBox.setEditable(true);
		comboBox.setSelectedIndex(1);
		comboBox.setMaximumRowCount(2);
		comboBox.setBounds(95, 93, 130, 22);
		getContentPane().add(comboBox);
		
		textField1_3 = new JTextField();
		textField1_3.setBounds(95, 260, 116, 22);
		textField1_3.setText("(siege ==0)");
		getContentPane().add(textField1_3);
		textField1_3.setColumns(10);
		
		textField1 = new JTextField();
		textField1.setBounds(95, 136, 116, 22);
		getContentPane().add(textField1);
		textField1.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(95, 178, 116, 22);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(96, 219, 116, 22);
		getContentPane().add(passwordField);
		
		JButton btnValider = new JButton("valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s=comboBox.getSelectedItem().toString();
				if(Integer.parseInt(textField1_3.getText())==0){
					try {
						new EmployeDAO().inserted(new EmployeSiege(textField1.getText(), passwordField.getText(), Integer.parseInt(textField_1.getText()), 0));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					try {
						new EmployeDAO().inserted(new EmployeAgence(textField1.getText(), passwordField.getText(), Integer.parseInt(textField_1.getText()), Integer.parseInt(textField1_3.getText())));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				model.addRow(new Object[]{textField1.getText(),textField_1.getText(),comboBox.getSelectedItem(),passwordField.getText()});
				
			}
		});
		btnValider.setBackground(Color.GREEN);
		btnValider.setForeground(Color.BLACK);
		btnValider.setBounds(64, 290, 97, 25);
		getContentPane().add(btnValider);
		
		JButton btnNewButton = new JButton("remove_employee");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				new Authentification();
			}
		});
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(198, 345, 149, 25);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_5 = new JButton("actualiser");
		btnNewButton_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				try {
					new FenetreGestionEmployee().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_5.setBackground(Color.CYAN);
		btnNewButton_5.setBounds(20, 345, 149, 25);
		getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_1 = new JButton("listes agences");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				try {
					new FenetreGestionAgence().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setBounds(413, 345, 149, 25);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("list of employee");
		lblNewLabel_4.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(459, 21, 179, 36);
		getContentPane().add(lblNewLabel_4);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(346, 70, 424, 218);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		model.addColumn("name");
		model.addColumn("cin");
		model.addColumn("code");
		model.addColumn("password");
		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		table.setModel(model);
		table.setBounds(0, 0, 1, 1);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(424, 218);
		panel.add(scrollPane);
		
		for(Employe e:new EmployeDAO().SelectAll()){
			model.addRow(new Object[]{e.getNom(),e.getCin(),e.getCode(),e.getMotdepasse()});
		}
		
		JButton btnRetourne = new JButton("retourne");
		btnRetourne.setBackground(Color.PINK);
		btnRetourne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
					new Fenetre().setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnRetourne.setForeground(Color.black);
		btnRetourne.setBounds(599, 345, 156, 25);
		getContentPane().add(btnRetourne);
		
		setVisible(true);
	}

	class Authentification extends JDialog {
		
		private JPanel contentPanel=new JPanel();
		private JPasswordField passwordField;
		private JLabel label = new JLabel("");
		private void quitter(){
			dispose();
		}
		public Authentification(){
			Administrateur admi=new Administrateur();
			setTitle("authentification");
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
						JLabel lblPassword = new JLabel("cin");
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
							try{
							int i=0;
							for(Employe ee:new EmployeDAO().SelectAll()){
								if(Integer.parseInt(passwordField.getText())==ee.getCin()){
									i=1;
									dispose();
									new EmployeDAO().deleted(ee.getCin());
									JOptionPane.showMessageDialog(null, "employee supprimer", "message", JOptionPane.INFORMATION_MESSAGE);;
								
								}
							
						}
							if(i==0){
								label.setForeground(Color.red);
								label.setText("cin incorrecte");
							}}catch(Exception e1){}
							
								
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
							quitter();
							
						}
					});
					buttonPane.add(cancelButton);
				}
			}
			setVisible(true);
		}
		
		
	}
}
