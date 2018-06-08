package OneCard;

/**
 * Implementing a GameField Class. GameField Class is the manager of the overall
 * part of the game area.
 * 
 * 20121165 김재희 소프트웨어프로젝트 (03) - 이남규 교수님 2018-06-08
 */

public class GameFiled {
	DeckOfCards stack;
	DeckOfCards deck;

	private int nowTurn;
	private int direction;
	private int losePlayerNum;
	private int drawingCount;
	private boolean changingSuit;
	private String tempSuit;
	private boolean onAttack;

	GameFiled() {
		stack = new DeckOfCards();
		deck = makeDeck();
		stack.push(deck.pop());
		setDrawingCount(1);
		setNowTurn(0);
		setDirection(1);
		setLosePlayerNum(0);
		setChangingSuit(false);
		setOnAttack(false);
	}

	DeckOfCards makeDeck() {
		DeckOfCards deck = new DeckOfCards();
		String[] suit = { "♠", "♦", "♥", "♣" };
		String[] rank = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
		for (int i = 0; i < suit.length; ++i)
			for (int j = 0; j < rank.length; ++j)
				deck.push(new Card(suit[i], rank[j]));
		deck.push(new Card("Joker", "Black"));
		deck.push(new Card("Joker", "Red"));
		deck.shufflingCard();
		return deck;
	}

	public int getNowTurn() {
		return nowTurn;
	}

	public void setNowTurn(int nowTurn) {
		this.nowTurn = nowTurn;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getLosePlayerNum() {
		return losePlayerNum;
	}

	public void setLosePlayerNum(int losePlayerNum) {
		this.losePlayerNum = losePlayerNum;
	}

	public boolean isChangingSuit() {
		return changingSuit;
	}

	public void setChangingSuit(boolean changingSuit) {
		this.changingSuit = changingSuit;
	}

	public String getTempSuit() {
		return tempSuit;
	}

	public void setTempSuit(String tempSuit) {
		this.tempSuit = tempSuit;
	}

	public boolean isOnAttack() {
		return onAttack;
	}

	public void setOnAttack(boolean onAttack) {
		this.onAttack = onAttack;
	}

	public int getDrawingCount() {
		return drawingCount;
	}

	public void setDrawingCount(int drawingCount) {
		this.drawingCount = drawingCount;
	}

	public Card top() {
		if (isChangingSuit()) {
			return new Card(getTempSuit(), "7");
		}
		return stack.peek();
	}

	public boolean compareCard(Card a) {
		if (a.getSuit() == "Joker" || top().getSuit() == "Joker") {
			if (a.getSuit() == "Joker")
				return checkJoker(a, top());
			else
				return checkJoker(top(), a);
		}
		if (a.getRank() == top().getRank())
			return true;
		if (a.getSuit() == top().getSuit())
			return true;
		return false;
	}

	private boolean checkJoker(Card a, Card b) {
		if (b.getSuit() == "Joker")
			return true;
		if (a.getRank() == "Black" && isBlack(b))
			return true;
		else if (a.getRank() == "Red" && isRed(b))
			return true;
		else
			return false;
	}

	private boolean isBlack(Card c) {
		if (isOnAttack() && c.getRank() != "A")
			return false;
		if (c.getSuit() == "♠" || c.getSuit() == "♣")
			return true;
		return false;
	}

	private boolean isRed(Card c) {
		if (isOnAttack() && c.getRank() != "A")
			return false;
		if (c.getSuit() == "♦" || c.getSuit() == "♥")
			return true;
		return false;
	}

	public boolean isDeckEmpty() {
		return deck.empty();
	}

	public void shufflingDeck() {
		Card temp = stack.pop();
		while (!stack.empty())
			deck.push(stack.pop());
		deck.shufflingCard();
		stack.push(temp);
		return;
	}

	public int attackStack(Card c) {
		if (c.getRank() == "A")
			return 3;
		else if (c.getRank() == "2")
			return 2;
		else
			return 5;
	}

	public void specialAction(Card c) {
		if (c.getRank() == "7")
			changeSuit();
		else if (c.getRank() == "K")
			oneMoreTurn();
		else if (c.getRank() == "J")
			jumpPlayer();
		else
			reverseDirection();
	}

	private void changeSuit() {
		// TODO Auto-generated method stub

	}

	private void oneMoreTurn() {
		// TODO Auto-generated method stub

	}

	private void jumpPlayer() {
		// TODO Auto-generated method stub

	}

	private void reverseDirection() {
		setDirection(-1 * getDirection());
	}
}
