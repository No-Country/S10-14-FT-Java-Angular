package s1014ftjavaangular.loansapplication.domain.usecase;

import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;

public interface LoanApplicationByIdUseCase {
    LoanApplication findById(final String id);
}
