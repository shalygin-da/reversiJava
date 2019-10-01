package shalygin.board;

import shalygin.piece.Piece;

public class Move {

    public static Board execute(Board board, int destTile, boolean possible) {
        final Board.Builder builder = new Board.Builder();
        final Piece placedPiece = new Piece(board.getCurrentPlayer().getTeam(), destTile);

        if (possible) {
            for (final Piece piece : board.getCurrentPlayer().getPieces()) builder.setPiece(piece);
            for (final Piece piece : board.getCurrentPlayer().getOpponent().getPieces()) builder.setPiece(piece);
            builder.setPiece(placedPiece);
            builder.setPlayingTeam(board.getCurrentPlayer().getOpponent().getTeam());
            System.out.println("placed Piece at " + destTile);
            return new Board(builder);
        }
        else {
            for (final Piece piece : board.getCurrentPlayer().getPieces()) builder.setPiece(piece);
            for (final Piece piece : board.getCurrentPlayer().getOpponent().getPieces()) builder.setPiece(piece);
            builder.setPiece(placedPiece);
            builder.setPlayingTeam(board.getCurrentPlayer().getTeam());
            return new Board(builder);
        }
    }

    public static void checkEndGame(final Board board) {
        if (!board.getCurrentPlayer().hasMoves()) {
            final Board.Builder builder = new Board.Builder();
            builder.setPlayingTeam(board.getCurrentPlayer().getOpponent().getTeam());
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
