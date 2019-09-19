package shalygin.board;

import shalygin.piece.Piece;
import shalygin.piece.Team;
import shalygin.player.Player;

import java.util.*;

public class Board {
    
    public final List<Tile> board;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    public final Player currentPlayer;


    public Board(final Builder builder) {
        this.board = createBoard(builder);
        this.currentPlayer = builder.nextPlayer;
        this.whitePieces = calcPieces(this.board, Team.WHITE);
        this.blackPieces = calcPieces(this.board, Team.BLACK);
    }

    private Collection<Piece> calcPieces(List<Tile> board, Team team) {
        List<Piece> pieces = new ArrayList<>();
        for (final Tile tile : board) {
            if (tile.isOccupied()) {
                final Piece piece = tile.getPiece();
                if (piece.getTeam() == team) {
                    pieces.add(piece);
                }
            }
        }
        return pieces;
    }

    public Tile getTile(int id) { return board.get(id); }

    public static Board createStandardBoard() {
        final Builder builder = new Builder();
        builder.setPiece(new Piece(Team.BLACK, 27));
        builder.setPiece(new Piece(Team.WHITE, 28));
        builder.setPiece(new Piece(Team.BLACK, 36));
        builder.setPiece(new Piece(Team.WHITE, 35));
        builder.setPlayer(Team.WHITE);
        return builder.build();
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

    public Collection<Piece> getWhitePieces() { return this.whitePieces; }

    public Collection<Piece> getBlackPieces() { return this.blackPieces; }


    public static class Builder {

        Map<Integer, Piece> config;
        Player nextPlayer;
        Team nextTeam;
        public Builder() { this.config = new HashMap<>(); }

        public Builder setPiece(final Piece piece) {
            this.config.put(piece.getPosition(), piece);
            return this;
        }

        public Builder setPlayer(final Team nextTeam) {
            this.nextTeam = nextPlayer.getTeam();
            return this;
        }

        public Board build() { return new Board(this); }

    }
}
