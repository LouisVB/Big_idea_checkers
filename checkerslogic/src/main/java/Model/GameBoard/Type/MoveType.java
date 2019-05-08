package Model.GameBoard.Type;

public enum MoveType {
    Normal(1), Kill(2);

    final int type;

    MoveType(int type) {
        this.type = type;
    }
}
