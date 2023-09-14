package s1014ftjavaangular.loansapplication.domain.usecase;

import s1014ftjavaangular.loansapplication.domain.model.dto.request.LoanApplicationStatusDto;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;

public interface UpdateStatusUseCase {
    void updateStatus(LoanApplicationStatusDto request);
}
