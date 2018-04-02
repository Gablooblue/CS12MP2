
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CS12MP2 {

	public static Hero hero;
	public static Opponent7 opponents;
	public static Opponent opp;
	public static Background bg;
	public static Scanner s;
	public static ChoiceBoxes c;

	public static void main(String[] args) {
		hero = new Hero();
		opponents = new Opponent7();
		bg = new Background();
		boolean end = false;
		
		opp = opponents.getOpponent();
		c = new ChoiceBoxes();
		s = new Scanner(System.in);
		final MarioWindow w1 = new MarioWindow();
		w1.add(bg);
		w1.add(hero);
		w1.add(opponents);
		w1.add(c);
		(new Thread() {
			public void run() {
				w1.startGame();
			}			
		}).start();


		System.out.println("GAME START");

		while(!end)
		{
			end = askChoice();

		}
	}
	
	public static void delay(int seconds)
	{
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean askChoice()
	{
			int damage= 0 ;
			char choice;
			if(!hero.skipTurn)
			{
				c.visible = true;
				MarioWindow.delay(1);
				choice = c.status;
				switch(Character.toUpperCase(choice)) 
				{
					case 'A': 
						c.visible = false;
						damage = hero.basicAttack();
						break;
					case 'B':
						c.visible = false;
						damage = hero.specialAttack();
						break;
					case 'C':
						c.visible = false;
						damage = hero.recklessAttack();
						break;
					case 'D':
						c.visible = false;
						hero.heal();
						damage =0;
						break;
					case 'E':
						c.visible = false;
						hero.reload();
						damage = 0;
						break;
				}
				c.status = ' ';
				opp.hp -= damage;
			}
			else
			{
				System.out.println("You skip a turn");
				hero.displaySkipTurn();
				hero.skipTurn = false;
			}
			if(!c.visible)
			{
				delay(5);
				if(!opp.skipTurn)
					hero.hp -= opp.pickMove();
				else
				{
					System.out.println("Opponent skips a turn");
					opp.displaySkipTurn();
					opp.skipTurn = false;
				}
				delay(5);
			}
			
			if(hero.hp <= 0 || opp.hp <= 0)
			{
				System.out.println("GAME OVER");
				return true;
			}
			return false;
	}
}
