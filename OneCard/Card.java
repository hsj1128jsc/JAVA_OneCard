package OneCard;

/**
 * Implementing a Card Class.
 * Because there is no need to change information, it is implemented as an immutable class.
 * 
 * 20121165 김재희 소프트웨어프로젝트 (03) - 이남규 교수님 2018-06-12
 */

class Card {
	private String suit;
	private String rank;

	/** Construct a card with a specified suit and rank */
	public Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
	}

	/** Return the rank of this card */
	public String getRank() {
		return this.rank;
	}

	/** Return the suit of this card */
	public String getSuit() {
		return this.suit;
	}

	/** 'A','2' and 'Joker' can attack next player */
	public boolean canAttack() {
		if (rank == "A" || rank == "2" || suit == "Joker")
			return true;
		return false;
	}

	/** '7', 'J','Q' and 'K' have special ability */
	public boolean canSpecialAction() {
		if (rank == "7" || rank == "J" || rank == "Q" || rank == "K")
			return true;
		return false;
	}

	/** Return the string representation of this card */
	public String toString() {
		return suit + " " + rank;
	}
}
