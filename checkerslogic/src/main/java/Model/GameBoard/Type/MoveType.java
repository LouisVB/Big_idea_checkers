package Model.GameBoard.Type;

public enum MoveType {
    NONE(0), NORMAL(1), KILL(2);

    final int type;

    MoveType(int type) {
        this.type = type;
    }
}
