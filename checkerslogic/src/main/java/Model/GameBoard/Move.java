package Model.GameBoard;

import Model.GameBoard.Type.TypeMove;

public class Move {

    private Piece myPiece;
    private Piece enemyPiece;
    private Location newLocation;
    private TypeMove type;

    // Normal Move
    public Move(Piece myPiece, Location newLocation, TypeMove type) {
        this.myPiece = myPiece;
        this.newLocation = newLocation;
        this.type = type;
    }
    // Kill Move
    public Move(Piece myPiece, Piece enemyPiece, Location newLocation, TypeMove type) {
        this.myPiece = myPiece;
        this.enemyPiece = enemyPiece;
        this.newLocation = newLocation;
        this.type = type;
    }

    public Piece getMyPiece() {
        return myPiece;
    }

    public Piece getEnemyPiece() {
        return enemyPiece;
    }

    public Location getNewLocation() {
        return newLocation;
    }

    public TypeMove getType() {
        return type;
    }
}
