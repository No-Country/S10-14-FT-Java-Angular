package s1014ftjavaangular.security.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import s1014ftjavaangular.security.domain.exceptions.AccountAlreadyExists;
import s1014ftjavaangular.security.domain.model.dto.ExceptionDTO;

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

    @ExceptionHandler(value = AccountAlreadyExists.class)
    public ResponseEntity<ExceptionDTO> handlerConlictException(AccountAlreadyExists exception) {
        var exceptionDto = ExceptionDTO.builder()
                .detail(exception.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .type(exception.getClass().getTypeName())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDto);
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
