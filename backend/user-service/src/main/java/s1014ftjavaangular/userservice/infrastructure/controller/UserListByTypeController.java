package s1014ftjavaangular.userservice.infrastructure.controller;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.userservice.domain.model.dto.response.UserResponse;
import s1014ftjavaangular.userservice.domain.usecase.UserListByTypeUseCase;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserListByTypeController {
    private final UserListByTypeUseCase userListByTypeUseCase;

    @Retry(name = "userRetry")
    @GetMapping("/type/{type}")
    public ResponseEntity<List<UserResponse>> getAllByType(@PathVariable String type){
        var response = userListByTypeUseCase.findAllByType(type);

        return response.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(response);
    }
}
