package s1014ftjavaangular.loansapplication.infrastructure.exception;

import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import s1014ftjavaangular.loansapplication.domain.exceptions.ResourceAlreadyExists;
import s1014ftjavaangular.loansapplication.domain.model.dto.response.ExceptionDTO;


@RestControllerAdvice
public class GlobalExceptionHandlers {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionDTO> handleException(Exception exception) {
        var exceptionDto = ExceptionDTO.builder()
                .detail(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .type(exception.getClass().getTypeName())
                .build();

        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ExceptionDTO> handleRuntimeException(RuntimeException exception) {
        var exceptionDto = ExceptionDTO.builder()
                .detail(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .type(exception.getClass().getTypeName())
                .build();

        return ResponseEntity.badRequest().body(exceptionDto);
    }

    @ExceptionHandler(value = ResourceAlreadyExists.class)
    public ResponseEntity<ExceptionDTO> handleException(ResourceAlreadyExists exception) {
        var exceptionDto = ExceptionDTO.builder()
                .detail(exception.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .type(exception.getClass().getTypeName())
                .build();

        return new ResponseEntity<>(exceptionDto, HttpStatus.CONFLICT);
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

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handlerExceptions(NotFoundException exception) {
        var exceptionDto = ExceptionDTO.builder()
                .detail(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .type(exception.getClass().getTypeName())
                .build();

        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

}
