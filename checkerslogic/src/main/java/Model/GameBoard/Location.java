package Model.GameBoard;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void changeLocationX(int newLocationX){
        this.x = newLocationX;
    }

    public void changeLocationY(int newLocationY){
        this.y = newLocationY;
    }
}
