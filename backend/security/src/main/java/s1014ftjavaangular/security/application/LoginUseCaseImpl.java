package s1014ftjavaangular.security.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import s1014ftjavaangular.security.domain.model.dto.LoginResponse;
import s1014ftjavaangular.security.domain.service.AuthService;
import s1014ftjavaangular.security.domain.service.JwtProvider;
import s1014ftjavaangular.security.domain.usecase.LoginUseCase;

@Component
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final AuthService authService;
    private final JwtProvider provider;

    @Override
    public LoginResponse login(final String email, final String password) {
        var accountAuthenticated = authService.authenticate(email, password);

        var token = provider.generateToken(accountAuthenticated);

        var loginResponse = new LoginResponse();
        loginResponse.setEmail(email);
        loginResponse.setToken(token);
        loginResponse.setRol(accountAuthenticated.getAccount().getRol());
        loginResponse.setId(accountAuthenticated.getId());

        return loginResponse;
    }
}
