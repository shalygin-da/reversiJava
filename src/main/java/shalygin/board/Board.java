package shalygin.board;

import shalygin.piece.Piece;
import shalygin.piece.Team;
import shalygin.player.BlackPlayer;
import shalygin.player.Player;
import shalygin.player.WhitePlayer;

import java.util.*;

public class Board {

    private final List<Tile> board;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    private Player currentPlayer;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final List<Move> moves;
    private final Builder builder;


    Board(final Builder builder) {
        this.board = createBoard(builder);
        this.whitePlayer = new WhitePlayer(this);
        this.blackPlayer = new BlackPlayer(this);
        this.currentPlayer = builder.nextTeam.getPlayer(this.whitePlayer, this.blackPlayer);
        this.whitePieces = calcPieces(this.board, Team.WHITE);
        this.blackPieces = calcPieces(this.board, Team.BLACK);
        this.moves = currentPlayer.findMoves();
        this.builder = builder;

    }

    private Collection<Piece> calcPieces(List<Tile> board, Team team) {
        List<Piece> pieces = new ArrayList<>();
        for (final Tile tile : board)
            if (tile.isOccupied()) {
                final Piece piece = tile.getPiece();
                if (piece.getTeam() == team)
                    pieces.add(piece);
            }
        return pieces;
    }

    public Tile getTile(int id) {
        return board.get(id);
    }

    public static Board createStandardBoard() {
        final Builder builder = new Builder();
        builder.setPiece(new Piece(Team.BLACK, 27));
        builder.setPiece(new Piece(Team.WHITE, 28));
        builder.setPiece(new Piece(Team.BLACK, 36));
        builder.setPiece(new Piece(Team.WHITE, 35));
        builder.setPlayingTeam(Team.BLACK);
        return new Board(builder);
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

    public Player getWhitePlayer() { return this.whitePlayer; }

    public Player getBlackPlayer() { return this.blackPlayer; }

    public Player getCurrentPlayer() { return currentPlayer; }

    public List<Move> getMoves() { return moves; }

    List<Tile> getBoard() { return board; }

    public List<Integer> getDestTiles() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < getMoves().size(); i++) list.add(getMoves().get(i).getDestTile());
        return list;
    }

    public Board changePlayer() {
        Builder newBuilder = this.builder;
        builder.setPlayingTeam(currentPlayer.getTeam().getOpposite());
        return new Board(builder);
    }

    static class Builder {

        Map<Integer, Piece> config;
        Team nextTeam;

        Builder() {
            this.config = new HashMap<>();
        }

        void setPiece(final Piece piece) {
            this.config.put(piece.getPosition(), piece);
        }

        void setPlayingTeam(final Team nextTeam) {
            this.nextTeam = nextTeam;
        }
    }
}
