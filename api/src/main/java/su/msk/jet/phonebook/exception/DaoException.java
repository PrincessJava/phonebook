package su.msk.jet.phonebook.exception;

public class DaoException extends AbstractPhoneBookException {
    public DaoException() {
    }

    public DaoException(final String message) {
        super(message);
    }

    public DaoException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
