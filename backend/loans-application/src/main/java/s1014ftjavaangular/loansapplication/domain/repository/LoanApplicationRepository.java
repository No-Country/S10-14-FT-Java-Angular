package s1014ftjavaangular.loansapplication.domain.repository;

import s1014ftjavaangular.loansapplication.domain.model.dto.request.LoanApplicationStatusDto;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;

public interface LoanApplicationRepository {

    void updateLoanApplicationStatus(final String id, Status status);
    void updateLoanApplication(final Double requestAmount, final String id);

    LoanApplication findById(final String id);
    void saveLoanApplication(final LoanApplication model);

    String findLastLoanApplicationNumber();

    Integer countOfInactiveOrAuditingLoanApplicatin(final String identification);

    void loanApplicationOnReview(String id);

}
