package OneCard;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test1();
		// test2();
	}  
	
	public static DeckOfCards makeDeck() {
		DeckOfCards deck = new DeckOfCards(54);
		String[] suit = {"♠", "♦", "♥", "♣"};
		String[] rank = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K"};
		for(int i = 0;i<suit.length;++i)
			for(int j = 0;j<rank.length;++j)
				deck.push(new Card(suit[i],rank[j]));
		deck.push(new Card("Joker","Black"));
		deck.push(new Card("Joker","Red"));
		return deck;
	}
	
	public static void test1() {
		DeckOfCards grave = makeDeck();
		grave.shufflingCard();
		while(!grave.empty()) {
			Card top = grave.pop();
			System.out.println(top.getSuit() + " " + top.getRank());
		}
		System.out.println("-------------");
		grave = makeDeck();
		grave.shufflingCard();
		while(!grave.empty()) {
			Card top = grave.pop();
			System.out.println(top.getSuit() + " " + top.getRank());
		}
	}
	
	public static void test2() {
		Player p = new Player();
		DeckOfCards grave = makeDeck();
		grave.shufflingCard();
		
		for(int i = 0;i<30;++i)
			p.push(grave.pop());
		p.showHand();
		System.out.println("-----------------");
	
		for(int i = 0;i<p.getSize();++i) {
			int temp = new Random().nextInt(p.getSize());
			Card c = p.play(temp);
			System.out.println(c.getSuit() + " " + c.getRank());
			System.out.println(p.getSize());
		}
		p.showHand();
	}
}
