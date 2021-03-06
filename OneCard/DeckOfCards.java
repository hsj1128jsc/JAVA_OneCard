package OneCard;

import java.util.*;

/**
 * Implementing a DeckOfCards Class.
 * It is implemented based on the stack and shuffling is added.
 * 
 * 20121165 ������ ����Ʈ����������Ʈ (03) - �̳��� ������ 2018-06-12
 */

class DeckOfCards {
	private ArrayList<Card> elements;
	private int drawingCount;

	/** Construct a deck */
	public DeckOfCards() {
		elements = new ArrayList<Card>();
		setDrawingCount(1);
	}

	/** Push a new card into the top of the deck */
	public void push(Card card) {
		elements.add(card);
	}

	/** Return and remove the top element from the deck */
	public Card pop() {
		Card peek = peek();
		elements.remove(getSize() - 1);
		return peek;
	}

	/** Return the top element from the deck */
	public Card peek() {
		return elements.get(getSize() - 1);
	}

	/** Test whether the deck is empty */
	public boolean empty() {
		return elements.isEmpty();
	}

	/** Return the number of elements in the deck */
	public int getSize() {
		return elements.size();
	}

	/** Shuffle the cards */
	public void shufflingCard() {
		Collections.shuffle(elements);
	}

	/** Returns the number of cards to be drawn */
	public int getDrawingCount() {
		return drawingCount;
	}

	/**
	 * Set the number of cards to be drawn.
	 * When player play attack card, drawingCount is accumulated.
	 * And when the player draws cards, drawingCount is initialized.
	 */
	public void setDrawingCount(int drawingCount) {
		this.drawingCount = drawingCount;
	}
}
