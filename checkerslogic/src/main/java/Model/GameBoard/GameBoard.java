package Model.GameBoard;

import Model.GameBoard.Type.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class GameBoard implements Interface.Board {
    private int size;
    private Tile[][] gameBoard;
    private List<Piece> Pieces;

    public Tile[][] getGameBoard() {
        return gameBoard;
    }

    public List<Piece> getPieces() {
        return Pieces;
    }

    public int getSize() {
        return size;
    }

    public GameBoard() {
        size = 8;
        gameBoard = new Tile[size][size];
        Pieces = new ArrayList<>();
    }

    @Override
    public void createBoard() {
        for (int y = 0; y < gameBoard.length; y++)
        {
            for (int x = 0; x < gameBoard.length; x++)
            {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                gameBoard[x][y] = tile;

                Piece piece = null;

                if (y <= 2 && (x + y) % 2 != 0)
                {
                    piece = new Piece(PieceColor.Red, new Location(x, y));
                }

                if (y >= 5 && (x + y) % 2 != 0)
                {
                    piece = new Piece(PieceColor.White, new Location(x, y));
                }

                if (piece != null)
                {
                    tile.setPiece(piece);
                    Pieces.add(piece);
                }
            }
        }
    }

    public void createBoardWithoutPieces() {
        for (int y = 0; y < gameBoard.length; y++)
        {
            for (int x = 0; x < gameBoard.length; x++)
            {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                gameBoard[x][y] = tile;
            }
        }
    }

    @Override
    public List<Move> getPieceMoves(Piece piece) {
        return null;
    }

    @Override
    public List<Move> getAllKillMoves(PieceColor color) {
        return null;
    }

    @Override
    public boolean isKillMovePossible(PieceColor color) {
        return false;
    }

    @Override
    public void doMove(Move nextMove) {

    }


    private void removePiece(Piece piece) {

    }
}
