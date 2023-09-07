package s1014ftjavaangular.loansapplication.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s1014ftjavaangular.loansapplication.domain.repository.LoanApplicationRepository;
import s1014ftjavaangular.loansapplication.domain.usecase.LoanApplicationOnReviewUseCase;

@Service
@RequiredArgsConstructor
public class LoanApplicationOnReviewUseCaseImp implements LoanApplicationOnReviewUseCase {
    private final LoanApplicationRepository repository;

    @Override
    public void LoanApplicationOnReview(String id) {
        repository.loanApplicationOnReview(id);
    }
}
