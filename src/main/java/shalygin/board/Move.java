package shalygin.board;

import shalygin.piece.Piece;

public class Move {

    protected final Board reversiBoard;
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

    public Board execute() {
        final Board.Builder builder = new Board.Builder();
        final Piece placedPiece = new Piece(reversiBoard.currentPlayer.getTeam(), dest);
        builder.setPiece(placedPiece);
            // TODO: 9/19/2019 check if pieces can be overturned
        for (final Piece piece : this.reversiBoard.currentPlayer.getPieces()) {
            if (!this.placedPiece.equals(piece)) builder.setPiece(piece);
        }
        for (final Piece piece : this.reversiBoard.currentPlayer.getOpponent().getPieces()) builder.setPiece(piece);
    }

}
