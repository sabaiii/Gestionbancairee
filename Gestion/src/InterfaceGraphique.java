import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class InterfaceGraphique extends JFrame {
	
	
	

	private static final long serialVersionUID = 1L;
	//on crée notre menu
	private JMenuBar menuBar=new JMenuBar(); 
	//les titres principales de menu
	private JMenu Fichier = new JMenu("Fichier");
	private JMenu Edition = new JMenu("Editon");
	//les composantes
	private JMenuItem  Nouveau=new JMenuItem("Nouveau");
	private JMenuItem  Ouvrir=new JMenuItem("Ouvrir");
	private JMenuItem  Quitter=new JMenuItem("Quitter");
	private JMenuItem  Copier=new JMenuItem("Copier");
	private JMenuItem  Coller=new JMenuItem("Coller");
	//constructeur
	public InterfaceGraphique(){
		this.initiComponents();
		this.setVisible(true);
	}
	//les methodes
	public void initiComponents(){
		this.setTitle("Gestion Bancaire");
		this.setSize(1000,667);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.Fichier.add(Nouveau);
		this.Fichier.add(Ouvrir);
		this.Fichier.add(Quitter);
		this.Edition.add(Copier);
		this.Edition.add(Coller);
		
		this.Quitter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
				
			}
		});
		this.menuBar.add(Fichier);
		this.menuBar.add(Edition);
		this.setJMenuBar(menuBar);
	}
	
/*public static void main(String[] args) {
	new InterfaceGraphique();
}*/
}
