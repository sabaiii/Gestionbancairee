

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class Fenetre extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField1;
	
	public static void main(String[] args) throws SQLException {
		Fenetre frame = new Fenetre();
		frame.setVisible(true);
		
	}
	public Fenetre() throws SQLException {
		Administrateur a=new Administrateur().initilaze();
		
		setTitle("GESTION BANCAIRE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 350);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.PINK);
		setJMenuBar(menuBar);
		
		JMenu mnOption = new JMenu("file");
		menuBar.add(mnOption);
		
		JMenuItem mntmA = new JMenuItem("administrateur");
		mntmA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Authentification("password");
			}
		});
		
		mnOption.add(mntmA);
		
		JMenuItem mntmSelection = new JMenuItem("exit");
		mntmSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnOption.add(mntmSelection);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4,1));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2));
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		
		JLabel lblSelect = new JLabel("select");
		lblSelect.setForeground(Color.BLUE);
		panel_4.add(lblSelect);
		
		JPanel panel_7 = new JPanel();
		panel_1.add(panel_7);
		
		String[] tab = {"employee_siege","employee_agence"};
		JComboBox comboBox = new JComboBox(tab);
		comboBox.setForeground(Color.BLUE);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(comboBox.getSelectedItem());}
		});
		panel_7.add(comboBox);
		
	
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 2));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setForeground(Color.BLUE);
		panel_5.add(lblUsername);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);
		
		textField1 = new JTextField();
		panel_6.add(textField1);
		textField1.setColumns(14);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 2));
		
		JPanel panel_8 = new JPanel();
		panel_3.add(panel_8);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setForeground(Color.BLUE);
		panel_8.add(lblPassword);
		
		JPanel panel_9 = new JPanel();
		panel_3.add(panel_9);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(14);
		passwordField.setToolTipText("");
		passwordField.setSize(60, 60);
		panel_9.add(passwordField);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 3));
		
		JPanel panel_12 = new JPanel();
		panel.add(panel_12);
		
		JButton btnNewButton = new JButton("login");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i=0;
				for(Employe e:a.getEmployees()){
					i++;
					if(e.getMotdepasse().equals(passwordField.getText()) && e.getNom().equals(textField1.getText())){
						String s=comboBox.getSelectedItem().toString();
						if(s.equals("employee_siege") && e.getCode()==0){
							dispose();
							try {
								new FenetreGestionClient((EmployeSiege)e).setVisible(true);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;}
						
						if(s.equals("employee_agence") && e.getCode()!=0){
							dispose();
							try {
								new FenetreGestionClient((EmployeAgence)e).setVisible(true);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							break;}
						
					}
					if(i==a.getEmployees().size()){
						new Dialogue();
					}
		
					}
				
				
				}	
		});
	
		btnNewButton.setBackground(Color.GREEN);
		panel_12.add(btnNewButton);
		
		JPanel panel_10 = new JPanel();
		panel.add(panel_10);
		
		JButton btnNewButton_1 = new JButton("administrateur");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Authentification("password");
			}
		});
		btnNewButton_1.setBackground(Color.CYAN);
		panel_10.add(btnNewButton_1);
		
		JPanel panel_11 = new JPanel();
		panel.add(panel_11);
		
		JButton btnNewButton_2 = new JButton("exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_2.setBackground(Color.RED);
		panel_11.add(btnNewButton_2);
		
	}
	
	class Authentification extends JDialog {
		
		private JPanel contentPanel=new JPanel();
		private JPasswordField passwordField;
		private JLabel label = new JLabel("");
		private void quitter(){
			dispose();
		}
		public Authentification(String s){
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
							if(passwordField.getText().equals(admi.getPassword())){
								dispose();
								try {
									new FenetreGestionEmployee();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							else{
								label.setForeground(Color.red);
								label.setText(s +" incorrecte");
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
