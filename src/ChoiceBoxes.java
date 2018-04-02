import java.awt.Color;
import java.awt.Graphics2D;

public class ChoiceBoxes extends GameObject {
	char status;
	int x[] = new int[5];
	int y[] = new int[5];
	int width, height;
	boolean visible;
	
	public ChoiceBoxes()
	{
		x[0] = 100;
		y[0] = 520;
		width = 100;
		height = 60;
		for(int i=1; i < 5; i++)
		{
			x[i] = x[i-1] + width + 10;
			y[i] = y[0];
		}
		visible = true;
	}
	
	public void paint(Graphics2D g)
	{
		if(visible)
		{
			g.setColor(Color.BLUE);
			g.fillRect(x[0], y[0], width,height);
			g.setColor(Color.RED);
			g.fillRect(x[1], y[1], width,height);
			g.setColor(Color.CYAN);
			g.fillRect(x[2], y[2], width,height);
			g.setColor(Color.YELLOW);
			g.fillRect(x[3], y[3], width,height);
			g.setColor(Color.GREEN);
			g.fillRect(x[4], y[4], width,height);
		}
	}
	
	public void mouseClicked(int xmouse, int ymouse, int button)
	{
		if ((xmouse >= x[0]) && (xmouse <= x[0] + width) 
				&& (ymouse >= y[0]) && (ymouse <= y[0] + height)) 
		{
			    status = 'A';
		}
		else if ((xmouse >= x[1]) && (xmouse <= x[1] + width) 
				&& (ymouse >= y[1]) && (ymouse <= y[1] + height)) 
		{
			    status = 'B';
		}
		else if ((xmouse >= x[2]) && (xmouse <= x[2] + width) 
				&& (ymouse >= y[2]) && (ymouse <= y[2] + height)) 
		{
			    status = 'C';
		}
		else if ((xmouse >= x[3]) && (xmouse <= x[3] + width) 
				&& (ymouse >= y[3]) && (ymouse <= y[3] + height)) 
		{
			    status = 'D';
		}
		else if ((xmouse >= x[4]) && (xmouse <= x[4] + width) 
				&& (ymouse >= y[4]) && (ymouse <= y[4] + height)) 
		{
			    status = 'E';
		}
		MarioWindow.delay(1000);
	}

}
