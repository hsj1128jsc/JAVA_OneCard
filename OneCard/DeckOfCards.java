package OneCard;

class DeckOfCards {

	private Card[] elements;
	private int size;
	public static final int DEFAULT_CAPACITY = 54;

	/** Construct a deck with the default capacity 54 */
	public DeckOfCards() {
		this(DEFAULT_CAPACITY);
	}

	/** Construct a deck with the specified maximum capacity */
	public DeckOfCards(int capacity) {
		elements = new Card[capacity];
	}

	/** Push a new card into the top of the deck */
	public void push(Card inCard) {
		elements[size++] = inCard;
	}

	/** Return and remove the top element from the deck */
	public Card pop() {
		return elements[--size];
	}

	/** Return the top element from the deck */
	public Card peek() {
		return elements[size - 1];
	}

	/** Test whether the deck is empty */
	public boolean empty() {
		return size == 0;
	}

	/** Return the number of elements in the deck */
	public int getSize() {
		return size;
	}

	/** Shuffle the cards */
	public void shufflingCard() {
		int length = this.getSize();
		for (int i = 0; i < length; i++) {
			// Generate an index randomly
			int index = (int) (Math.random() * length);
			Card temp = elements[i];
			elements[i] = elements[index];
			elements[index] = temp;
		}
	}
}
