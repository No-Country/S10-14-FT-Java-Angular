package s1014ftjavaangular.loansapplication.domain.usecase;

import s1014ftjavaangular.loansapplication.domain.model.dto.response.ConfirmDataLoanApplicationDto;

public interface ConfirmDataLoanApplicationUseCase {

    ConfirmDataLoanApplicationDto findById(final String id);
}
