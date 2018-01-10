/* Class holds information of each player in the game for each user
*/
import java.util.*;

public class Player
{
	private Account account;
	private int acct;
	public Player() // constructor asks user to enter account number and creates an initial balance of a new account
	{
		Scanner input = new Scanner(System.in);
		boolean contIn = true;
		while(contIn)
		{
			try
			{
				print("Enter your account number: ");
				acct= input.nextInt();
				contIn = false;
			}
			catch(InputMismatchException ex)
			{
				println("Enter a valid account number");
				input.nextLine();
			}
		}
		contIn = true;
		double amount = 0;
		while(contIn)
		{
			try
			{
				print("How much money is in your account? ");
				amount = input.nextDouble();
				while(amount <= 0) {
					println("You must start with something. How much money is in your account?");
					amount = input.nextDouble();
				}
				contIn = false;
			}
			catch(InputMismatchException ex)
			{
				println("Enter a valid amount.");
				input.nextLine();
			}
		}
		account = new Account(acct, amount);
	}
	public void bet(double amt) // method places a bet on the balance by withdrawing the given amount 
	{
		this.account.withdraw(amt);
	}
	public void win(double amt) // method deposits the given amount when a player wins
	{
		this.account.deposit(amt);
	}
	public double getBalance() // method returns balance of the player's account
	{
		return this.account.getBalance();
	}
	public static void print(String str) // method creates a shorter version of System.out.print() for purpose of writing the program
	{
		System.out.print(str);
	}
	public static void println(String str) // method creates a shorter version of System.out.println() for purpose of writing the program
	{
		System.out.println(str);
	}
}
