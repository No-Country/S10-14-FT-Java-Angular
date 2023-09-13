package s1014ftjavaangular.loansapplication.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s1014ftjavaangular.loansapplication.domain.model.dto.LoanApplicationForCustomer;
import s1014ftjavaangular.loansapplication.domain.repository.LoanApplicationRepository;
import s1014ftjavaangular.loansapplication.domain.usecase.AllLoanApplicationFromCustomerUseCase;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AllLoanApplicationFromCustomerUseCaseImpl implements AllLoanApplicationFromCustomerUseCase {
    private final LoanApplicationRepository repository;

    @Override
    public List<LoanApplicationForCustomer> findByCustomerId(String customerId) {
        var response = repository.findByCustomerId(customerId);

        return response.isEmpty() ? null : response;
    }
}
