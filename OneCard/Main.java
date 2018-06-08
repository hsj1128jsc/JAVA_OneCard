package OneCard;

import java.util.*;

/**
 * Implementing Main Class. The Main Class supports you start and finish the
 * game.
 * 
 * 20121165 김재희 소프트웨어프로젝트 (03) - 이남규 교수님 2018-06-08
 */

public class Main {

	static int computerNumber;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initGame();
		startGame();
		// test1();
		// test2();
		// test3();
	}

	public static void initGame() {
		Scanner input = new Scanner(System.in);
		computerNumber = 0;
		while (computerNumber > 3 || computerNumber < 1) {
			System.out.print("Enter number of computer(1 ~ 3) : ");
			computerNumber = input.nextInt();
		}
	}

	public static void startGame() {
		GameFiled field = new GameFiled();
		Player[] player = createPlayer();

		passCard(player, field);

		while (field.getLosePlayerNum() < computerNumber) {
			if (player[field.getNowTurn()].getLose())
				field.setNowTurn(nextTurn(field.getNowTurn(), field.getDirection()));
			else {
				player[field.getNowTurn()].playTurn(field);
				if (player[field.getNowTurn()].getLose())
					field.setLosePlayerNum(field.getLosePlayerNum() + 1);
				field.setNowTurn(nextTurn(field.getNowTurn(), field.getDirection()));
			}
		}
	}

	public static int nextTurn(int now, int dir) {
		return (now + dir) % (computerNumber + 1);
	}

	public static void passCard(Player p[], GameFiled field) {
		for (int i = 0; i < computerNumber + 1; ++i)
			for (int j = 0; j < 7; ++j)
				p[i].push(field.deck.pop());
	}

	public static Player[] createPlayer() {
		Player[] p = new Player[computerNumber + 1];
		p[0] = new Player();
		for (int i = 1; i < computerNumber + 1; ++i)
			p[i] = new Computer();
		return p;
	}
}