package OneCard;

import java.util.*;

/**
 * Implementing a Player Class. Player can have less than 20 cards. If player's
 * card over 20, player lose.
 * 
 * 20121165 김재희 소프트웨어프로젝트 (03) - 이남규 교수님 2018-06-08
 */

class Player {
	protected ArrayList<Card> hand;
	protected boolean lose = false;

	public Player() {
		hand = new ArrayList<Card>();
	}

	/** Return and remove the chosen card from the hand */
	protected Card play(int index) {
		Card temp = hand.get(index);
		hand.remove(index);
		return temp;
	}

	/** Show all cards into the hand */
	protected void showHand() {
		for (int i = 0; i < hand.size(); ++i) {
			System.out.print("" + (i + 1) + ". " + hand.get(i).toString() + "\t");
			if (i % 10 == 0)
				System.out.println("");
		}
	}

	/**
	 * Push a new card into the hand If the number of cards in the hand over 20,
	 * player loses
	 */
	protected void push(Card c) {
		if (hand.size() > 20)
			lose();
		else
			hand.add(c);
	}

	/** Test whether the hand is empty */
	protected boolean empty() {
		return hand.size() == 0;
	}

	/** Return the number of cards in the hand */
	protected int getSize() {
		return hand.size();
	}

	protected boolean canPlay(Card chosen, GameFiled field) {
		if (field.isOnAttack()) {
			if (!chosen.canAttack())
				return false;
			else {
				if (field.compareCard(chosen))
					return true;
				return false;
			}
		} else if (field.compareCard(chosen))
			return true;
		else
			return false;
	}

	protected void lose() {
		lose = true;
	}

	public boolean getLose() {
		return this.lose;
	}

	public void playTurn(GameFiled field) {
		showHand();
		Scanner input = new Scanner(System.in);
		int selectedIndex;
		while (true) {
			System.out.println("Select index of card to play or enter '0' to draw : ");
			selectedIndex = input.nextInt();
			if (selectedIndex < 0 || selectedIndex > getSize())
				continue;
			if (selectedIndex == 0) {
				cardDraw(field);
			}
			if (canPlay(hand.get(selectedIndex - 1), field)) {

			}
		}

	}

	private void cardDraw(GameFiled field) {
		int drawingCount = 0;
		while (drawingCount++ < field.getDrawingCount() && !getLose()) {
			push(field.deck.pop());
			if (field.isDeckEmpty())
				field.shufflingDeck();
		}
		field.setDrawingCount(1);
		if (getLose()) {
			while (!empty())
				field.deck.push(play(0));
			field.deck.shufflingCard();
		}
	}
}
