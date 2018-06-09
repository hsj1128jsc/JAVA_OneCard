package OneCard;

import java.util.*;

/**
 * Implementing a Computer Class. Computer class inherits Player Class and has
 * method to choose which card to play.
 * 
 * 20121165 김재희 소프트웨어프로젝트 (03) - 이남규 교수님 2018-06-08
 */

class Computer extends Player {
	private String name;
	private ArrayList<Integer> availableIndex;

	public Computer(String name) {
		this.name = name;
		hand = new ArrayList<Card>();
		availableIndex = new ArrayList<Integer>();
	}

	public void checkAvailable(GameField field) {
		for (int i = 0; i < hand.size(); ++i)
			if (canPlay(hand.get(i), field))
				availableIndex.add(i);
	}

	@Override
	public void showHand() {
		System.out.println(getName() + " have " + getSize() + " cards.");
		System.out.println("");
	}

	@Override
	public void playTurn(GameField field) {
		checkAvailable(field);
		if (availableIndex.isEmpty()) {
			System.out.println(getName() + " draws " + field.getDrawingCount() + " cards");
			cardDraw(field);
		} else {
			int chosenCard = availableIndex.get(new Random().nextInt(availableIndex.size()));
			availableIndex.clear();
			field.stack.push(play(chosenCard));
			field.setChangingSuit(false);
			System.out.println(getName() + " plays " + field.stack.peek());
			if (field.stack.peek().canAttack()) {
				field.setOnAttack(true);
				field.setDrawingCount(field.attackStack(field.stack.peek())
						+ (field.getDrawingCount() == 1 ? 0 : field.getDrawingCount()));
			}
			if (field.stack.peek().canSpecialAction()) {
				if (field.stack.peek().getRank() == "7")
					autoChangeSuit(field);
				else
					field.specialAction(field.stack.peek());
			}
		}
		showHand();
	}

	private void autoChangeSuit(GameField field) {
		int[] suitCount = new int[4];
		int maxv = 0;
		int selectedSuit = 0;
		String[] suit = { "♠", "♦", "♥", "♣" };
		for (int i = 0; i < getSize(); ++i)
			for (int j = 0; j < 4; ++j)
				if (suit[j] == hand.get(i).getSuit())
					suitCount[j]++;
		for (int i = 0; i < 4; ++i)
			if (suitCount[i] > maxv) {
				maxv = suitCount[i];
				selectedSuit = i;
			}
		field.setChangingSuit(true);
		field.setTempSuit(suit[selectedSuit]);
		System.out.println(getName() + " sets a suit as " + suit[selectedSuit]);
	}

	public String getName() {
		return name;
	}
}
