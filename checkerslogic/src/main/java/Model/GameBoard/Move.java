package Model.GameBoard;

import Model.GameBoard.Type.MoveType;

public class Move {

    private Piece myPiece;
    private Piece enemyPiece;
    private Location newLocation;
    private MoveType type;

    // NORMAL Move
    public Move(Piece myPiece, Location newLocation, MoveType type) {
        this.myPiece = myPiece;
        this.newLocation = newLocation;
        this.type = type;
    }
    // KILL Move
    public Move(Piece myPiece, Piece enemyPiece, Location newLocation, MoveType type) {
        this.myPiece = myPiece;
        this.enemyPiece = enemyPiece;
        this.newLocation = newLocation;
        this.type = type;
    }

    public Move() {
    }

    //empty move
    public Move(MoveType type) {
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

    public MoveType getType() {
        return type;
    }
}
