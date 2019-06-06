package Model.GameBoard;

import Model.GameBoard.Type.PieceColor;

public class Piece {

    private PieceColor color;
    private Location location;
    private boolean isKing;

    public Piece(PieceColor color, Location location) {
        this.color = color;
        this.location = location;
        isKing = false;
    }

    public Piece() {
    }

    public PieceColor getColor() {
        return color;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isKing() {
        return isKing;
    }

    //Change piece to king piece
    public void becameKing() {
        isKing = true;
    }

}
