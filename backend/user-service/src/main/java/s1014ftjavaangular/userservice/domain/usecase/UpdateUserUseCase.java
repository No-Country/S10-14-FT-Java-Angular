package s1014ftjavaangular.userservice.domain.usecase;

import s1014ftjavaangular.userservice.domain.model.dto.request.UserRequest;

public interface UpdateUserUseCase {
    void update(UserRequest request);
}
