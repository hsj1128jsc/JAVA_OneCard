package OneCard;

import java.util.*;

/**
 * Implementing Main Class.
 * The Main Class supports you start and finish the game.
 * 
 * 20121165 김재희 소프트웨어프로젝트 (03) - 이남규 교수님 2018-06-12
 */

public class Main {

	static int computerNumber;

	public static void main(String[] args) {
		initGame();
		startGame();
	}

	/** Set the number of computer player */
	public static void initGame() {
		Scanner input = new Scanner(System.in);
		computerNumber = 0;
		while (computerNumber > 3 || computerNumber < 1) {
			System.out.print("Enter number of computer(1 ~ 3) : ");
			computerNumber = input.nextInt();
		}
		// Create game field and players to play game
	}

	public static void startGame() {
		GameField field = new GameField();
		Player[] player = createPlayer();
		passCard(player, field);
		playing(player, field);
	} 

	public static void playing(Player[] player, GameField field) {
		while (field.getLosePlayerNum() < computerNumber) {
			if (player[field.getNowTurn()].getLose())
				field.setNowTurn(nextTurn(field.getNowTurn(), field.getDirection()));
			else {
				// If player doesn't lose, play his turn.
				player[field.getNowTurn()].playTurn(field);
				if (player[field.getNowTurn()].getLose())
					field.setLosePlayerNum(field.getLosePlayerNum() + 1);
				else if (player[field.getNowTurn()].empty()) {
					// If player has no cards in hand, player wins.
					System.out.println(player[field.getNowTurn()].getName() + " Win!!");
					break;
				}

				// Set next player
				if (field.isOneMoreTurn())
					field.setOneMoreTurn(false);
				else if (field.isJumpTurn()) {
					field.setJumpTurn(false);
					field.setNowTurn(nextTurn(field.getNowTurn(), field.getDirection()));
					field.setNowTurn(nextTurn(field.getNowTurn(), field.getDirection()));
				} else
					field.setNowTurn(nextTurn(field.getNowTurn(), field.getDirection()));
			}
		}
	}

	public static Player[] createPlayer() {
		Player[] p = new Player[computerNumber + 1];
		p[0] = new Player();
		for (int i = 1; i < computerNumber + 1; ++i)
			p[i] = new Computer("com" + i);
		return p;
	}

	public static void passCard(Player p[], GameField field) {
		for (int i = 0; i < computerNumber + 1; ++i)
			for (int j = 0; j < 7; ++j)
				p[i].push(field.deck.pop());
	}

	public static int nextTurn(int now, int dir) {
		return (computerNumber + 1 + now + dir) % (computerNumber + 1);
	}
}