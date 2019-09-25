package shalygin.board;

import shalygin.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    protected final int tileCoord;

    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createTiles();

    private Tile(int tileCoord) {
        this.tileCoord = tileCoord;
    }

    private static Map<Integer, EmptyTile> createTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < 64; i++) emptyTileMap.put(i, new EmptyTile(i));
        return emptyTileMap;
    }

    public static Tile create(final int tileCoord, Piece piece) {
        return piece != null ? new OccupiedTile(tileCoord, piece) : EMPTY_TILES_CACHE.get(tileCoord);
    }

    public abstract boolean isOccupied();

    public abstract Piece getPiece();

    public int getTileCoord() { return this.tileCoord; }

    public static final class  EmptyTile extends Tile {

        public EmptyTile(final int tileCoord) {
            super(tileCoord);
        }

        @Override
        public boolean isOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }

        @Override
        public String toString() {
            return "-";
        }
    }

    public static final class OccupiedTile extends Tile {

        private final Piece piece;

        private OccupiedTile(int tileCoord, Piece piece) {
            super(tileCoord);
            this.piece = piece;
        }

        @Override
        public boolean isOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return piece;
        }

        @Override
        public String toString() {
            return getPiece().getTeam().isBlack() ? getPiece().toString().toLowerCase() : getPiece().toString();
        }
    }

}
