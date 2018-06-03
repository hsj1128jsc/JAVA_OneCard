package OneCard;

import java.util.*;

/**
 * Implementing a Player Class. Player can have less than 20 cards. If player's
 * card over 20, player lose.
 * 
 * 20121165 김재희 소프트웨어프로젝트 (03) - 이남규 교수님 2018-06-03
 */

class Player {
	protected ArrayList<Card> hand;

	public Player() {
		hand = new ArrayList<Card>();
	}

	/** Return and remove the chosen card from the hand */
	public Card play(int index) {
		Card temp = hand.get(index);
		hand.remove(index);
		return temp;
	}

	/** Show all cards into the hand */
	public void showHand() {
		for (int i = 0; i < hand.size(); ++i)
			System.out.println(hand.get(i).toString());
	}

	/**
	 * Push a new card into the hand If the number of cards in the hand over 20,
	 * player loses
	 */
	public void push(Card c) {
		if (hand.size() >= 20)
			lose();
		else
			hand.add(c);
	}

	/** Test whether the hand is empty */
	public boolean empty() {
		return hand.size() == 0;
	}

	/** Return the number of cards in the hand */
	public int getSize() {
		return hand.size();
	}

	public boolean canPlay(Card chosen, Card peekCard) {
		if (peekCard == null)
			return true;
		if (chosen.getRank() == peekCard.getRank())
			return true;
		if (chosen.getSuit() == peekCard.getSuit())
			return true;
		if (chosen.getSuit() == "Joker")
			return true;
		return false;
	}

	public void lose() {

	}
}
