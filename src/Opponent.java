import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;



public class Opponent extends GameObject{
	int hp, bullets, chance;
	boolean skipTurn;
	Timer aniTimer;
	Random r;
	BufferedImage OpponentAlive;
	BufferedImage OpponentDead;
	BufferedImage SingkoImage;
	boolean basic,special,reckless,heal,reload = false;
	boolean timerSet, first;
	String name;

	public Opponent(int hp, int bullets, String name, String imgAlive, String imgDead){
		this.name = name;
		this.hp = hp;
		this.bullets = bullets;
		skipTurn = false;
		r = new Random();
		OpponentAlive = MarioWindow.getImage(imgAlive);
		OpponentDead= MarioWindow.getImage(imgDead);
		SingkoImage = MarioWindow.getImage("singko.png");
		aniTimer = new Timer();
		timerSet = false;
		first = true;
	}
	public void paint(Graphics2D g)
	{
		g.setColor(Color.WHITE);
		g.drawString("HP: " + hp, 650, 50);
		g.drawString("Energy: " + bullets, 650, 75);
		if(this.hp > 0)
		{
			g.drawImage(OpponentAlive, 425, 200, null);
		}
		else
		{
			g.drawImage(OpponentDead, 425, 200, null);
		}
		
		if(basic)
		{
			displayAction(g, "Quiz");
		}
		if(special)
		{
			displayAction(g, "Long Exam");
		}
		if(reckless)
		{
			displayAction(g, "Release Grades");
			g.drawImage(SingkoImage, 375, 200, null);
			g.drawImage(SingkoImage, 400, 150, null);
			g.drawImage(SingkoImage, 395, 250, null);
			g.drawImage(SingkoImage, 410, 300, null);
			g.drawImage(SingkoImage, 390, 330, null);
			g.drawImage(SingkoImage, 360, 370 , null);
		}
		if(heal)
		{
			displayAction(g, "Lecture");
		}
		if(reload)
		{
			displayAction(g, "End class");
		}
	}
	public int pickMove() 
	{
		if(this.bullets == 0)
		{
			reload();
		}
		else 
		{
			int choice = r.nextInt(5);
			switch(choice) 
			{
				case 0: 
					return basicAttack();
				case 1:
					return specialAttack();
				case 2:
					return recklessAttack();
				case 3:
					heal();
					break;
				case 4:
					reload();
					break;
			}
		}
		return 0;
	}
	private void displayAction( Graphics2D g, String text)
	{
			text = "UP used " + text; 
			g.drawString(text, 520, 200);
			if(!timerSet)
			{
				aniTimer.schedule(new TimerTask() 
				{
					@Override
					public void run() 
					{
						if(!first)
							resetAnimations();
						else
							first = false;
					}
					
				},0, 5000);
				timerSet = true;
			}
	}
	
	public int basicAttack()
	{
		System.out.println(name + " performed basic attack");
		basic = true;
		return 5;
	}
	
	public int specialAttack() 
	{
		System.out.println(name + " performed special attack");
		chance = r.nextInt(100) + 1;
		special = true;
		bullets--;
		if(chance > 20)
		{
			int damage = 30;
			return damage;
		}
		else
		{
			return 0;
		}
	}
	
	public int recklessAttack()
	{
		System.out.println(name + " performed reckless attack");
		chance = r.nextInt(100) + 1;
		reckless = true;
		skipTurn = true;
		if(chance > 50)
		{
			int damage = 50;
			return damage;
		}
		else
		{
			return 0;
		}
	}
	
	public void heal()
	{
		System.out.println(name + " healed");
		heal = true;
		int heal = r.nextInt(20) + 1;
		if(this.hp + heal > 100)
		{
			this.hp = 100;
		}
		else
		{
			this.hp += heal;
		}
	}
	
	public void reload()
	{
		System.out.println(name + " reloaded");
		reload = true;
		this.bullets = 6;
	}
	private void resetAnimations()
	{
		basic = false;
		special = false;
		reckless =  false;
		heal = false;
		reload = false;
		timerSet = false;

		first = true;
		try {
			aniTimer.cancel();
			aniTimer.purge();
		}
		finally
		{
		}
		aniTimer = new Timer();
	}
}
