package su.msk.jet.phonebook.exception;

public class BadRequestException extends AbstractPhoneBookException {
    public BadRequestException(final String message) {
        super(message);
    }
}
