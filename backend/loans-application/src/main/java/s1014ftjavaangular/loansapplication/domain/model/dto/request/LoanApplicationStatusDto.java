package s1014ftjavaangular.loansapplication.domain.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;

@Data
@Builder
public class LoanApplicationStatusDto {
    @NotEmpty(message = "Field required")
    private String loanApplicationId;

    @NotNull(message = "Field required")
    private Status status;
}
