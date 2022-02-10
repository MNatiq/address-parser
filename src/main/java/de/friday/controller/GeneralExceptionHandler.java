package de.friday.controller;

import de.friday.exception.MatcherNotFoundException;
import de.friday.exception.NullOrEmptyAddressException;
import de.friday.exception.ParserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice(assignableTypes = AddressParserController.class)
public class GeneralExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            NullOrEmptyAddressException.class,
            MatcherNotFoundException.class,
            ParserNotFoundException.class
    })
    public ErrorResponse handleException(HttpServletRequest req, Exception e) {
        log.error("{}", e);
        return new ErrorResponse(e.getMessage());
    }
}
