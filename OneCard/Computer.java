package OneCard;

import java.util.*;

/**
 * Implementing a Computer Class. Computer class inherits Player Class and has
 * method to choose which card to play.
 * 
 * 20121165 김재희 소프트웨어프로젝트 (03) - 이남규 교수님 2018-06-03
 */

class Computer extends Player {
	private ArrayList<Integer> availableIndex;

	public Computer() {
		hand = new ArrayList<Card>();
		availableIndex = new ArrayList<Integer>();
	}

	public void checkAvailable(Card peekCard) {
		for (int i = 0; i < availableIndex.size(); ++i)
			if (canPlay(hand.get(i), peekCard))
				availableIndex.add(i);
	}

	public Card play() {
		int chosenCard = availableIndex.get(new Random().nextInt(availableIndex.size()));
		availableIndex.clear();
		return super.play(chosenCard);
	}
}
