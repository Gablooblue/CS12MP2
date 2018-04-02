import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class Opponent7 extends GameObject {
	
	static ArrayList<Opponent> opponents = new ArrayList<Opponent>();
	Random rand = new Random();
	int curr_hp, curr_bullets;
	static Opponent curr_opp;
	static int curr_opp_index;
	
	String[] names = { "Fred", "Bill", "Mark", "John", "Richard", "Maria", "Jose"};
	public Opponent7()
	{
		for(int i = 0; i < 7; i++)
		{
			curr_hp = rand.nextInt(170)+ 31;
			curr_bullets = rand.nextInt(10) +1;
			opponents.add(new Opponent(curr_hp, curr_bullets, names[i], "upseal-300x300.png", "upsealded.png" ));
		}
		curr_opp_index = 0;
		curr_opp = opponents.get(0);
	}
	
	public boolean isCurrentDead()
	{
		if(opponents.get(curr_opp_index).hp <= 0)
		{
			return true;
		}
		return false;
	}
	
	
	public void nextOpponent()
	{
		curr_opp_index++;
		curr_opp = opponents.get(curr_opp_index);
	}
	
	public Opponent getOpponent()
	{
		return curr_opp;
	}
	
	public void paint(Graphics2D g)
	{
		g.setColor(Color.WHITE);
		g.drawString("HP: " + curr_opp.hp, 650, 50);
		g.drawString("Energy: " + curr_opp.bullets, 650, 75);
		if(curr_opp.hp > 0)
		{
			g.drawImage(curr_opp.OpponentAlive, 425, 200, null);
		}
		else
		{
			g.drawImage(curr_opp.OpponentDead, 425, 200, null);
		}
		
		if(curr_opp.basic)
		{
			displayAction(g, "Quiz");
		}
		if(curr_opp.special)
		{
			displayAction(g, "Long Exam");
		}
		if(curr_opp.reckless)
		{
			displayAction(g, "Release Grades");
			g.drawImage(curr_opp.RecklessImage, 375, 200, null);
			g.drawImage(curr_opp.RecklessImage, 400, 150, null);
			g.drawImage(curr_opp.RecklessImage, 395, 250, null);
			g.drawImage(curr_opp.RecklessImage, 410, 300, null);
			g.drawImage(curr_opp.RecklessImage, 390, 330, null);
			g.drawImage(curr_opp.RecklessImage, 360, 370 , null);
		}
		if(curr_opp.heal)
		{
			displayAction(g, "Lecture");
		}
		if(curr_opp.reload)
		{
			displayAction(g, "Suspend class");
		}
		if(curr_opp.display)
		{
			displayText(g, curr_opp.name + " skips a turn");
		}

	}
	private void displayAction( Graphics2D g, String text)
	{
			text = curr_opp.name + " used " + text; 
			displayText(g, text);
	}
	
	public void displayText(Graphics2D g, String text)
	{
			g.drawString(text, 520, 200);
			if(!curr_opp.timerSet)
			{
				curr_opp.aniTimer.schedule(new TimerTask() 
				{
					@Override
					public void run() 
					{
						if(!curr_opp.first)
							curr_opp.resetAnimations();
						else
							curr_opp.first = false;
					}
					
				},0, 5000);
				curr_opp.timerSet = true;
			}
	}

}
