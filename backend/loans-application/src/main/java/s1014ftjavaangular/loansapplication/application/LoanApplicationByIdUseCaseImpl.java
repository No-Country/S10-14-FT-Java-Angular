package s1014ftjavaangular.loansapplication.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;
import s1014ftjavaangular.loansapplication.domain.repository.LoanApplicationRepository;
import s1014ftjavaangular.loansapplication.domain.usecase.LoanApplicationByIdUseCase;

@Service
@RequiredArgsConstructor
public class LoanApplicationByIdUseCaseImpl implements LoanApplicationByIdUseCase {
    private final LoanApplicationRepository repository;

    @Override
    public LoanApplication findById(String id) {
        var response = repository.findById(id);
        return response;
    }
}
