package su.msk.jet.phonebook.exception;

public class AbstractPhoneBookException extends RuntimeException {
    public AbstractPhoneBookException() {
    }

    AbstractPhoneBookException(final String message) {
        super(message);
    }

    AbstractPhoneBookException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
