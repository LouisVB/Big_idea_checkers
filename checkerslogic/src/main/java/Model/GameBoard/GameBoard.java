package Model.GameBoard;

import Model.GameBoard.Type.MoveType;
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
        for (int y = 0; y < gameBoard.length; y++) {
            for (int x = 0; x < gameBoard.length; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                gameBoard[x][y] = tile;

                Piece piece = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = new Piece(PieceColor.Red, new Location(x, y));
                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = new Piece(PieceColor.White, new Location(x, y));
                }

                if (piece != null) {
                    tile.setPiece(piece);
                    Pieces.add(piece);
                }
            }
        }
    }

    //UnitTestOnly
    public void createBoardWithoutPieces() {
        for (int y = 0; y < gameBoard.length; y++) {
            for (int x = 0; x < gameBoard.length; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                gameBoard[x][y] = tile;
            }
        }
    }

    @Override
    public List<Move> getPieceMoves(Piece piece) {
        List<Move> result = new ArrayList<>();
        //Loop by all existing pieces.
                // x location move based on move dir
                int killCheckY = piece.getLocation().getY() + piece.getColor().getMoveDir() * 1;
                //try to find kill move and add to list
                try {
                    for (int i = 0; i < 2; i++) {
                        int killCheckX = (i == 1) ? piece.getLocation().getX() - 2 : piece.getLocation().getX() + 2;
                        Move NewMove = tryMove(piece, new Location(killCheckX, killCheckY));
                        if (NewMove.getType() == MoveType.KILL) {
                            result.add(new Move(piece, NewMove.getEnemyPiece(), new Location(killCheckX, killCheckY), NewMove.getType()));
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Out of bounds");
                } catch (Exception e) {
                    System.out.println("Exception occurred");
                }
        return result;
    }

    @Override
    public List<Move> getAllKillMoves(PieceColor color) {
        List<Move> result = new ArrayList<>();
        //Loop by all existing pieces.
        for (Piece piece : Pieces) {
            //check pieces of the selected color
            if (piece.getColor() == color) {
                // x location move based on move dir
                int killCheckY = piece.getLocation().getY() + piece.getColor().getMoveDir() * 2;
                //try to find kill move and add to list
                try {
                    for (int i = 0; i < 2; i++) {
                        int killCheckX = (i == 1) ? piece.getLocation().getX() - 2 : piece.getLocation().getX() + 2;
                        Move NewMove = tryMove(piece, new Location(killCheckX, killCheckY));
                        if (NewMove.getType() == MoveType.KILL) {
                            result.add(new Move(piece, NewMove.getEnemyPiece(), new Location(killCheckX, killCheckY), NewMove.getType()));
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Out of bounds");
                } catch (Exception e) {
                    System.out.println("Exception occurred");
                }
            }
        }
        return result;
    }

    @Override
    public boolean isKillMovePossible(PieceColor color) {
        return false;
    }

    @Override
    public void doMove(Move nextMove) {

    }

    /**
     * [private FUNCTIONS]
     **/
    private void removePiece(Piece piece) {
        Pieces.remove(piece); //remove piece from list
        gameBoard[piece.getLocation().getX()][piece.getLocation().getY()].removePiece(); //remove piece from tile
    }

    private Piece getPieceByLocation(Location location) {
        Piece specificPiece = new Piece();
        for (Piece piece : Pieces) {
            if (location.getX() == piece.getLocation().getX() && location.getY() == piece.getLocation().getY()) {
                specificPiece = piece;
            }
        }
        return specificPiece;
    }

    private Move tryMove(Piece piece, Location newLocation) {

        if (Math.abs(newLocation.getX() - piece.getLocation().getX()) == 1 && newLocation.getY() - piece.getLocation().getY() == piece.getColor().getMoveDir()) {//NORMAL move
            return new Move(piece, newLocation, MoveType.NORMAL);
        } else if (Math.abs(newLocation.getX() - piece.getLocation().getX()) == 2 && newLocation.getY() - piece.getLocation().getY() == piece.getColor().getMoveDir() * 2) {//KILL move
            int enemyTileLocationX = piece.getLocation().getX() + (newLocation.getX() - piece.getLocation().getX()) / 2;
            int enemyTileLocationY = piece.getLocation().getY() + (newLocation.getY() - piece.getLocation().getY()) / 2;

            if (gameBoard[enemyTileLocationX][enemyTileLocationY].HasPiece() && gameBoard[enemyTileLocationX][enemyTileLocationY].getPiece().getColor() != piece.getColor()) {
                return new Move(piece, gameBoard[enemyTileLocationX][enemyTileLocationY].getPiece(), newLocation, MoveType.KILL);
            }
        }
        return new Move(MoveType.NONE);
    }


}
