import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class InterfaceGraphiqueAuth extends JFrame  {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel p1=new JPanel();
	private JPanel p2=new JPanel();
	private JLabel loginL=new JLabel("Login");
	private JLabel passwdL=new JLabel("Mot de passe");
	private JTextField LoginTF=new JTextField();
	private static JPasswordField passwordTF=new JPasswordField();
	private JButton confirmerB=new JButton("Confirmer");
	private JButton annulerB=new JButton("Annuler");
	private static String login="aissa";
	private static char[] password={'a','b','c','d'};
	
	
	
	
	public InterfaceGraphiqueAuth(){
		this.iniComponents();
		this.setVisible(true);
	}	
	public void iniComponents(){
		
		this.setTitle("Authentifiation");
		this.setSize(400, 120);
		//Fermeture de la fenêtre quand on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Positionnement au centre de l’écran:
		this.setLocationRelativeTo(null);
		
		
		//ajouter des composants au GridLayout de p1
		p1.setBackground(Color.cyan);
		p1.setLayout(new GridLayout(1, 4));
		p1.add(loginL);
		p1.add(LoginTF);
		p1.add(passwdL);
		p1.add(passwordTF);
		
		////ajouter des composants au FlowLayout de p2
		p2.setBackground(Color.cyan);
		p2.setLayout(new FlowLayout()); //c'est pas necessaire car une jpanel son layout par defaut est flowlayout
		p2.add(confirmerB);
		p2.add(annulerB);
		
		//Ajouter les panels à la fenêtre principale 
		this.setLayout(new BorderLayout()); //c'est pas necessaire car une jframe son layout par defaut est borderlayout
		this.getContentPane().add(p1,BorderLayout.NORTH );
		this.getContentPane().add(p2,BorderLayout.SOUTH );
		
		
		//Indiquer au composant que la classe interne l’écoute. à travers une classe interne
		confirmerB.addActionListener(new confirmerBHandler());
		
		// à travers une classe anonyme
		annulerB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	
	}
	//la classe interne
	class confirmerBHandler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			int i;
			char[] pa=passwordTF.getPassword();
			
			if((LoginTF.getText()).equals(login)){
				
				if(pa.length==password.length){
				
					for(i=0;i<password.length;i++){
						if(pa[i]==password[i] )
							continue;
						else
							break;
					}
			
					if(i<password.length-1)
						JOptionPane.showMessageDialog(null, "echec", "message", JOptionPane.ERROR_MESSAGE);
						
					else
						JOptionPane.showMessageDialog(null, "succes", "message", JOptionPane.INFORMATION_MESSAGE);
					
				}
				else
					JOptionPane.showMessageDialog(null, "echec", "message", JOptionPane.ERROR_MESSAGE);
					}
			else
				JOptionPane.showMessageDialog(null, "echec", "message", JOptionPane.ERROR_MESSAGE);
				}
		}

}
