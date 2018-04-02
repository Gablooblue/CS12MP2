import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Hero extends GameObject{
	
	BufferedImage HeroAlive;
	BufferedImage HeroDead;
	BufferedImage BookImage, StackImage, BeerImage;
	Timer aniTimer;
	TimerTask reset;
	
	int hp, bullets, chance;
	boolean skipTurn;
	boolean basic,special,reckless,heal,reload,display = false;
	boolean timerSet, first;
	Random r;
	public Hero() 
	{
		hp = 100;
		bullets = 6;
		skipTurn = false;
		HeroAlive = MarioWindow.getImage("me.png");
		HeroDead= MarioWindow.getImage("meded.png");
		BookImage = MarioWindow.getImage("Open-book-png.png");
		StackImage = MarioWindow.getImage("bookstack.png");
		BeerImage = MarioWindow.getImage("beer.png");

		r = new Random();
		aniTimer = new Timer();
		timerSet = false;
		first = true;
	}
	
	public void displaySkipTurn()
	{
		display = true;
	}
	public void paint(Graphics2D g)
	{
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 15)); 

		g.drawString("HP: " + hp, 75, 50);
		g.drawString("Energy: " + bullets, 75, 75);
		if(this.hp > 0)
		{
			g.drawImage(HeroAlive, 50, 200, null);
		}
		else 
		{
			g.drawImage(HeroDead, 50, 200, null);
		}
		
		//For attack animations
		if(basic)
		{
			g.drawImage(BookImage, 75, 400,300, 100, null);
			displayAction(g, "Study");

		}
		if(special)
		{
			g.drawImage(StackImage, 75, 200 ,300, 300, null);
			displayAction(g, "Cram");
		}
		if(reckless)
		{
			displayAction(g, "Cut class");
		}
		if(heal)
		{
			g.drawImage(BeerImage, 130, 200 ,300, 300, null);
			displayAction(g, "Drink alcohol");
		}
		if(reload)
		{
			displayAction(g, "Sleep");
		}
		if(display)
		{
			displayText(g, "Gab skips a turn");
		}
	}
	
	private void displayAction( Graphics2D g, String text)
	{
			text = "Gab used " + text; 
			displayText(g, text);
	}
	
	public void displayText(Graphics2D g, String text)
	{
			g.drawString(text, 150, 200);
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
		System.out.println("Hero performed basic attack!");
		basic = true;
		return 5;
	}
	
	public int specialAttack() 
	{
		System.out.println("Hero performed special attack!");
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
		System.out.println("Hero performed reckless attack!");
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
		System.out.println("Hero healed");
		int heal = r.nextInt(20) + 1;
		this.heal = true;
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
		System.out.println("Hero reloaded");
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
		display = false;
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
