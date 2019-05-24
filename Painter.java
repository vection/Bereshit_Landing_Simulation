import java.awt.*;

import javax.swing.JComponent;


public class Painter extends JComponent{
	spaceCraft craft;
	
	public Painter(spaceCraft craft) {
		this.craft = craft;
	}
	
	
	/*public void paintComponent(Graphics g) {
		super.paintComponent(g);
		craft.paint(g);
		
	}*/
}