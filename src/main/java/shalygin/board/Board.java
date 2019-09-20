package shalygin.board;

import shalygin.piece.Piece;
import shalygin.piece.Team;
import shalygin.player.BlackPlayer;
import shalygin.player.Player;
import shalygin.player.WhitePlayer;

import java.util.*;

public class Board {
    
    public final List<Tile> board;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    public final Player currentPlayer;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;


    public Board(final Builder builder) {
        this.board = createBoard(builder);
        this.currentPlayer = builder.nextPlayer;
        this.whitePlayer = new WhitePlayer(this);
        this.blackPlayer = new BlackPlayer(this);
        this.whitePieces = calcPieces(this.board, Team.WHITE);
        this.blackPieces = calcPieces(this.board, Team.BLACK);
    }

    private Collection<Piece> calcPieces(List<Tile> board, Team team) {
        List<Piece> pieces = new ArrayList<>();
        for (final Tile tile : board) {
            if (tile.isOccupied()) {
                if (tile.getPiece().getTeam() == team) {
                    pieces.add(tile.getPiece());
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
        builder.setPlayer(Team.BLACK);
        return builder.build();
    }

    private List<Tile> createBoard(Builder builder) {
        final Tile[] tiles = new Tile[64];
        List<Tile> tileList = new ArrayList<>();
        for (int i = 0; i < 64; i++) {
            tiles[i] = Tile.create(i, builder.config.get(i));
            tileList.add(tiles[i]);
        }
        return tileList;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 64; i++) {
            final String tileInfo = this.board.get(i).toString();
            builder.append(String.format("%3s", tileInfo));
            if ((i + 1) % 8 == 0) builder.append("\n");
        }
        return builder.toString();
    }

    public Collection<Piece> getWhitePieces() { return this.whitePieces; }

    public Collection<Piece> getBlackPieces() { return this.blackPieces; }

    public Player whitePlayer() {
        return this.whitePlayer;
    }

    public Player blackPlayer() {
        return this.blackPlayer;
    }

    public List<Integer> initColumn(int columnNumber) {
        List<Integer> column = new ArrayList<>();
        do {
            column.add(columnNumber);
            columnNumber += 8;
        } while (columnNumber < 64);
        return column;
    }

    public List<Integer> initRow(int rowNumber) {
        List<Integer> row = new ArrayList<>();
        do {
            row.add(rowNumber);
            rowNumber++;
        } while (rowNumber % 8 != 0);
        return row;
    }

    public List<Integer> initCrossLtoR(int position) {
        List<Integer> cross = new ArrayList<>();
        int pos = position;
        do {
            cross.add(pos - 9);
            pos -= 9;
        } while (pos > 0);
        pos = position;
        do {
            cross.add(pos + 9);
            pos += 9;
        } while (pos < 64);
        return cross;
    }

    public List<Integer> initCrossRtoL(int position) {
        List<Integer> cross = new ArrayList<>();
        int pos = position;
        do {
            cross.add(pos - 7);
            pos -= 7;
        } while (pos > 0);
        pos = position;
        do {
            cross.add(pos + 7);
            pos += 7;
        } while (pos < 64);
        return cross;
    }

    public void endGame() {
        int x = 0;
        int y = 0;
        for (Tile tile : board) {
            if (tile.getPiece().getTeam().isWhite()) x += 1;
            else if (tile.getPiece().getTeam().isBlack()) y += 1;
        }
        if (x > y) System.out.println("Whites won!");
        if (y > x) System.out.println("Blacks won!");
        if (x == y) System.out.println("It's a Draw!");
    }


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
            this.nextTeam = nextTeam;
            return this;
        }

        public Board build() { return new Board(this); }

    }
}
