
public class Card
{
	private int rank;
	private String face;
	private String suit;
	private int value;
	public Card(int card)
	{
		String[] faces = {"Jack", "Queen", "King", "Ace"};
		String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
		int num = (card % 13) + 1; // 1 thru 13
		if(num >= 2 && num <= 10)
		{
			this.rank = num;
			this.value = num;
		}
		else
		{
			this.rank = 11;
			if(num==1)
			{
				this.face = "Ace";
				this.value = 11;
			}
			else
			{
				this.face = faces[num - 11];
				this.value = 10;
			}
		}
		this.suit = suits[card % 4];
	}
	public int getValue()
	{
		return this.value;
	}
	public String getSuit()
	{
		return this.suit;
	}
	public String getFace()
	{
		return this.face;
	}
	public void reduceAce()
	{
		if(this.value == 11)
			this.value = 1;
	}
	@Override
	public String toString()
	{	
		if(this.rank == 11)
			return (this.face + " of " + this.suit);
		else
			return (this.rank + " of " + this.suit);
	}
}
