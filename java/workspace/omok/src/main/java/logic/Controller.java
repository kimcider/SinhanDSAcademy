package logic;

import java.util.*;

public class Controller {
	private Player user;
	private Player computer;
	private Board board;
	private Scanner scanner;

	Controller() {
		scanner = new Scanner(System.in);
		init();
	}

	private void init() {
		user = new Player("사용자", "O");
		computer = new Player("컴퓨터", "X");
		board = new Board(19);
		play(board, user, computer);
	}

	private void play(Board board, Player user, Player computer) {
		Player[] player = new Player[2];
		player[0] = user;
		player[1] = computer;

		int turn = 0;
		while (true) {
			board.print();

			System.out.println(player[turn % 2].name + ">");
			String input = getInput();
			Position pos = getPosition(input);
			
			if (board.canPlaceStone(pos)) {
				board.placeStone(pos, player[turn % 2]);
				if (checkEnd(pos, player[turn % 2]) == true) {
					board.print();
					System.out.println(player[turn % 2].name + " is Win");
					return;
				}
			} else {
				System.out.println("그곳에 돌을 둘 수 없습니다.");
				continue;
			}
			turn++;
		}
	}

	private String getInput() {
		return scanner.nextLine().trim();
	}

	private Position getPosition(String input) {
		String[] inputArray = input.split(" ");

		int row, col;
		try {
			if (inputArray[0].toUpperCase().charAt(0) >= 'A' && inputArray[0].toUpperCase().charAt(0) <= 'Z') {
				row = Integer.valueOf(inputArray[1]);
				col = (int) inputArray[0].toUpperCase().charAt(0) - (int) 'A';
			} else {
				row = Integer.valueOf(inputArray[0]);
				col = (int) inputArray[1].toUpperCase().charAt(0) - (int) 'A';
			}
		} catch (Exception e) {
			row = 10000;
			col = 10000;
		}

		return new Position(row, col);
	}

	private boolean checkEnd(Position now, Player player) {
		int minRow, maxRow, minCol, maxCol;

		if (now.getRow() - 4 >= 0) {
			minRow = now.getRow() - 4;
		} else {
			minRow = 0;
		}

		if (now.getRow() + 4 < board.getSize()) {
			maxRow = now.getRow() + 4;
		} else {
			maxRow = board.getSize() - 1;
		}

		if (now.getCol() - 4 >= 0) {
			minCol = now.getCol() - 4;
		} else {
			minCol = 0;
		}

		if (now.getCol() + 4 < board.getSize()) {
			maxCol = now.getCol() + 4;
		} else {
			maxCol = board.getSize() - 1;
		}

		// 수평
		int counter = 0;
		for (int i = minCol; i <= maxCol; i++) {
			Position pos = new Position(now.getRow(), i);
			if (board.getStone(pos).equals(player.stone)) {
				counter++;
			} else {
				counter = 0;
			}
			if (counter == 5)
				return true;
		}
		// 수직
		counter = 0;
		for (int i = minRow; i <= maxRow; i++) {
			Position pos = new Position(i, now.getCol());
			if (board.getStone(pos).equals(player.stone)) {
				counter++;
			} else {
				counter = 0;
			}
			if (counter == 5)
				return true;
		}
		// 왼쪽위대각
		counter = 0;
		for (int i = 1; i < 5; i++) {
			if (now.getRow() - i >= minRow && now.getCol() - i >= minCol) {
				Position pos = new Position(now.getRow() - i, now.getCol() - i);
				if (board.getStone(pos).equals(player.stone)) {
					counter++;
				} else {
					break;
				}
			}

		}
		if (counter == 4)
			return true;
		else {
			for (int i = 1; i < 5; i++) {
				if (now.getRow() + i <= maxRow && now.getCol() + i <= maxCol) {
					Position pos = new Position(now.getRow() + i, now.getCol() + i);
					if (board.getStone(pos).equals(player.stone)) {
						counter++;
					} else {
						break;
					}
				}
			}
		}
		if (counter == 4)
			return true;

		// 왼쪽아래대각
		counter = 0;
		for (int i = 1; i < 5; i++) {
			if (now.getRow() - i >= minRow && now.getCol() + i <= maxCol) {
				Position pos = new Position(now.getRow() - i, now.getCol() + i);
				if (board.getStone(pos).equals(player.stone)) {
					counter++;
				} else {
					break;
				}
			}
		}
		if (counter == 4)
			return true;
		for (int i = 1; i < 5; i++) {
			if (now.getRow() + i <= maxRow && now.getCol() - i >= minRow) {
				Position pos = new Position(now.getRow() + i, now.getCol() - i);
				if (board.getStone(pos).equals(player.stone)) {
					counter++;
				} else {
					break;
				}
			}
		}
		if (counter == 4)
			return true;
		return false;
	}
}