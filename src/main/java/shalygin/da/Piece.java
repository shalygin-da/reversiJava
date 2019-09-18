package shalygin.da;

public class Piece {

    protected final Team team;
    protected final int position;
    private final int cachedHashCode;


    public Piece(Team team, int position) {
        this.team = team;
        this.position = position;
        this.cachedHashCode = computeHashCode();
    }

    private int computeHashCode() {
        return 31 * position * 19 * team.hashCode();
    }

    public Team getTeam() { return team; }

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
}
