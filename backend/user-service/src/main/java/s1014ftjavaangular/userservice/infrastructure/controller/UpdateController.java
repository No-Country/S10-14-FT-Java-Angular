package s1014ftjavaangular.userservice.infrastructure.controller;

import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.userservice.application.UpdateUserUseCaseImpl;
import s1014ftjavaangular.userservice.domain.model.dto.request.UserRequest;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UpdateController {
    private final UpdateUserUseCaseImpl updUser;

    @Retry(name = "userRetry")
    @PutMapping()
    public HttpEntity<Void> userUpdate(@Valid @RequestBody UserRequest request){

        updUser.update(request);
//
        return ResponseEntity.noContent().build();
    }
}
