package s1014ftjavaangular.loansapplication.domain.usecase;

import s1014ftjavaangular.loansapplication.domain.model.dto.request.LoanApplicationStatusDto;

public interface UpdateStatusUseCase {
    void updateStatus(LoanApplicationStatusDto request);
}
