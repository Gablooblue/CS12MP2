import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
public class Background extends GameObject {
	BufferedImage bgImage;
	
	public Background() {
		bgImage = MarioWindow.getImage("vaporwaveneon.jpg");
	}
	
	public void paint(Graphics2D g)
	{
		g.drawImage(bgImage, -200,0,null);
	}

}
