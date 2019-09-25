package shalygin.board;

import shalygin.piece.Piece;

import java.util.List;

public class Move {

    protected static Board reversiBoard;
    protected final Piece placedPiece;
    protected final int dest;

    private Move(final Board reversiBoard, final Piece piece, final int dest) {
        this.reversiBoard = reversiBoard;
        this.dest = dest;
        this.placedPiece = piece;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Move)) return false;
        final Move otherMove = (Move) other;
        return this.dest == otherMove.dest && this.placedPiece == otherMove.placedPiece;
    }

    @Override
    public int hashCode() {
        return 31 * dest * 31 * placedPiece.hashCode();
    }

    public static Board execute(final Board board, int destTile) {
        final Board.Builder builder = new Board.Builder();
        final Piece placedPiece = new Piece(board.getCurrentPlayer().getTeam(), destTile);

        for (final Piece piece : board.getCurrentPlayer().getPieces()) builder.setPiece(piece);
        for (final Piece piece : board.getCurrentPlayer().getOpponent().getPieces()) builder.setPiece(piece);
        builder.setPiece(placedPiece);
        System.out.println("placed Piece at " + destTile);
        if (placedPiece.getRow() > 1) {
            for (int i = 0; i < placedPiece.getRow() - 1; i++) { //handling columns going up from placedPiece
                Tile checkTile = board.getTile(destTile - (8 * i));
                if (checkTile.isOccupied() && checkTile.getPiece().getTeam() == placedPiece.getTeam()) {
                    for (int t = checkTile.getPiece().getRow(); t < placedPiece.getRow(); t++) {
                        if (board.getTile(t).getPiece() != null &&
                                board.getTile(t).getPiece().getTeam() != placedPiece.getTeam()) {
                            board.getTile(t).getPiece().team = board.getTile(t).getPiece().getTeam().getOpposite();
                        }
                    }
                }
            }
        }
        if (placedPiece.getRow() < 6) {
            for (int i = 2 + placedPiece.getRow(); i <= 7; i++) { //handling columns going down from placedPiece
                Tile checkTile = board.getTile(destTile + (8 * (i - placedPiece.getRow())));
                if (checkTile.isOccupied() && checkTile.getPiece().getTeam() == placedPiece.getTeam()) {
                    for (int t = placedPiece.getRow(); t < checkTile.getPiece().getRow(); t++) {
                        if (board.getTile(t).getPiece() != null &&
                                board.getTile(t).getPiece().getTeam() != placedPiece.getTeam()) {
                            board.getTile(t).getPiece().team = board.getTile(t).getPiece().getTeam().getOpposite();
                        }
                    }
                }
            }
        }
        if (placedPiece.getColumn() > 1) {
            for (int i = 0; i < placedPiece.getColumn() - 1; i++) {//handling rows going right from placedPiece
                Tile checkTile = board.getTile(destTile - i);
                if (checkTile.isOccupied() && checkTile.getPiece().getTeam() == placedPiece.getTeam()) {
                    for (int t = checkTile.getPiece().getColumn(); t < placedPiece.getColumn(); t++) {
                        if (board.getTile(t).getPiece() != null &&
                                board.getTile(t).getPiece().getTeam() != placedPiece.getTeam()) {
                            board.getTile(t).getPiece().team = board.getTile(t).getPiece().getTeam().getOpposite();
                        }
                    }
                }
            }
        }
        if (placedPiece.getColumn() < 6) {
            for (int i = placedPiece.getColumn() + 2; i <= 7; i++) {//handling rows going left from placedPiece
                Tile checkTile = board.getTile(destTile + i);
                if (checkTile.isOccupied() && checkTile.getPiece().getTeam() == placedPiece.getTeam()) {
                    for (int t = placedPiece.getColumn(); t < checkTile.getPiece().getColumn(); t++) {
                        if (board.getTile(t).getPiece() != null &&
                                board.getTile(t).getPiece().getTeam() != placedPiece.getTeam()) {
                            board.getTile(t).getPiece().team = board.getTile(t).getPiece().getTeam().getOpposite();
                        }
                    }
                }
            }
        }
        builder.setPlayer(board.getCurrentPlayer().getOpponent().getTeam());
        return new Board(builder);
    }

    public static void checkEndGame(final Board board) {
        if (!board.getCurrentPlayer().hasMoves()) {
            final Board.Builder builder = new Board.Builder();
            builder.setPlayer(board.getCurrentPlayer().getOpponent().getTeam());
        }
        if (!board.getCurrentPlayer().hasMoves()) endGame(board);
    }

    public static void endGame(final Board board) {
        int x = 0;
        int y = 0;
        for (Tile tile : board.getBoard()) {
            if (tile.getPiece()!= null && tile.getPiece().getTeam().isWhite()) x += 1;
            else if (tile.getPiece()!= null && tile.getPiece().getTeam().isBlack()) y += 1;
        }
        if (x > y) System.out.println("Team White won!");
        if (y > x) System.out.println("Team Black won!");
        if (x == y) System.out.println("It's a Draw!");
    }
}
