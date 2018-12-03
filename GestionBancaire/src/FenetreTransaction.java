import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FenetreTransaction extends JFrame {

	private JPanel contentPane;
	private JTextField txtSvpEntrezLe;

	
	
	public FenetreTransaction(Compte c) throws SQLException {
		c.getTransaction().addAll(0, new TransactionDAO().SelectAll(c.getCode()));
		setTitle("transaction");
		setBounds(100, 100, 456, 367);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.PINK);
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("file");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomDeClient = new JLabel("nom de client : "+c.getNom());
		lblNomDeClient.setForeground(Color.BLUE);
		lblNomDeClient.setBounds(55, 83, 221, 16);
		contentPane.add(lblNomDeClient);
		
		JLabel lblSonCin = new JLabel("son cin :" +c.getCin());
		lblSonCin.setForeground(Color.BLUE);
		lblSonCin.setBounds(55, 112, 202, 16);
		contentPane.add(lblSonCin);
		
		JLabel lblCodeDuCompte = new JLabel("code du compte : "+c.getCode());
		lblCodeDuCompte.setForeground(Color.BLUE);
		lblCodeDuCompte.setBounds(55, 26, 172, 16);
		contentPane.add(lblCodeDuCompte);
		
		JButton btnNewButton = new JButton("retirer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new CompteCourantDAO().Select(c.getCode()).retirer(Double.parseDouble(txtSvpEntrezLe.getText()));
					new CompteCourantDAO().Select(c.getCode()).addTransaction(1,Double.parseDouble(txtSvpEntrezLe.getText()));
					JOptionPane.showMessageDialog(null, "transaction réussi", "message", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(99, 197, 97, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("d\u00E9poser");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					new CompteCourantDAO().Select(c.getCode()).déposer((Double.parseDouble(txtSvpEntrezLe.getText())));
					new CompteCourantDAO().Select(c.getCode()).addTransaction(0,Double.parseDouble(txtSvpEntrezLe.getText()));
					JOptionPane.showMessageDialog(null, "transaction réussi", "message", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBackground(Color.ORANGE);
		btnNewButton_1.setBounds(231, 197, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnExit = new JButton("exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBackground(Color.PINK);
		btnExit.setBounds(318, 265, 97, 25);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("montant :");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(100, 155, 56, 16);
		contentPane.add(lblNewLabel);
		
		txtSvpEntrezLe = new JTextField();
		txtSvpEntrezLe.setText(" SVP entrez le montant ");
		txtSvpEntrezLe.setBounds(187, 152, 146, 22);
		contentPane.add(txtSvpEntrezLe);
		txtSvpEntrezLe.setColumns(10);
	}
}
