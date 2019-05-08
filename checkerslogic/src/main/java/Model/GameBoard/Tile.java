package Model.GameBoard;

import Model.GameBoard.Type.TileColor;

public class Tile {

    private Piece piece;
    private TileColor color;
    private Location location;

    public Tile(boolean isWhite, int x, int y) {
        color =  (isWhite) ? TileColor.WHITE :  TileColor.BLACK;
        location = new Location(x,y);
    }

    public boolean HasPiece() {
      return piece != null;
    }

    public TileColor getColor() {
        return color;
    }

    public Location getLocation() {
        return location;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece)
    {
        this.piece = piece;
    }
    public void removePiece(){
        piece = null;
    }
}