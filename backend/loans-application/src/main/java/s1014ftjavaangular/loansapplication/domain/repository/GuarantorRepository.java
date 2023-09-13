package s1014ftjavaangular.loansapplication.domain.repository;

import s1014ftjavaangular.loansapplication.domain.model.entity.Guarantor;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;

public interface GuarantorRepository {
    void deleteGuarantor(final String id);

    void saveGuarantor(final Guarantor model, final LoanApplication loanApplication);

}
