package OneCard;

import java.util.*;

/**
 * Implementing a Player Class. Player can have less than 20 cards.
 * If player's card over 20, player lose.
 * 
 * 20121165 김재희 소프트웨어프로젝트 (03) - 이남규 교수님 2018-06-12
 */

class Player {
	private String name;
	protected ArrayList<Card> hand;
	protected boolean lose = false;

	public Player() {
		hand = new ArrayList<Card>();
		name = "Player";
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
			System.out.println("" + (i + 1) + ". " + hand.get(i).toString() + "\t");
		}
	}

	/**
	 * Push a new card into the hand.
	 * If the number of cards in the hand over 20, player loses.
	 */
	protected void push(Card c) {
		if (hand.size() >= 20)
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

	/** Return whether the chosen card can be played */
	protected boolean canPlay(Card chosen, GameField field) {
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

	/** Notification when a player loses */
	protected void lose() {
		System.out.println(getName() + " Loses!!");
		System.out.println("");
		lose = true;
	}

	public boolean getLose() {
		return this.lose;
	}

	/** Players play their turn. */
	public void playTurn(GameField field) {
		System.out.println("Top : " + field.top().toString());
		showHand();
		Scanner input = new Scanner(System.in);
		int selectedIndex;
		System.out.print("Select index of card to play or enter '0' to draw ");
		System.out.println(field.getDrawingCount() + " cards : ");
		while (true) {
			selectedIndex = input.nextInt();
			if (selectedIndex < 0 || selectedIndex > getSize()) {
				System.out.println("Invalid input");
				continue;
			}
			if (selectedIndex == 0) {
				cardDraw(field);
				break;
			}
			// If selected card can be played, player plays that card.
			// If the card can attack or do a special action, do it.
			if (canPlay(hand.get(selectedIndex - 1), field)) {
				field.stack.push(play(selectedIndex - 1));
				field.setChangingSuit(false);
				if (field.stack.peek().canAttack()) {
					field.setOnAttack(true);
					field.setDrawingCount(field.attackStack(field.stack.peek())
							+ (field.getDrawingCount() == 1 ? 0 : field.getDrawingCount()));
				}
				if (field.stack.peek().canSpecialAction())
					field.specialAction(field.stack.peek());
				break;
			} else
				System.out.println("Selected card can not be played.");
		}

	}

	/**
	 * The player draws as many cards as are accumulated in the drawingCount.
	 * And drawingCount is initialized to 1.
	 */
	protected void cardDraw(GameField field) {
		int drawingCount = 0;
		while (drawingCount++ < field.getDrawingCount() && !getLose()) {
			push(field.deck.pop());
			if (field.isDeckEmpty())
				field.shufflingDeck();
		}
		field.setDrawingCount(1);
		field.setOnAttack(false);
		if (getLose()) {
			while (!empty())
				field.deck.push(play(0));
			field.deck.shufflingCard();
		}
	}

	public String getName() {
		return name;
	}
}
