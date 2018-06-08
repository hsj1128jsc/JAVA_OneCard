package OneCard;

import java.util.*;

/**
 * Implementing a Computer Class. Computer class inherits Player Class and has
 * method to choose which card to play.
 * 
 * 20121165 ������ ����Ʈ����������Ʈ (03) - �̳��� ������ 2018-06-08
 */

class Computer extends Player {
	private ArrayList<Integer> availableIndex;

	public Computer() {
		hand = new ArrayList<Card>();
		availableIndex = new ArrayList<Integer>();
	}

	public void checkAvailable(Card peekCard) {
		for (int i = 0; i < hand.size(); ++i)
			if (canPlay(hand.get(i), peekCard))
				availableIndex.add(i);
	}

	public Card play(Card peekCard) {
		checkAvailable(peekCard);
		int chosenCard = availableIndex.get(new Random().nextInt(availableIndex.size()));
		availableIndex.clear();
		return super.play(chosenCard);
	}
	
	@Override
	public void playTurn(GameFiled f) {
		
	}
}
