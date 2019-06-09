package Model;

import Model.GameBoard.Type.PieceColor;

public class Player {
    private String username;
    private String Password;
    private PieceColor myPieces;

    public Player() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return Password;
    }

    public PieceColor getMyPieces() {
        return myPieces;
    }

    public void setMyPieces(PieceColor myPieces) {
        this.myPieces = myPieces;
    }
}
