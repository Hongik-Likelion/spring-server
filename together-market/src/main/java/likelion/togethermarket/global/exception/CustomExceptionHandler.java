package likelion.togethermarket.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@CrossOrigin(origins = "*")
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException e){

        return new ResponseEntity<String>(e.getErrorCode().getMessage(), e.getErrorCode().getHttpStatus());
    }
}
