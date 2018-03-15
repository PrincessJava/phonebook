package su.msk.jet.phonebook.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import su.msk.jet.phonebook.exception.BadRequestException;
import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.model.ResponseError;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class RestErrorHandler {

    private static final Logger log = LogManager.getLogger(PhoneController.class);
    private MessageSource messageSource;

    @Autowired
    public RestErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseError processBadRequest(BadRequestException e) {
        log.error(e);
        DefaultMessageSourceResolvable defaultMessageSourceResolvable = new DefaultMessageSourceResolvable(new String[]{e.getClass().getCanonicalName()}, "Bad request");
        List<DefaultMessageSourceResolvable> defaultMessageSourceResolvables = Arrays.asList(defaultMessageSourceResolvable);
        return processFieldErrors(defaultMessageSourceResolvables);
    }

    @ExceptionHandler(DaoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseError processInternalServerError(DaoException e) {
        log.error(e);
        DefaultMessageSourceResolvable defaultMessageSourceResolvable = new DefaultMessageSourceResolvable(new String[]{e.getClass().getCanonicalName()}, "Internal server error");
        List<DefaultMessageSourceResolvable> defaultMessageSourceResolvables = Arrays.asList(defaultMessageSourceResolvable);
        return processFieldErrors(defaultMessageSourceResolvables);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseError processValidationError(MethodArgumentNotValidException e) {
        log.error(e);
        BindingResult bindingResult = e.getBindingResult();
        List<? extends DefaultMessageSourceResolvable> fieldErrors = bindingResult.getAllErrors();
        return processFieldErrors((List<DefaultMessageSourceResolvable>) fieldErrors);
    }

    private ResponseError processFieldErrors(List<DefaultMessageSourceResolvable> fieldErrors) {

        String localizedErrorMessage = null;
        for (DefaultMessageSourceResolvable fieldError : fieldErrors) {
            if (localizedErrorMessage == null) {
                localizedErrorMessage = resolveLocalizedErrorMessage(fieldError) + System.lineSeparator();
            } else {
                localizedErrorMessage = localizedErrorMessage + resolveLocalizedErrorMessage(fieldError) + System.lineSeparator();
            }
        }
        return new ResponseError(localizedErrorMessage);
    }

    private String resolveLocalizedErrorMessage(DefaultMessageSourceResolvable fieldError) {

        Locale currentLocale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(fieldError, currentLocale);

    }
}
