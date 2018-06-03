package OneCard;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test1();
		// test2();
		test3();
	}

	public static DeckOfCards makeDeck() {
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

	public static void test1() {
		// Test for making and shuffling deck
		DeckOfCards grave = makeDeck();
		grave.shufflingCard();
		while (!grave.empty()) {
			Card top = grave.pop();
			System.out.println(top.toString());
		}
		System.out.println("-------------");
		grave = makeDeck();
		grave.shufflingCard();
		while (!grave.empty()) {
			Card top = grave.pop();
			System.out.println(top.toString());
		}
	}

	public static void test2() {
		// Test for player class
		Player p = new Player();
		DeckOfCards grave = makeDeck();
		grave.shufflingCard();

		for (int i = 0; i < 30; ++i)
			p.push(grave.pop());
		p.showHand();
		System.out.println("-----------------");

		for (int i = 0; i < p.getSize(); ++i) {
			int temp = new Random().nextInt(p.getSize());
			Card c = p.play(temp);
			System.out.println(c.toString());
			System.out.println(p.getSize());
		}
		p.showHand();
	}

	public static void test3() {
		// Test for Computer Class
		DeckOfCards deck = makeDeck();

		Player player = new Player();
		Computer com[] = new Computer[3];
		for (int j = 0; j < 3; ++j)
			com[j] = new Computer();

		for (int i = 0; i < 7; ++i) {
			player.push(deck.pop());
			for (int j = 0; j < 3; ++j)
				com[j].push(deck.pop());
		}

		player.showHand();
		System.out.println("------------");
		for (int j = 0; j < 3; ++j) {
			com[j].showHand();
			System.out.println("------------");
		}
	}
}
