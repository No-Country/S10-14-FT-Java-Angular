package s1014ftjavaangular.loansapplication.domain.usecase;

import s1014ftjavaangular.loansapplication.domain.model.dto.LoanApplicationForCustomer;

import java.util.List;

public interface AllLoanApplicationFromCustomerUseCase {

    List<LoanApplicationForCustomer> findByCustomerId(String id);

}
