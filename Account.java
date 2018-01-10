/* class creates a new account for each player
* holds information about account number and balance
* allows the program to withdraw, deposit, and get the balance
*/
public class Account 
{
	private int accNum; 
	private double balance;
	public Account(int num) // creates a new account without money
	{
		this.accNum = num;
		this.balance = 0;
	}
	public Account(int num, double balance) // creates a new account with the given balance
	{
		this.accNum = num;
		this.balance = balance;
	}
	public void withdraw(double amount) // method subtracts the given amount from an account
	{
		this.balance -= amount;
	}
	public void deposit(double amount) // method adds the given amoutn to an account
	{
		this.balance += amount;
	}
	public double getBalance()  // method returns the balance of an account
	{
		return this.balance;
	}
}
