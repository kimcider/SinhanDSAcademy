package omok;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {
	private Player user;
	private Player[] player;
	private Player computer;
	private Board board;
	private static final long serialVersionUID = 1L;
    public ControllerServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		user = new Player("사용자", "O");
		computer = new Player("컴퓨터", "X");
		player = new Player[2];
		player[0] = user;
		player[1] = computer;
		board = new Board(19);
	}

	public void destroy() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		Position pos = new Position((String)request.getParameter("row"), (String)request.getParameter("col"));
		int turn = Integer.valueOf((String)request.getParameter("turn"));
		System.out.println("turn: " + request.getParameter("turn") + " row: " + pos.getRow() + " col: " + pos.getCol());
		
		if(board.canPlaceStone(pos) && board.checkTurn(turn)) {
			board.placeStone(pos, player[turn % 2]);
			if(checkEnd(pos, player[turn % 2])) {
				writer.write("victory");
				return;
			}
			writer.write("placable");
		}else {
			writer.write("unplacable");
		}
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
			if (board.getStone(pos).equals(player.getStone())) {
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
			if (board.getStone(pos).equals(player.getStone())) {
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
				if (board.getStone(pos).equals(player.getStone())) {
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
					if (board.getStone(pos).equals(player.getStone())) {
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
				if (board.getStone(pos).equals(player.getStone())) {
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
				if (board.getStone(pos).equals(player.getStone())) {
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
