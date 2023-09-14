package s1014ftjavaangular.loansapplication.domain.usecase;

import s1014ftjavaangular.loansapplication.domain.model.dto.LoanApplicationForCustomer;

import java.util.List;
import java.util.Set;

public interface AllLoanApplicationFromCustomerUseCase {

    List<LoanApplicationForCustomer> findByCustomerId(String id);

}
