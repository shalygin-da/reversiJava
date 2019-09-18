package shalygin.da.board;

import shalygin.da.player.Player;

import java.util.List;

public class Board {

    private final List<Tile> board;
    private final Player currentPlayer;


    public Board() {
        currentPlayer = null;
        board = null;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 63; i++) {
            final String tileInfo = this.board.get(i).toString();
            builder.append(String.format("%3s", tileInfo));
            if ((i + 1) % 8 == 0) builder.append("/n");
        }
        return builder.toString();
    }



}
