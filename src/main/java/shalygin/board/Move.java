package shalygin.board;

import shalygin.piece.Piece;

public class Move {

    protected final Board board;
    protected final Piece placedPiece;
    protected final int dest;

    private Move(final Board board, final Piece piece, final int dest) {
        this.board = board;
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

    public Board execute() {
        final Board.Builder builder = new Board.Builder();
        if (Board.initRow(placedPiece.position.getRow()))
        for (final Piece piece : this.board.currentPlayer().getPieces()) {
            if (!this.placedPiece.equals(piece)) builder.setPiece(piece);
        }
        for (final Piece piece : this.board.currentPlayer().getOpponent().getPieces) builder.setPiece(piece);
    }

}
