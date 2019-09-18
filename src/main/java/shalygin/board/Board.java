package shalygin.board;

import shalygin.piece.Piece;
import shalygin.piece.Team;
import shalygin.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    
    private final List<Tile> board;
    private final Player currentPlayer;


    public Board(final Builder builder) {
        this.board = createBoard(builder);
        currentPlayer = null; // TODO: 9/18/2019  

    }

    private List<Tile> createBoard(Builder builder) {
        final Tile[] tiles = new Tile[63];
        List<Tile> tileList = new ArrayList<Tile>();
        for (int i = 0; i < 63; i++) {
            tiles[i] = Tile.create(i, builder.config.get(i));
            tileList.add(tiles[i]);
        }
        return tileList;
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


    public static class Builder {

        Map<Integer, Piece> config;
        Team nextPlayer;
        public Builder() { this.config = new HashMap<>(); }

        public Builder setPiece(final Piece piece) {
            this.config.put(piece.getPosition(), piece);
            return this;
        }

        public Builder setPlayer(final Team nextPlayer) {
            this.nextPlayer = nextPlayer;
            return this;
        }

        public Board build() { return new Board(this); }

    }
}
