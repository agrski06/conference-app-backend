package conference.api.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = EmailInUseException.class)
    protected ResponseEntity<Object> handleEmailInUse(RuntimeException ex, WebRequest request) {
        String responseBody = "Email already in use";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = LoginInUseException.class)
    protected ResponseEntity<Object> handleLoginInUse(RuntimeException ex, WebRequest request) {
        String responseBody = "Login already in use";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = UserExistsException.class)
    protected ResponseEntity<Object> handleUserExistsException(RuntimeException ex, WebRequest request) {
        String responseBody = "User already exists";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(RuntimeException ex, WebRequest request) {
        String responseBody = "User with given login not found";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = NoSeatsLeftException.class)
    protected ResponseEntity<Object> handleNoSeatsLeft(RuntimeException ex, WebRequest request) {
        String responseBody = "Capacity of 5 participants reached";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = OverlappingLecturesException.class)
    protected ResponseEntity<Object> handleOverlappingLectures(RuntimeException ex, WebRequest request) {
        String responseBody = "You are registered to another lecture at this time";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = UserAlreadyRegisteredForLectureException.class)
    protected ResponseEntity<Object> handleAlreadyRegisteredForLecture(RuntimeException ex, WebRequest request) {
        String responseBody = "User already registered for this lecture";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = UserNotRegisteredForThisLectureException.class)
    protected ResponseEntity<Object> handleUserNotRegisteredForThisLectureException(RuntimeException ex, WebRequest request) {
        String responseBody = "User is not registered for this lecture";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElement(RuntimeException ex, WebRequest request) {
        String responseBody = "Invalid id (maybe out of range?)";
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
