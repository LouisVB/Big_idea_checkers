package nl.fhict.s3.websocketserver.SocketMessage;

public class SocketMessage {
    private Operation operation;
    private String Message;

    public SocketMessage(Operation operation, String message) {
        this.operation = operation;
        Message = message;
    }

    public SocketMessage() {
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
