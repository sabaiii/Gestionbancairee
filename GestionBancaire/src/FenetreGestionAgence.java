import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.Button;

public class FenetreGestionAgence extends JFrame {
	
	private int veri=0;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private DefaultTableModel model=new DefaultTableModel();
	

	
	public FenetreGestionAgence() throws SQLException {
		setTitle("gestion agence");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1004, 595);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.PINK);
		setJMenuBar(menuBar);
		
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		
		
		
		JMenuItem mntmRetourne = new JMenuItem("retourne");
		mnFile.add(mntmRetourne);
		mntmRetourne.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				try {
					new FenetreGestionEmployee();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem mntmExit = new JMenuItem("exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAjouterUneAgnece = new JLabel("ajouter une agence ");
		lblAjouterUneAgnece.setForeground(Color.MAGENTA);
		lblAjouterUneAgnece.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		lblAjouterUneAgnece.setBounds(70, 55, 213, 37);
		contentPane.add(lblAjouterUneAgnece);
		
		JLabel lblNewLabel = new JLabel("nom");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(12, 133, 56, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ville");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setBounds(12, 195, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("addresse");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setBounds(12, 251, 56, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("code");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(12, 316, 56, 16);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(83, 130, 200, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(83, 192, 200, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(83, 245, 200, 22);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(83, 310, 200, 22);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				new AgenceBancaireDAO().inserted(new AgenceBancaire(textField_2.getText(), textField_1.getText(), Integer.parseInt(textField_3.getText()), textField.getText()));
				JOptionPane.showMessageDialog(null, "agence crée", "message", JOptionPane.INFORMATION_MESSAGE);}
				catch(Exception e1){
					
				}
				
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(93, 374, 97, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("remove agence");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new Verifier();
				
			}
		});
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.setBounds(101, 459, 143, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnModifierAgence = new JButton("modifier agence");
		btnModifierAgence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				veri=1;
				new Verifier();
			}
		});
		btnModifierAgence.setBackground(Color.CYAN);
		btnModifierAgence.setBounds(310, 459, 143, 25);
		contentPane.add(btnModifierAgence);
		
		JButton btnRetourne = new JButton("retourne");
		btnRetourne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				try {
					new FenetreGestionEmployee();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRetourne.setBackground(Color.PINK);
		btnRetourne.setBounds(797, 459, 143, 25);
		contentPane.add(btnRetourne);
		
		JPanel panel = new JPanel();
		panel.setBounds(338, 105, 633, 318);
		contentPane.add(panel);
		panel.setLayout(null);
		
		table = new JTable();
		table.setBounds(0, 0, 541, 273);
		panel.add(table);
		model.addColumn("nom");
		model.addColumn("ville");
		model.addColumn("addresse");
		model.addColumn("code");
		model.addColumn("date_creation");
		table.setBackground(Color.WHITE);
		for(AgenceBancaire a:new AgenceBancaireDAO().SelectAll()){
			model.addRow(new Object[]{a.getNom(),a.getVille(),a.getAdresse(),a.getCode(),a.getDateCreation()});
		}
		table.setModel(model);
		table.setBounds(0, 0, 1, 1);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(633, 318);
		panel.add(scrollPane);
		
		JLabel lblListeDesAgences = new JLabel("liste des agences ");
		lblListeDesAgences.setForeground(Color.MAGENTA);
		lblListeDesAgences.setFont(new Font("Segoe Print", Font.PLAIN, 20));
		lblListeDesAgences.setBounds(597, 55, 213, 37);
		contentPane.add(lblListeDesAgences);
		
		JButton btnActualiser = new JButton("actualiser");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
					new FenetreGestionAgence().setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnActualiser.setBackground(Color.CYAN);
		btnActualiser.setBounds(639, 459, 97, 25);
		contentPane.add(btnActualiser);
	}
	
	
	class Verifier extends JDialog {
		
		private JPanel contentPanel=new JPanel();
		private JTextField text1;
		private JLabel label = new JLabel("");
		public Verifier(){
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
						JLabel lblPassword = new JLabel("code d'agence");
						lblPassword.setForeground(Color.BLUE);
						panel_1.add(lblPassword);
					}
				}
				{
					JPanel panel_1 = new JPanel();
					panel.add(panel_1);
					{
						text1=new JTextField();
						text1.setColumns(10);
						panel_1.add(text1);
					}
				}
			}
			{
				JPanel panel = new JPanel();
				contentPanel.add(panel);
				{
					label.setForeground(Color.RED);
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
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							
							if(veri==1){
								veri=0;
								dispose();
								new Modifier().setVisible(true);;
								
							}
							else{
								dispose();
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
		}
}
	public class Modifier extends JFrame {
		private JPanel contentPane;
		private JTextField textField;
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;

		public Modifier() {
			setTitle("modification");
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			setBounds(100, 100, 452, 410);
			setLocationRelativeTo(null);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("nom d'agence");
			lblNewLabel.setForeground(Color.BLUE);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
			lblNewLabel.setBounds(48, 40, 115, 16);
			contentPane.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("ville");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
			lblNewLabel_1.setForeground(Color.BLUE);
			lblNewLabel_1.setBounds(48, 103, 84, 16);
			contentPane.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("addresse");
			lblNewLabel_2.setForeground(Color.BLUE);
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
			lblNewLabel_2.setBounds(48, 175, 97, 16);
			contentPane.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("code d'agence");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
			lblNewLabel_3.setForeground(Color.RED);
			lblNewLabel_3.setBounds(53, 244, 122, 16);
			contentPane.add(lblNewLabel_3);
			
			JButton btnNewButton = new JButton("valider");
			btnNewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dispose();
				}
			});
			btnNewButton.setBackground(Color.GREEN);
			btnNewButton.setBounds(78, 302, 97, 25);
			contentPane.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("cancel");
			btnNewButton_1.setBackground(Color.PINK);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnNewButton_1.setBounds(239, 302, 97, 25);
			contentPane.add(btnNewButton_1);
			
			textField = new JTextField();
			textField.setBounds(196, 37, 140, 22);
			contentPane.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setBounds(196, 100, 140, 22);
			contentPane.add(textField_1);
			textField_1.setColumns(10);
			
			textField_2 = new JTextField();
			textField_2.setBounds(196, 172, 140, 22);
			contentPane.add(textField_2);
			textField_2.setColumns(10);
			
			textField_3 = new JTextField();
			textField_3.setBounds(196, 242, 140, 22);
			contentPane.add(textField_3);
			textField_3.setColumns(10);
		}
	}
}
