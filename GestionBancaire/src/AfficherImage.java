


import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class AfficherImage extends JPanel {

		Image image;
		
		public AfficherImage(String s){
			
			this.image=getToolkit().getImage(s); 
			
		}
		
		public void paintComponent(Graphics g){
				
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				
		}
}
