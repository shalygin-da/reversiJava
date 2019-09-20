package shalygin.board;

import shalygin.piece.Piece;

import java.util.List;

public class Move {

    protected static Board reversiBoard;
    protected final Piece placedPiece;
    protected final int dest;
    static int end = 0;

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

    public static Board execute(int destTile) {
        final Board.Builder builder = new Board.Builder();
        final Piece placedPiece = new Piece(reversiBoard.currentPlayer.getTeam(), destTile);

        List<Integer> column = reversiBoard.initColumn(placedPiece.getColumn());
        column.remove(placedPiece.getPosition());
        column.remove((placedPiece.getPosition() - 8));
        column.remove((placedPiece.getPosition() + 8));
        List<Integer> row = reversiBoard.initRow(placedPiece.getRow());
        row.remove(placedPiece.getPosition());
        row.remove((placedPiece.getPosition() - 1));
        row.remove((placedPiece.getPosition() + 1));
        List<Integer> crossLtoR = reversiBoard.initCrossLtoR(placedPiece.getPosition());
        crossLtoR.remove(placedPiece.getPosition());
        crossLtoR.remove((placedPiece.getPosition() - 9));
        crossLtoR.remove((placedPiece.getPosition() + 9));
        List<Integer> crossRtoL = reversiBoard.initCrossRtoL(placedPiece.getPosition());
        crossRtoL.remove(placedPiece.getPosition());
        crossRtoL.remove((placedPiece.getPosition() - 9));
        crossRtoL.remove((placedPiece.getPosition() + 9));

        builder.setPiece(placedPiece);
            for (int id : column) {
                Piece otherPiece = reversiBoard.getTile(id).getPiece();
                if (otherPiece.getTeam() == placedPiece.getTeam()) { // TODO: 9/19/2019 check if pieces can be overturned
                    if (otherPiece.) {

                    }
                }
            }
            for (int id : row) {
                Piece otherPiece = reversiBoard.getTile(id).getPiece();
                if (otherPiece.getTeam() == placedPiece.getTeam()) {
                    if (otherPiece.) {

                    }
                }
            }
            for (int id : crossLtoR) {
                Piece otherPiece = reversiBoard.getTile(id).getPiece();
                if (otherPiece.getTeam() == placedPiece.getTeam()) {
                    if (otherPiece.) {

                    }
                }
            }
            for (int id : crossLtoR) {
                Piece otherPiece = reversiBoard.getTile(id).getPiece();
                if (otherPiece.getTeam() == placedPiece.getTeam()) {
                    if (otherPiece.) {

                    }
                }
            }

            for (final Piece piece : reversiBoard.currentPlayer.getPieces()) {
                if (!placedPiece.equals(piece)) builder.setPiece(piece);
            }
            for (final Piece piece : reversiBoard.currentPlayer.getOpponent().getPieces()) builder.setPiece(piece);
                builder.setPlayer(reversiBoard.currentPlayer.getOpponent().getTeam());
            reversiBoard = null; // TODO: 9/20/2019 check if works, if not - revert changes
            return new Board(builder);
    }

}
