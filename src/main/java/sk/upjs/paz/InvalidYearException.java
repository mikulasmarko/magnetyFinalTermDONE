package sk.upjs.paz;

public class InvalidYearException extends RuntimeException {


    public InvalidYearException() {
        super();
    }

    public InvalidYearException(String message) {
        super(message);
    }

    public InvalidYearException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidYearException(Throwable cause) {
        super(cause);
    }

    protected InvalidYearException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
