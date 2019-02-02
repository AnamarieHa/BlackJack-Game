/* Class simulates a game of Black Jack between the computer and the user
 * The computer acts as the dealer
 */
import java.util.*;
public class Blackjack 
{
	static int playerTotal; // variable holds the total value of the player's hand(s)
	static int dealerTotal; // variable holds the total value of the dealer's hand
	static ArrayList<Integer> allCards; // Array list holds all cards that has been dealt in the game
	static ArrayList<Card> dealerCards; // Array list holds the dealer's cards
	static ArrayList<Card> playerCards; // Array list holds the player's cards
	static Player player;
	public static void main(String[] args)
	{
		player = new Player();
		playBlackjack();
	}
	public static void playBlackjack() 
	{
		boolean contPlay = true; // whether the player wants to continue playing
		Scanner input = new Scanner(System.in);
		double bet = player.bet();
		double winnings = 0;
		//contPlay = true;
		playerCards = new ArrayList<Card>(); // holds the player's hands
		dealerCards = new ArrayList<Card>(); // holds the dealer's hand
		allCards = new ArrayList<Integer>(); // keeps track of all cards in play
		for(int i = 0; i < 4; i++) // loop draw first cards 
		{
			drawCard();
			if(i<2) //Draw 2 cards for the player
				playerCards.add(new Card(allCards.get(i)));
			else //Draw 2 cards for the dealer
				dealerCards.add(new Card(allCards.get(i)));

		}
		playerTotal = calculateTotal(playerCards);
		dealerTotal = calculateTotal(dealerCards);
		println("You have the " + playerCards.get(0) + " and the " + playerCards.get(1));
		println("Your total is " + playerTotal);
		println("Dealer has the " + dealerCards.get(0));
		String reply = "stand"; 
		if(playerTotal < 21) 
		{
			println("Would you like to Hit, Stand, or Split? ");
			reply = input.nextLine().toLowerCase();
			while(!(reply.equals("hit") || reply.equals("split") || reply.equals("stand"))) //Input validation
			{
				println("Please enter Hit, Stand, or Split.");
				reply = input.nextLine().toLowerCase();
			}
			while(reply.equals("hit"))
			{	
				reply = playerHit(playerCards);
			}
			if(reply.equals("split"))
			{
				playerTotal = 0;
				int hand1Total = split(0);
				int hand2Total = split(1);
				if(hand1Total > 21 && hand2Total >21)
				{
					println("Dealer stands with " + dealerCards.get(0) + ", " + dealerCards.get(1));
				}
				else
				{
					dealer();
					if((dealerTotal < 18) && ((hand1Total <= 21 && hand1Total >= 16) || (hand2Total <= 21 && hand2Total >= 16)))
					{
						drawCard();
						dealerCards.add(new Card(allCards.get(allCards.size()-1)));
						println("Dealer hit and got the " + dealerCards.get(dealerCards.size()-1));
						dealerTotal = calculateTotal(dealerCards);
					}
					println("Dealer stands with " + dealerCards);
				}
				println("Dealer has a total of " + dealerTotal);
				if(hand1Total <= 21 && (hand1Total > dealerTotal  || dealerTotal > 21))
				{
					winnings += bet*2;
					println("Hand 1 wins. You get $" + winnings);
				}
				else if(hand1Total == dealerTotal)
				{
					winnings += bet/2;
					println("Hand 1 and the dealer tie. You get %" + winnings);
				}
				else
					println("Hand 1 loses.");
				if(hand2Total <= 21 && (hand2Total > dealerTotal  || dealerTotal > 21))
				{
					winnings += bet*2;
					println("Hand 2 wins. You get $" + (bet*2));
				}
				else if(hand2Total == dealerTotal)
				{
					winnings += bet/2;
					println("Hand 2 and the dealer tie. You get $" + (bet/2));
				}
				else
					println("Hand 2 loses.");
			}
		}
		if(reply.equals("stand"))
		{
			if(playerTotal > 21)
			{
				println("Dealer stands with " + dealerCards.get(0) + ", " + dealerCards.get(1));
			}
			else
			{
				dealer();
				println("Dealer stands with " + dealerCards);
			}
			println("Dealer has a total of " + dealerTotal);
			if(playerTotal <= 21 && (playerTotal > dealerTotal  || dealerTotal > 21))
			{
				winnings = bet*2;
				println("You won. You get $" + bet);
				player.win(bet*2);
				println("Your balance is now " + String.valueOf(player.getBalance()));
			}
			else if(playerTotal == dealerTotal)
			{
				winnings = bet/2;
				println("You and the dealer tie.");
				player.win(bet);
				println("Your balance is now " + String.valueOf(player.getBalance()));
			}
			else 
			{
				println("Dealer wins.");
				println("Your balance is now " + String.valueOf(player.getBalance()));
		
			}
		}
		player.win(winnings);
		println("Would you like to play again? (yes/no)");
		String answer = input.nextLine().toLowerCase();
		while (answer.equals("yes") || answer.equals("no")) {
			if(answer.equals("yes"))
			{
				
				playBlackjack();
			}
			else if(answer.equals("no"))
			{
				contPlay = false;
			}

		}
		if (contPlay == false) 
		{
			println("Thank you for playing.");
			println("You ended with $" + player.getBalance() + " in your account.");
		}
		input.close();
	}
	public static void drawCard() // method draws another card into a hand (arraylist)
	{
		boolean cont = true;
		while(cont)
		{
			int num = (int)(Math.random()*53) + 1;
			if(!allCards.contains(num))
			{
				allCards.add(num);
				cont = false;
			}
		}
	}
	public static int calculateTotal(ArrayList<Card> list) // method returns the total value of a hand (arraylist)
	{
		int total = 0;
		for(int i = 0; i < list.size(); i++)
			total += list.get(i).getValue();
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getValue() == 11 && total > 21) // Reduce an Ace of value 11 to an Ace of value 1 when necessary
			{
				list.get(i).reduceAce();
				total -= 10;
			}
		}
		return total;
	}
	public static String playerHit(ArrayList<Card> cards) /* method adds a card to player's hand, removes the drawn card from the deck of cards 
	calculates total to verify the total value of the player's hand and reinitiates "hit or stand" of the total is less than 21*/
	{
		Scanner input = new Scanner(System.in);
		String reply = "";
		drawCard();
		cards.add(new Card(allCards.get(allCards.size()-1)));
		println("You got the " + cards.get(cards.size()-1));
		playerTotal = calculateTotal(cards);
		if(playerTotal > 21)
		{	
			println("Your total is " + playerTotal);
			reply = "stand";
		}
		else if(playerTotal == 21)
		{
			println("Your total is 21.");
			reply = "stand";
		}
		else
		{
			println("Your total is " + playerTotal + ".");
			println("Would you like to hit or stand?");
			reply = input.nextLine().toLowerCase();
		}
		while(!(reply.equals("hit") || reply.equals("stand")))
		{
			print("Please enter hit or stand: ");
			reply = input.nextLine().toLowerCase();
		}
		return reply;
	}
	public static int split(int i) { /* method draws another card into each hand, calculates the total, prints the total of each to the player,
	and asks the player if he/she would like to draw again into each hand*/
		Scanner input = new Scanner(System.in);
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(playerCards.get(i));
		drawCard();
		hand.add(new Card(allCards.get(allCards.size()-1)));
		println("Hand " + (i+1) + ": " + hand.get(0) + ", " + hand.get(1));
		int handTotal = calculateTotal(hand);
		println("Hand total is " + handTotal);
		println("Would you like to hit or stand?");
		String reply = input.nextLine().toLowerCase();
		while(!(reply.equals("hit") || reply.equals("stand")))
		{
			print("Please enter hit or stand: ");
			reply = input.nextLine().toLowerCase();
		}
		while(reply.equals("hit"))
		{
			reply = playerHit(hand);
		}
		return calculateTotal(hand);
	}

	public static void dealer() // method determines and displays what cards the dealer's hand holds and calculates its total
	{
		while(dealerTotal <= 14)
		{
			drawCard();
			dealerCards.add(new Card(allCards.get(allCards.size()-1)));
			println("Dealer hit and got the " + dealerCards.get(dealerCards.size()-1));
			dealerTotal = calculateTotal(dealerCards);
		}
		while(dealerTotal < 18 && playerTotal <= 21 && playerTotal >= 16)
		{
			drawCard();
			dealerCards.add(new Card(allCards.get(allCards.size()-1)));
			println("Dealer hit and got the " + dealerCards.get(dealerCards.size()-1));
			dealerTotal = calculateTotal(dealerCards);
		}
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
