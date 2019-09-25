package shalygin.player;

import shalygin.board.Board;
import shalygin.board.Move;
import shalygin.piece.Piece;
import shalygin.piece.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlackPlayer extends Player{

    public BlackPlayer(final Board board) {
        super(board);
        this.team = Team.BLACK;
    }

    @Override
    public Team getTeam() { return team; }

    @Override
    public Player getOpponent() {
        return this.board.getWhitePlayer();
    }

    @Override
    public boolean hasMoves() {
        return !findMoves().isEmpty();
    }

    @Override
    public List<Move> findMoves() {
        List<Move> moves = new ArrayList<>();
// TODO: 9/20/2019  
        return moves;
    }

    public Collection<Piece> getPieces() { return this.board.getBlackPieces(); }

}

