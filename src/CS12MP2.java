
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class CS12MP2 {

	public static Hero hero;
	public static Opponent7 opponents;
	public static Opponent opp;
	public static Background bg;
	public static Scanner s;

	public static void main(String[] args) {
		hero = new Hero();
		opponents = new Opponent7();
		bg = new Background();
		boolean end = false;
		

		s = new Scanner(System.in);
		final MarioWindow w1 = new MarioWindow();
		w1.add(bg);
		w1.add(hero);
		w1.add(opp);
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
				System.out.println("HERO HP: " + hero.hp);
				System.out.println("OPPONENT HP: " + opp.hp);
				System.out.println("MOVE LIST");
				System.out.println("A. Basic Attack");
				System.out.println("B. Special Attack");
				System.out.println("C. Reckless Attack");
				System.out.println("D. Heal");
				System.out.println("E. Reload");
				choice = s.next().charAt(0);
				
				switch(Character.toUpperCase(choice)) 
				{
					case 'A': 
						damage = hero.basicAttack();
						break;
					case 'B':
						damage = hero.specialAttack();
						break;
					case 'C':
						damage = hero.recklessAttack();
						break;
					case 'D':
						hero.heal();
						damage =0;
						break;
					case 'E':
						hero.reload();
						damage = 0;
						break;
				}
				opp.hp -= damage;
			}
			else
			{
				System.out.println("You skip a turn");
				hero.skipTurn = false;
			}

			delay(5);
			if(!opp.skipTurn)
				hero.hp -= opp.pickMove();
			else
			{
				System.out.println("Opponent skips a turn");
				opp.skipTurn = false;
			}
			delay(5);
			
			if(hero.hp <= 0 || opp.hp <= 0)
			{
				System.out.println("GAME OVER");
				return true;
			}
			return false;
	}
}
