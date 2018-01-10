import java.util.*;

public class Player
{
	private Account account;
	private int acct;
	public Player()
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
	public void bet(double amt)
	{
		this.account.withdraw(amt);
	}
	public void win(double amt)
	{
		this.account.deposit(amt);
	}
	public double getBalance()
	{
		return this.account.getBalance();
	}
	public static void print(String str)
	{
		System.out.print(str);
	}
	public static void println(String str)
	{
		System.out.println(str);
	}
}
