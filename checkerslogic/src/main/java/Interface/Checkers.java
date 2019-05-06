package Interface;

public interface Checkers {

    void registerPlayer(String name, String password);

    void startGame(int playerNumber);

    void gameEnded();

    void surrender(int playerNumber);

    void rematch();

    void getMovePiece();

    void useMove();

}