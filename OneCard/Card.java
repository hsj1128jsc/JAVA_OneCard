package OneCard;

public class Card {
	private String rank;
	private String suit;

	/** Construct a card with a specified rank and suit */
	public Card(String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}

	/** Return the rank of this card */
	public String getRank() {
		return this.rank;
	}

	/** Return the suit of this card */
	public String getSuit() {
		return this.suit;
	}
}
