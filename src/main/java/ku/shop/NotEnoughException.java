package ku.shop;

public class NotEnoughException extends Exception{
    public NotEnoughException() {}
    public NotEnoughException(String reason) {
        super(reason);
    }
}