package nl.fhict.s3.websocketclient.SocketMessage;

public enum Operation {
    CONNECT(0), REGISTERPLAYER(1), STARTGAME(2), GETMOVES(3), USEMOVE(4), CHANGETURN(5), SURRENDER(6);


    final int type;

    Operation(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
