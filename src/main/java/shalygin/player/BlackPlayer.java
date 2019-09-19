package shalygin.player;

import shalygin.board.Board;
import shalygin.piece.Piece;
import shalygin.piece.Team;

import java.util.Collection;

public class BlackPlayer {

    private static Team team;
    protected final Board board;

    public BlackPlayer(final Board board) {
        this.team = Team.WHITE;
        this.board = board;
    }

    public static Team getTeam() { return team; }

    public Collection<Piece> getPieces() { return this.board.getBlackPieces(); }

}

