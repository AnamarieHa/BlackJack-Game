
public class Account 
{
	private int accNum;
	private double balance;
	public Account(int num)
	{
		this.accNum = num;
		this.balance = 0;
	}
	public Account(int num, double balance)
	{
		this.accNum = num;
		this.balance = balance;
	}
	public void withdraw(double amount)
	{
		this.balance -= amount;
	}
	public void deposit(double amount)
	{
		this.balance += amount;
	}
	public double getBalance()
	{
		return this.balance;
	}
}
