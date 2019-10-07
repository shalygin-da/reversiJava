package shalygin.player;

import shalygin.board.Board;
import shalygin.piece.Piece;
import shalygin.piece.Team;

import java.util.Collection;

public class WhitePlayer extends Player {

    public WhitePlayer(final Board board) {
        super(board);
    }

    @Override
    public Team getTeam() { return Team.WHITE; }

    @Override
    public Player getOpponent() {
        return this.board.getBlackPlayer();
    }

    @Override
    public boolean hasMoves() {
        return !findMoves().isEmpty();
    }

    public Collection<Piece> getPieces() { return this.board.getWhitePieces(); }

}

