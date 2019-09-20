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
        List<Integer> row = reversiBoard.initRow(placedPiece.getRow());
        List<Integer> crossLtoR = reversiBoard.initCrossLtoR(placedPiece.getPosition());
        List<Integer> crossRtoL = reversiBoard.initCrossRtoL(placedPiece.getPosition());

        builder.setPiece(placedPiece);
        if (placedPiece.getRow() > 1) {
            for (int i = 0; i < placedPiece.getRow() - 1; i++) { //handling columns going up from placedPiece
                Tile checkTile = reversiBoard.getTile(destTile - (8 * i));
                if (checkTile.isOccupied() && checkTile.getPiece().getTeam() == placedPiece.getTeam()) {
                    for (int t = checkTile.getPiece().getRow(); t < placedPiece.getRow(); t++) {
                        if (reversiBoard.getTile(t).getPiece().getTeam() != placedPiece.getTeam()) {
                            reversiBoard.getTile(t).getPiece().team = reversiBoard.getTile(t).getPiece().getTeam().getOpposite();
                        }
                    }
                }
            }
        }
        if (placedPiece.getRow() < 6) {
            for (int i = 2 + placedPiece.getRow(); i <= 7; i++) { //handling columns going down from placedPiece
                Tile checkTile = reversiBoard.getTile(destTile + (8 * (i - placedPiece.getRow())));
                if (checkTile.isOccupied() && checkTile.getPiece().getTeam() == placedPiece.getTeam()) {
                    for (int t = placedPiece.getRow(); t < checkTile.getPiece().getRow(); t++) {
                        if (reversiBoard.getTile(t).getPiece().getTeam() != placedPiece.getTeam()) {
                            reversiBoard.getTile(t).getPiece().team = reversiBoard.getTile(t).getPiece().getTeam().getOpposite();
                        }
                    }
                }
            }
        }
        if (placedPiece.getColumn() > 1) {
            for (int i = 0; i < placedPiece.getColumn() - 1; i++) {//handling rows going right from placedPiece
                Tile checkTile = reversiBoard.getTile(destTile - i);
                if (checkTile.isOccupied() && checkTile.getPiece().getTeam() == placedPiece.getTeam()) {
                    for (int t = checkTile.getPiece().getColumn(); t < placedPiece.getColumn(); t++) {
                        if (reversiBoard.getTile(t).getPiece().getTeam() != placedPiece.getTeam()) {
                            reversiBoard.getTile(t).getPiece().team = reversiBoard.getTile(t).getPiece().getTeam().getOpposite();
                        }
                    }
                }
            }
        }
        if (placedPiece.getColumn() < 6) {
            for (int i = placedPiece.getColumn() + 2; i <= 7; i++) {//handling rows going left from placedPiece
                Tile checkTile = reversiBoard.getTile(destTile + i);
                if (checkTile.isOccupied() && checkTile.getPiece().getTeam() == placedPiece.getTeam()) {
                    for (int t = placedPiece.getColumn(); t < checkTile.getPiece().getColumn(); t++) {
                        if (reversiBoard.getTile(t).getPiece().getTeam() != placedPiece.getTeam()) {
                            reversiBoard.getTile(t).getPiece().team = reversiBoard.getTile(t).getPiece().getTeam().getOpposite();
                        }
                    }
                }
            }
        }
            for (final Piece piece : reversiBoard.currentPlayer.getPieces()) {
                if (!placedPiece.equals(piece)) builder.setPiece(piece);
            }
            for (final Piece piece : reversiBoard.currentPlayer.getOpponent().getPieces()) builder.setPiece(piece);
                builder.setPlayer(reversiBoard.currentPlayer.getOpponent().getTeam());
            return new Board(builder);
    }

    public static void checkEndGame() {
        if (!reversiBoard.currentPlayer.hasMoves()) {
            final Board.Builder builder = new Board.Builder();
            builder.setPlayer(reversiBoard.currentPlayer.getOpponent().getTeam());
        }
        if (!reversiBoard.currentPlayer.hasMoves()) endGame();
    }

    public static void endGame() {
        int x = 0;
        int y = 0;
        for (Tile tile : reversiBoard.board) {
            if (tile.getPiece().getTeam().isWhite()) x += 1;
            else if (tile.getPiece().getTeam().isBlack()) y += 1;
        }
        if (x > y) System.out.println("Whites won!");
        if (y > x) System.out.println("Blacks won!");
        if (x == y) System.out.println("It's a Draw!");
    }
}
