package shalygin.piece;

import shalygin.player.Player;

public enum Team {
    WHITE {
        @Override
        public boolean isWhite() { return true; }

        @Override
        public boolean isBlack() { return false; }

        @Override
        public Team getOpposite() { return  BLACK; }

        @Override
        public String toString() { return "W"; }

        @Override
        public Player getTeam(final Player whitePlayer, final Player blackPlayer) {
            return whitePlayer;
        }

    },
    BLACK {
        @Override
        public boolean isWhite() { return false; }

        @Override
        public boolean isBlack() { return true; }

        @Override
        public Team getOpposite() { return WHITE; }

        @Override
        public String toString() { return "b"; }

        @Override
        public Player getTeam(final Player whitePlayer, final Player blackPlayer) {
            return blackPlayer;
        }

    };

    public abstract boolean isWhite();
    public abstract boolean isBlack();
    public abstract Team getOpposite();
    public abstract String toString();
    public abstract Player getTeam(final Player whitePlayer, final Player blackPlayer);

}
