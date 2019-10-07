package shalygin.board;

import shalygin.Gui;
import shalygin.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class Move {

    private int destTile;
    private int dir;
    private Piece tgtPiece;

    /*  for dir:
        0 = UP
        1 = DOWN
        2 = LEFT
        3 = RIGHT
        4 = RL-DIAG-UPWARDS
        5 = RL-DIAG-DOWNWARDS
        6 = LR-DIAG-UPWARDS
        7 = LR-DIAG-DOWNWARDS */

    public Move(int coord, int dir, Piece tgtPiece) {
        this.destTile = coord;
        this.dir = dir;
        this.tgtPiece = tgtPiece;
    }

    int getDestTile() { return destTile; }
    private int getDir() { return dir; }
    private Piece getTgtPiece() { return tgtPiece; }

    public static Board execute(Board board, Move move) {
        final Board.Builder builder = new Board.Builder();
        final Piece placedPiece = new Piece(board.getCurrentPlayer().getTeam(), move.getDestTile());
        List<Piece> piecesToSet = (ArrayList<Piece>) board.getWhitePieces();
        piecesToSet.addAll(board.getBlackPieces());
        builder.setPiece(placedPiece);

        if (move.getDir() == 0) {
            for (int tileID = placedPiece.getPosition() + 8; tileID < move.getTgtPiece().getPosition(); tileID += 8) {
                Piece piece = board.getTile(tileID).getPiece();
                piece.changeTeam();
                builder.setPiece(piece);
                piecesToSet.remove(piece);
            }
        } else if (move.getDir() == 1) {
            for (int tileID = placedPiece.getPosition() - 8; tileID > move.getTgtPiece().getPosition(); tileID -= 8) {
                Piece piece = board.getTile(tileID).getPiece();
                piece.changeTeam();
                builder.setPiece(piece);
                piecesToSet.remove(piece);
            }
        } else if (move.getDir() == 2) {
            for (int tileID = placedPiece.getPosition() + 1; tileID < move.getTgtPiece().getPosition(); tileID++) {
                Piece piece = board.getTile(tileID).getPiece();
                piece.changeTeam();
                builder.setPiece(piece);
                piecesToSet.remove(piece);
            }
        } else if (move.getDir() == 3) {
                for (int tileID = placedPiece.getPosition() - 1; tileID > move.getTgtPiece().getPosition(); tileID--) {
                    Piece piece = board.getTile(tileID).getPiece();
                    piece.changeTeam();
                    builder.setPiece(piece);
                    piecesToSet.remove(piece);
                }
        } else if (move.getDir() == 4) {
            for (int tileID = placedPiece.getPosition() + 7; tileID < move.getTgtPiece().getPosition(); tileID += 7) {
                Piece piece = board.getTile(tileID).getPiece();
                piece.changeTeam();
                builder.setPiece(piece);
                piecesToSet.remove(piece);
            }
        } else if (move.getDir() == 5) {
            for (int tileID = placedPiece.getPosition() - 7; tileID > move.getTgtPiece().getPosition(); tileID -= 7) {
                Piece piece = board.getTile(tileID).getPiece();
                piece.changeTeam();
                builder.setPiece(piece);
                piecesToSet.remove(piece);
            }
        } else if (move.getDir() == 6) {
            for (int tileID = placedPiece.getPosition() + 9; tileID < move.getTgtPiece().getPosition(); tileID += 9) {
                Piece piece = board.getTile(tileID).getPiece();
                piece.changeTeam();
                builder.setPiece(piece);
                piecesToSet.remove(piece);
            }
        } else if (move.getDir() == 7) {
            for (int tileID = placedPiece.getPosition() - 9; tileID > move.getTgtPiece().getPosition(); tileID -= 9) {
                Piece piece = board.getTile(tileID).getPiece();
                piece.changeTeam();
                builder.setPiece(piece);
                piecesToSet.remove(piece);
            }
        }

        for (final Piece piece : piecesToSet) builder.setPiece(piece);
        builder.setPlayingTeam(board.getCurrentPlayer().getOpponent().getTeam());
        Board newBoard = new Board(builder);
        System.out.println(newBoard);
        return newBoard;
    }

    public static void checkEndGame(final Board board) {
        if (!board.getCurrentPlayer().hasMoves()) {
            final Board.Builder builder = new Board.Builder();
            builder.setPlayingTeam(board.getCurrentPlayer().getOpponent().getTeam());
        }
        if (!board.getCurrentPlayer().hasMoves()) endGame(board);
    }

    private static void endGame(final Board board) {
       int x = scoreX(board);
       int y = scoreY(board);
        if (x > y) {
            System.out.println("Team Red won!" + "\nTeam Black: " + x + "\nTeam Red: " + y);
        } else if (x < y) {
            System.out.println("Team Black won!" + "\nTeam Black: " + x + "\nTeam Red: " + y);
        } else System.out.println("It's a Draw!" + "\nTeam Black: " + x + "\nTeam Red: " + y);
        Gui.endGameWindow(board);
    }

    public static int scoreX(final Board board) {
        int x = 0;
        for (Tile tile : board.getBoard()) if (tile.getPiece()!= null && tile.getPiece().getTeam().isWhite()) x += 1;
        return x;
    }
    public static int scoreY(final Board board) {
        int y = 0;
        for (Tile tile : board.getBoard()) if (tile.getPiece()!= null && !tile.getPiece().getTeam().isWhite()) y += 1;
        return y;
    }
}

