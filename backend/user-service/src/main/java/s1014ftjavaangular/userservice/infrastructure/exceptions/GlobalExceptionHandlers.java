package s1014ftjavaangular.userservice.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import s1014ftjavaangular.userservice.domain.model.dto.response.ExceptionDTO;
import s1014ftjavaangular.userservice.domain.model.exception.ResourceAlreadyExists;
import s1014ftjavaangular.userservice.domain.model.exception.UserNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlers {

    //Este metodo responde a excepciones de tipo "Exception", es decir excepciones genericas
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionDTO> handlerExceptions(Exception exception) {
        var exceptionDto = ExceptionDTO.builder()
                .detail(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .type(exception.getClass().getTypeName())
                .build();

        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handlerConlictException(UserNotFoundException exception) {
        var exceptionDto = ExceptionDTO.builder()
                .detail(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .type(exception.getClass().getTypeName())
                .build();

        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ExceptionDTO> handleIllegalArgumentException(IllegalArgumentException exception) {
        var exceptionDto = ExceptionDTO.builder()
                .detail(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .type(exception.getClass().getTypeName())
                .build();

        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(value = ResourceAlreadyExists.class)
    public ResponseEntity<ExceptionDTO> handlerConlictException(ResourceAlreadyExists exception) {
        var exceptionDto = ExceptionDTO.builder()
                .detail(exception.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .type(exception.getClass().getTypeName())
                .build();

        return new ResponseEntity<>(exceptionDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentException(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String name = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errores.put(name, message);
        });
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
}
