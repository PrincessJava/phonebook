package su.msk.jet.phonebook.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import su.msk.jet.phonebook.exception.BadRequestException;
import su.msk.jet.phonebook.exception.DaoException;
import su.msk.jet.phonebook.model.ResponseError;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@ControllerAdvice
public class RestServicesErrorHandler {

    private static final Logger log = LogManager.getLogger(ServicesController.class);
    private MessageSource messageSource;

    @Autowired
    public RestServicesErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseError processBadRequest(BadRequestException e) {
        log.error(e);
        return new ResponseError(e.getMessage());
    }

    @ExceptionHandler(DaoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseError processInternalServerError(DaoException e) {
        log.error(e);
        DefaultMessageSourceResolvable defaultMessageSourceResolvable = new DefaultMessageSourceResolvable(new String[]{e.getClass().getCanonicalName()}, e.getMessage());
        List<DefaultMessageSourceResolvable> defaultMessageSourceResolvables = Collections.singletonList(defaultMessageSourceResolvable);
        return processFieldErrors(defaultMessageSourceResolvables);
    }

    private ResponseError processFieldErrors(List<DefaultMessageSourceResolvable> fieldErrors) {

        String localizedErrorMessage;

        StringBuilder builder = new StringBuilder();
        for (DefaultMessageSourceResolvable fieldError : fieldErrors) {
            builder.append(resolveLocalizedErrorMessage(fieldError)).append("\n");
            log.info("fieldError : {}", fieldError);
        }
        localizedErrorMessage = builder.toString();

        ResponseError errorResponse = new ResponseError();
        errorResponse.setMessage(localizedErrorMessage);
        return errorResponse;
    }

    private String resolveLocalizedErrorMessage(DefaultMessageSourceResolvable fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        //If a message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage != null &&
                fieldError instanceof ObjectError &&
                localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }
}
