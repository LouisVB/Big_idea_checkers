package Model.GameBoard.Type;

public enum PieceColor {
    Red(1), White(-1);

    final int moveDir;

    PieceColor(int moveDir) {
        this.moveDir =  moveDir;
    }

    public int getMoveDir()
    {
        return moveDir;
    }
}
