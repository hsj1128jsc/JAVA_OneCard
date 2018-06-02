package OneCard;

public class Player {
	private Card[] hand = new Card[20];
	private int size;

	/** Return and remove the chosen card from the hand */
	public Card play(int index) {
		Card temp = hand[index];
		while (index + 1 < size)
			hand[index] = hand[index++ + 1];
		--size;
		return temp;
	}

	/** Show all cards into the hand*/
	public void showHand() {
		for (int i = 0; i < size; ++i)
			System.out.println(hand[i].getSuit() + " " + hand[i].getRank());
	}
	
	/** Push a new card into the hand
	 	If the number of cards in the hand over 20, player loses*/
	public void push(Card c) {
		if (size >= 20)
			lose();
		else
			hand[size++] = c;
	}
	
	/** Test whether the hand is empty */
	public boolean empty() {
		return size == 0;
	}

	/** Return the number of cards in the hand */
	public int getSize() {
		return size;
	}

	public void lose() {
		
	}
}
