import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;



public class Opponent {
	int hp, bullets, chance;
	boolean skipTurn;
	Timer aniTimer;
	Random r;
	BufferedImage OpponentAlive;
	BufferedImage OpponentDead;
	BufferedImage RecklessImage;
	boolean basic,special,reckless,heal,reload,display = false;
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
		RecklessImage= MarioWindow.getImage("singko.png");
		aniTimer = new Timer();
		timerSet = false;
		first = true;
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
	
	public void displaySkipTurn()
	{
		display = true;
	}
	public void reload()
	{
		System.out.println(name + " reloaded");
		reload = true;
		this.bullets = 6;
	}
	public void resetAnimations()
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
