package s1014ftjavaangular.loansapplication.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.LoanApplicationStatusDto;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;
import s1014ftjavaangular.loansapplication.domain.repository.LoanApplicationRepository;
import s1014ftjavaangular.loansapplication.domain.usecase.UpdateStatusUseCase;

@Service
@RequiredArgsConstructor
public class UpdateStatusUseCaseImpl implements UpdateStatusUseCase {

    private final LoanApplicationRepository repository;

    @Override
    public void updateStatus(LoanApplicationStatusDto request) {
        if (!request.getStatus().equals(Status.APPROVED) && request.getStatus().equals(Status.DECLINED)) {
            throw new RuntimeException("Please select a correct status");
        }
        repository.updateLoanApplicationStatus(request.getLoanApplicationId(), request.getStatus());
    }

}