package ob.poc.msproducts.utils.handler;


import ob.poc.msproducts.entity.dto.response.ErrorDtoResponse;
import ob.poc.msproducts.utils.exceptions.AlreadyExistsException;
import ob.poc.msproducts.utils.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorDtoResponse> handleAlreadyExistsException(AlreadyExistsException exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDtoResponse.builder()
                .errorCode(1)
                .message(exception.getMessage())
                .build());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDtoResponse> handleNotFoundException(NotFoundException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDtoResponse.builder()
                .message(exception.getMessage())
                .errorCode(2)
                .build());
    }
}

