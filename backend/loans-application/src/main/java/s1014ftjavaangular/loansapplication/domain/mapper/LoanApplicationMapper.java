package s1014ftjavaangular.loansapplication.domain.mapper;

import org.springframework.stereotype.Component;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.GeneralDataDto;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class LoanApplicationMapper {

    public final BiFunction<GeneralDataDto, String, LoanApplication> dtoToModel = (dto, loanApplicationNumber) ->
            LoanApplication.builder()
                    .loanApplicationId(UUID.randomUUID().toString())
                    .customersUuid(dto.getCustomersUuid())
                    .requestedAmount(dto.getRequestedAmount())
                    .createAt(LocalDate.now())
                    .status(Status.INCOMPLETE)
                    .loanApplicationNumber(loanApplicationNumber)
                    .build();
}
