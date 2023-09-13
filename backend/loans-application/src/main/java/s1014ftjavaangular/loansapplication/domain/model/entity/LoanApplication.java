package s1014ftjavaangular.loansapplication.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoanApplication {

    private String loanApplicationId;
    private String customersUuid;
    private String loanApplicationNumber;
    private Double requestedAmount;
    private LocalDate createAt;
    private JobInformation jobInformation;
    private Guarantor guarantor;
    private GeneralData generalData;
    private CreditAudit creditAudit;
    private Status status;
}
