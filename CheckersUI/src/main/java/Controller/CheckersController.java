package Controller;

import Model.GameBoard.*;
import Model.GameBoard.Type.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import nl.fhict.s3.websocketclient.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class CheckersController extends Application implements Observer {

    private GameBoard Checkersboard;
    private List<Move> possibleMoves;

    // fxml buttons
    @FXML
    public PasswordField Password;
    @FXML
    public TextField Name;
    @FXML
    public GridPane GameBoard;


    @FXML
    public void initialize() {
        Client.getInstance().addObserver(this);
        Client.getInstance().setViewController(this);
        Checkersboard = new GameBoard();
        Checkersboard.createBoard();
        possibleMoves = new ArrayList<>();
        fillRaster(GameBoard, Checkersboard.getGameBoard());

    }

    private void refreshUI(){
        GameBoard.getChildren().clear();
        fillRaster(GameBoard, Checkersboard.getGameBoard());
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load((getClass().getClassLoader().getResource("checkers.fxml")));
            Stage gameStage = new Stage();
            gameStage.setScene(new Scene(root));
            gameStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Rectangle createTiles(Tile Tile) {
        //create Tile
        Rectangle tile = new Rectangle();
        tile.setHeight(62);
        tile.setWidth(62);
        //check and set color
        tile.setFill(Tile.getColor() == TileColor.WHITE ? Color.valueOf("#fff") : Color.valueOf("#303030"));

        return tile;
    }

    private Ellipse createPieceBackground() {

        Ellipse bg = new Ellipse(62 * 0.3125, 62 * 0.26);
        bg.setFill(Color.BLACK);

        bg.setStroke(Color.BLACK);
        bg.setStrokeWidth(62 * 0.03);

        bg.setTranslateX(10);
        bg.setTranslateY(5);

        return bg;
    }

    private Ellipse createPiece(Piece piece) {
        Ellipse ellipse = new Ellipse(62 * 0.3125, 62 * 0.26);
        ellipse.setFill(piece.getColor() == PieceColor.Red
                ? Color.valueOf("#A80000") : Color.valueOf("#fff9f4"));
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(62 * 0.03);
        ellipse.setTranslateX(10);
        ellipse.setTranslateY(0);

        ellipse.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        pieceSelected(event, piece);
                    }
                });

        return ellipse;
    }

    private Ellipse createPossibleMove(Move move) {
        Ellipse ellipse = new Ellipse(62 * 0.3125, 62 * 0.3125);
        ellipse.setFill(Color.valueOf("#303030"));
        ellipse.setStroke(move.getMyPiece().getColor() == PieceColor.Red
                ? Color.valueOf("#A80000") : Color.valueOf("#fff9f4"));;
        ellipse.setStrokeWidth(3);
        ellipse.setTranslateX(10);
        ellipse.setTranslateY(0);

        ellipse.addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        possibleMoveSelected(event, move);
                    }
                });
        return ellipse;
    }


    private void fillRaster(GridPane pane, Tile[][] board) {

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                pane.add(createTiles(board[x][y]), x, y);
                if (board[x][y].HasPiece()) {
                    pane.add(createPieceBackground(), x, y);
                    pane.add(createPiece(board[x][y].getPiece()), x, y);
                }
                if(!possibleMoves.isEmpty()) {
                    for (Move move : possibleMoves) {
                        if (move.getNewLocation().getX() == x && move.getNewLocation().getY() == y ) {
                            pane.add(createPossibleMove(move), x, y);
                        }
                    }
                }
            }
        }
    }

    private void pieceSelected(MouseEvent event, Piece piece) {
        possibleMoves = Checkersboard.getPieceMoves(piece);
        refreshUI();
    }

    private void possibleMoveSelected(MouseEvent event, Move move) {
        possibleMoves.clear();
        Checkersboard.doMove(move);
        refreshUI();
    }

    //Checks user user account and checks for single or multilayer.
    public void connectToSession(MouseEvent mouseEvent) {
        String name = Name.getText();
        String password = Password.getText();


    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
