package shalygin.player;

import shalygin.board.Board;
import shalygin.board.Move;
import shalygin.piece.Piece;
import shalygin.piece.Team;

import java.util.*;

public abstract class Player {

    final Board board;

    Player(final Board board) {
        this.board = board;
    }

    public abstract Collection<Piece> getPieces();

    public abstract Team getTeam();

    public abstract Player getOpponent();

    public abstract boolean hasMoves();

    public List<Move> findMoves() {
        List<Move> moves = new ArrayList<>();

            for (Piece piece : getPieces()) {
                int pos = piece.getPosition(); //vertical upwards
                while (pos > 7 && board.getTile(pos - 8).isOccupied() &&
                        board.getTile(pos - 8).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    pos -= 8;
                if (pos > 7 && !board.getTile(pos - 8).isOccupied() &&
                        board.getTile(pos).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    moves.add(new Move(pos - 8, 0, piece));

                //vertical downwards
                pos = piece.getPosition();
                while (pos < 55 && board.getTile(pos + 8).isOccupied() &&
                        board.getTile(pos + 8).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    pos += 8;
                if (pos < 56 && !board.getTile(pos + 8).isOccupied() &&
                        board.getTile(pos).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    moves.add(new Move(pos + 8, 1, piece));

                 //horizontal right
                pos = piece.getPosition();
                while (pos % 8 < 7 && board.getTile(pos + 1).isOccupied() &&
                        board.getTile(pos + 1).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    pos++;
                if (pos % 8 < 7 && !board.getTile(pos + 1).isOccupied() &&
                        board.getTile(pos).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    moves.add(new Move(pos + 1, 3, piece));

                 //horizontal left
                pos = piece.getPosition();
                while (pos % 8 > 0 && board.getTile(pos - 1).isOccupied() &&
                        board.getTile(pos - 1).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    pos--;
                if (pos % 8 > 0 && !board.getTile(pos - 1).isOccupied() &&
                        board.getTile(pos).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    moves.add(new Move(pos - 1, 2, piece));

                // rl-diag-upwards
                pos = piece.getPosition();
                while (pos > 7 && board.getTile(pos).getColumn() != 7 && board.getTile(pos - 7).isOccupied() &&
                        board.getTile(pos - 7).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    pos -= 7;
                if (pos > 7 && board.getTile(pos).getColumn() != 7 && !board.getTile(pos - 7).isOccupied() &&
                        board.getTile(pos).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    moves.add(new Move(pos - 7, 4, piece));

                // rl-diag-downwards
                pos = piece.getPosition();
                while (pos < 56 && board.getTile(pos).getColumn() != 0 && board.getTile(pos + 7).isOccupied() &&
                        board.getTile(pos + 7).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    pos += 7;
                if (pos < 56 && board.getTile(pos).getColumn() != 0 && !board.getTile(pos + 7).isOccupied() &&
                        board.getTile(pos).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    moves.add(new Move(pos + 7, 5, piece));

                //lr-diag-upwards
                pos = piece.getPosition();
                while (pos > 8 && board.getTile(pos).getColumn() != 0 && board.getTile(pos - 9).isOccupied() &&
                        board.getTile(pos - 9).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    pos -= 9;
                if (pos > 8 && board.getTile(pos).getColumn() != 0 && !board.getTile(pos - 9).isOccupied() &&
                        board.getTile(pos).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    moves.add(new Move(pos - 9, 6, piece));

                //lr-diag-downwards
                pos = piece.getPosition();
                while (pos < 55 && board.getTile(pos).getColumn() != 7 && board.getTile(pos + 9).isOccupied() &&
                        board.getTile(pos + 9).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    pos += 9;
                if (pos < 55 && board.getTile(pos).getColumn() != 7 && !board.getTile(pos + 9).isOccupied() &&
                        board.getTile(pos).getPiece().getTeam() != board.getCurrentPlayer().getTeam())
                    moves.add(new Move(pos + 9, 7, piece));
            }
        return moves;
    }
}
