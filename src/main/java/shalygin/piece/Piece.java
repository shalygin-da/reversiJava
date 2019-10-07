package shalygin.piece;

public class Piece {

    private Team team;
    private final int position;
    private final int cachedHashCode;


    public Piece(Team team, int position) {
        this.team = team;
        this.position = position;
        this.cachedHashCode = computeHashCode();
    }

    @Override
    public String toString() {
        return getTeam().toString();
    }

    private int computeHashCode() {
        return 31 * position * 19 * team.hashCode();
    }

    public Team getTeam() { return team; }

    public void changeTeam() {
        this.team = team.getOpposite();
    }

    public int getPosition() { return position; }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Piece)) return false;
        final Piece otherPiece = (Piece) other;
        return position == otherPiece.getPosition() && team == otherPiece.getTeam();
    }

    @Override
    public int hashCode() { return this.cachedHashCode; }

    public int getRow() { return position / 8; }

    public int getColumn() { return position % 8; }

}
