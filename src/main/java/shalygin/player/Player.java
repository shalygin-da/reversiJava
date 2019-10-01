package shalygin.player;

import shalygin.board.Board;
import shalygin.piece.Piece;
import shalygin.piece.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {

    protected final Board board;

    Player(final Board board) {
        this.board = board;
    }

    public abstract Collection<Piece> getPieces();

    public abstract Team getTeam();

    public abstract Player getOpponent();

    public abstract boolean hasMoves();

    public List<Integer> findMoves() {
        {
            List<Integer> moves = new ArrayList<>(); // TODO: 9/20/2019
            for (Piece piece : getPieces()) {
                if (piece.getPosition() > 7) {
                    int pos = piece.getPosition();
                    do { pos -= 8; }
                    while (pos > 7 && board.getTile(pos - 8).isOccupied() &&
                            board.getTile(piece.getPosition() - 8).getPiece().getTeam() != piece.getTeam());
                    if (board.getTile(pos).getTileCoord() > 7 && !board.getTile(pos - 8).isOccupied())
                        moves.add(pos - 8);
                }

                if (piece.getPosition() < 55) {
                    int pos = piece.getPosition();
                    do { pos += 8; }
                    while (pos < 55 && board.getTile(pos + 8).isOccupied() &&
                            board.getTile(piece.getPosition() + 8).getPiece().getTeam() != piece.getTeam());
                    if (board.getTile(pos).getTileCoord() < 55 && !board.getTile(pos + 8).isOccupied())
                        moves.add(pos + 8);
                }

                // TODO: 10/2/2019 HORIZONTALS AND DIAGONALS!!!!!!!!!!!!
            }
            return moves;
        }
    };
}
