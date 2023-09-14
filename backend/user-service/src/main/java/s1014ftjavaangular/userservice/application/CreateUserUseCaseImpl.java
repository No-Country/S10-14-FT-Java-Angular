package s1014ftjavaangular.userservice.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import s1014ftjavaangular.userservice.domain.model.dto.request.AccountCreatedDto;
import s1014ftjavaangular.userservice.domain.model.entity.User;
import s1014ftjavaangular.userservice.domain.repository.UserRepository;
import s1014ftjavaangular.userservice.domain.usecase.CreateUserUseCase;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository repository;

    @Override
    public void saveUser(final AccountCreatedDto message){
        var lastUserNumber = repository.findLastUserNumber(message.getAccountRol());
        log.info("Last user number: {}", lastUserNumber);
        var nextCustomerNumber = getCustomerActualCustomerNumber(lastUserNumber);
        String customerNumber = LocalDate.now().getYear()+"-"+nextCustomerNumber;
        log.info("Next number: {}", customerNumber);

        var model = User.builder()
                .id(message.getAccountUuid())
                .name(message.getName())
                .lastName(message.getLastname())
                .type(message.getAccountRol())
                .number(customerNumber)
                .blackList(false)
                .build();

        repository.saveUser(model);
    }

    private String getCustomerActualCustomerNumber(String lastUserNumber) {
        return Optional.ofNullable( lastUserNumber )
                .filter(lastNumber-> lastNumber.indexOf("-") == 4 && lastNumber.split("-").length == 2)
                .flatMap(lastNumber-> Optional.of( Integer.valueOf( lastNumber.split("-")[1] ) ))
                .flatMap(number-> Optional.of( String.valueOf( ++number ) ) )
                .orElse("1");
    }

}
