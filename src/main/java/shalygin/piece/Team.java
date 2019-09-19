package shalygin.piece;


public enum Team {
    WHITE {
        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
        }
    },
    BLACK {
        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }
    };

    public abstract boolean isWhite();
    public abstract boolean isBlack();

}
