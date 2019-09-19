package shalygin.player;

import shalygin.board.Board;
import shalygin.board.Move;
import shalygin.piece.Piece;
import shalygin.piece.Team;

import java.util.Collection;

public abstract class Player {

    private static Team team;
    protected final Board board;

    Player(final Board board) { this.board = board; }

    public abstract Collection<Piece> getAlivePieces();

    public abstract Team getTeam();

    public abstract Collection<Piece> getPieces();

    public abstract Player getOpponent();

}
