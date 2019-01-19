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
	public boolean bet(double amt) // method places a bet on the balance by withdrawing the given amount & call askDeposit if bet > balance
	{
		boolean contIn = true;
		double bet = 0;
		Scanner input = new Scanner(System.in);
		while(contIn)
		{
			try // try catch clause prevents non-numerical or non-positive inputs
			{
				print("Enter the amount you want to bet: ");
				bet = input.nextDouble();
				if(bet <= 0) // Prevent non-positive numbers
				{
					println("You must bet something.");
					contIn = true;
					return true;
				}
				else if(bet > getBalance())
				{
					contIn = askDeposit();
					return false;
				}
				else
					contIn = false;
					return false;
			}
			catch(InputMismatchException ex) //Prevent non-numerical inputs
			{
				println("Enter a valid amount.");
				input.next();
			}
		}

		this.account.withdraw(amt);
		return false;
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
	public boolean askDeposit() /* method asks the player if he/she wants to deposit more money, deposits the amount the user input,
	or terminates the game depending on the player's choice*/
	{
		Scanner input = new Scanner(System.in);
		println("Not enough money in your account. Deposit more? (yes/no)");
		String ans = "";
		//boolean accountCheck = true;
		//		while(accountCheck)
		//		{
		//			println("Please enter Yes or No: ");
		//			ans = input.nextLine();
		//			ans = ans.toLowerCase();
		//			if(ans.equals("yes"))
		//			{
		//				accountCheck = false;
		//			}
		//			else if(ans.equals("no"))
		//			{
		//				accountCheck = false;
		//			}
		//		}
		//		accountCheck = true;
		//while(accountCheck)
		//{
		try {
			println("Please enter Yes or No: ");
			ans = input.nextLine();
			ans = ans.toLowerCase();
			if(ans.equals("yes"))
			{
				println("Deposit how much?");
				double amount = input.nextDouble();
				while(amount <= 0) {
					println("You must deposit something.");
					amount = input.nextDouble();
				}
				this.win(amount);
				return true;
				//accountCheck = false;
			}
			else if(ans.equals("no"))
			{
				print("OK. ");
				return false;
				//accountCheck = false;
			}
			return false;

		}
		catch(InputMismatchException ex)
		{
			println("Enter a valid amount");
			input.nextLine();
		}
		//}
		return false;
	}
}
