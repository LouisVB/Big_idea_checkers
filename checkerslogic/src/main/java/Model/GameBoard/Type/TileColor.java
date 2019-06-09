package Model.GameBoard.Type;

public enum TileColor {

    WHITE(1), BLACK(2);

    final int Color;

    TileColor(int Color) {
        this.Color = Color;
    }

    public int getColor() {
        return Color;
    }
}
