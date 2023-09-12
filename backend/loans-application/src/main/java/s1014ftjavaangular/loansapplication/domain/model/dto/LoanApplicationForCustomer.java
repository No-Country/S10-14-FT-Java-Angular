package s1014ftjavaangular.loansapplication.domain.model.dto;

import jakarta.persistence.Enumerated;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;

import java.time.LocalDate;

public interface LoanApplicationForCustomer {
    String getLoanApplicationId();

    String getLoanApplicationNumber();

    Double getRequestedAmount();
    LocalDate getCreateAt();

    Status getStatus();
}
