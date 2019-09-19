package shalygin;

import shalygin.board.Board;

public class Reversi {

    public static void main(String[] args) {

        Board board = Board.createStandardBoard();
        System.out.println(board);

        Gui gui = new Gui();
    }
}
