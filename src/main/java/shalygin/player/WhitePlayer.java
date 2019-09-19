package shalygin.player;

import shalygin.board.Board;
import shalygin.board.Move;
import shalygin.piece.Piece;
import shalygin.piece.Team;

import java.util.ArrayList;
import java.util.Collection;

public class WhitePlayer {

    private static Team team;
    protected final Board board;

    public WhitePlayer(final Board board) {
        this.team = Team.WHITE;
        this.board = board;
    }

    public static Team getTeam() { return team; }

    public Collection<Piece> getPieces() { return this.board.getWhitePieces(); }

}

