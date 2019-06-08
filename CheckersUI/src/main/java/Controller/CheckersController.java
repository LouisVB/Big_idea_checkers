package Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CheckersController {




    private boolean squareSelectedInOceanArea = false;
    private boolean playingMode = false;
    private boolean gameEnded = false;
    private int lastX;
    private int lastY;



    @FXML
    private PasswordField Password;
    @FXML
    private TextField Name;

    @FXML
    public RadioButton multiplayer;
    public RadioButton Horizantally;


    @FXML
    public GridPane GameBoard;



    @FXML
    private void initialize() {
        refreshGui();
    }

    private void refreshGui() {


    }

//    private Rectangle createSquare( int x, int y) {
//        Rectangle rectangle = new Rectangle(x, y, 45, 40);
//        rectangle.setStroke(Color.web("#000"));
//
//        final int xpos = x;
//        final int ypos = y;
//
//        rectangle.addEventHandler(MouseEvent.MOUSE_PRESSED,
//                new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent event) {
//                        if(isEnemy){
//                            rectangleTargetAreaMousePressed(event, xpos, ypos);
//                        } else{
//                            if(!playingMode){
//                                rectangleOceanAreaMousePressed(event, xpos, ypos);
//                                rectangle.setFill(Color.YELLOW);
//                            }
//
//                        }
//                    }
//                });
//
//
//        return rectangle;
//    }
//


    private void rectangleOceanAreaMousePressed(MouseEvent event, int x, int y) {
        if (!playingMode) {
            // Game is not in playing mode: select square to place a ship
            lastX = x;
            lastY = y;
            squareSelectedInOceanArea = true;
        }

    }

    private void fillRaster(GridPane pane ) {

    }



    private Boolean getSelectedGameType() {
        if (multiplayer.isSelected()) return false;
        return true;
    }

    private Boolean getSelectedplaceType() {
        if (Horizantally.isSelected()) return true;
        return false;
    }


    //Checks user user account and checks for single or multilayer.
    public void connectToSession(MouseEvent mouseEvent) {
        String name = Name.getText();
        String password = Password.getText();
        boolean isSingleplayer = getSelectedGameType();

    }






}
