package shalygin.player;

import shalygin.board.Board;
import shalygin.board.Move;
import shalygin.piece.Piece;
import shalygin.piece.Team;

import java.util.Collection;
import java.util.List;

public abstract class Player {

    protected static Team team;
    protected final Board board;

    Player(final Board board) {
        this.board = board;
    }

    public abstract Collection<Piece> getPieces();

    public abstract Team getTeam();

    public abstract Player getOpponent();

    public abstract boolean hasMoves();

    public abstract List<Move> findMoves();
}
