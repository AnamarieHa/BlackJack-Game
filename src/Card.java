/* class creates a randomized deck of cards
*/
public class Card
{
	private int rank;
	private String face;
	private String suit;
	private int value;
	public Card(int card) // contructor creates a randomized deck of cards and assigns value to each card
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
	public int getValue() // method returns value of the card (eg: value of a King is 10)
	{
		return this.value;
	}
	public String getSuit() // method returns suit of the card
	{
		return this.suit;
	}
	public String getFace() // method returns the face of the card (eg: Queen)
	{
		return this.face;
	}
	public void reduceAce() // method reduces the value of an Ace from 11 to 1
	{
		if(this.value == 11)
			this.value = 1;
	}
	@Override
	public String toString() // method returns a String representation of the cards
	{	
		if(this.rank == 11)
			return (this.face + " of " + this.suit);
		else
			return (this.rank + " of " + this.suit);
	}
}
