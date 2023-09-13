package s1014ftjavaangular.loansapplication.domain.model.dto.response;

import lombok.Builder;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;

import java.time.LocalDate;

@Builder
public record ConfirmDataLoanApplicationDto(
        String loanApplicationNumber,
        Double requestedAmount,
        Status status,
        LocalDate createAt,
        String name,
        String lastname,
        String customersUuid, // en la vista figura el campo mail, reemplazarlo por este
        String identification,
        String phone

) {
}
