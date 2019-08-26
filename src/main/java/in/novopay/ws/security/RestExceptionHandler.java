package in.novopay.ws.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import in.novopay.ws.dto.response.FailureResponse;
import in.novopay.ws.dto.response.FieldValidationError;
import in.novopay.ws.exception.CustomException;
import in.novopay.ws.exception.CustomResponseStatusException;

@ControllerAdvice
public class RestExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);
	private static final String UNEXPECTED_ERROR = "Exception.unexpected";
	private static final String BAD_REQUEST = "Exception.bad.request";
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FailureResponse> handleArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {
        BindingResult result = ex.getBindingResult();
       	List<FieldValidationError> errorMessageList = new ArrayList<>();
       	FieldValidationError fieldValidationError;
        for(ObjectError objectError:result.getAllErrors()) {
        	fieldValidationError = new FieldValidationError();
        	if(objectError instanceof FieldError) {
        		fieldValidationError.setField(((FieldError)objectError).getField());
        	}
        	fieldValidationError.setMessage(messageSource.getMessage(objectError, locale));
        	
        	errorMessageList.add(fieldValidationError);
        }
        return new ResponseEntity<>(new FailureResponse("IA002",HttpStatus.BAD_REQUEST.value(), "", ex.getMessage(),errorMessageList), HttpStatus.BAD_REQUEST);
    }
	
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<FailureResponse> handleCustomExceptions(CustomException ex, Locale locale) {
    	LOG.error(ex.getMessage(), ex);
        String errorMessage;
        try {
			errorMessage = messageSource.getMessage(ex.getMessageCode(), null, locale);
		} catch (NoSuchMessageException e) {
			errorMessage = messageSource.getMessage(UNEXPECTED_ERROR, null, locale);
		}
        return new ResponseEntity<>(new FailureResponse(ex.getMessageCode(), ex.getStatus().value(), errorMessage, errorMessage), ex.getStatus());
    }
    
    @ExceptionHandler(CustomResponseStatusException.class)
    public ResponseEntity<FailureResponse> handleCustomExceptions(CustomResponseStatusException ex, Locale locale) {
    	LOG.error(ex.getMessage(), ex);
        String errorMessage;
        try {
			errorMessage = messageSource.getMessage(ex.getCode(), null, locale);
		} catch (NoSuchMessageException e) {
			errorMessage = messageSource.getMessage(UNEXPECTED_ERROR, null, locale);
		}
        return new ResponseEntity<>(new FailureResponse(ex.getCode(), ex.getStatus().value(), errorMessage, errorMessage), ex.getStatus());
    }
	
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<FailureResponse> handleHttpMethodNotReadableException(Exception ex, Locale locale) {
    	LOG.error(ex.getMessage(), ex);
        String errorMessage = messageSource.getMessage(BAD_REQUEST, null, locale);
        return new ResponseEntity<>(new FailureResponse("IA001", HttpStatus.BAD_REQUEST.value(), errorMessage, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<FailureResponse> handleExceptions(Exception ex, Locale locale) {
    	LOG.error(ex.getMessage(), ex);
        String errorMessage = messageSource.getMessage(UNEXPECTED_ERROR, null, locale);
        return new ResponseEntity<>(new FailureResponse("UN001", HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
