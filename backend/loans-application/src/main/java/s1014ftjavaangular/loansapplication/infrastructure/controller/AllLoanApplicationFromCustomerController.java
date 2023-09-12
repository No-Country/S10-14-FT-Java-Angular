package s1014ftjavaangular.loansapplication.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.loansapplication.domain.model.dto.LoanApplicationForCustomer;
import s1014ftjavaangular.loansapplication.domain.usecase.AllLoanApplicationFromCustomerUseCase;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loanapplication")
public class AllLoanApplicationFromCustomerController {
    private final AllLoanApplicationFromCustomerUseCase useCase;

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<List<LoanApplicationForCustomer>> findLoansApplication(@PathVariable("id") String customerId) {
        var response = useCase.findByCustomerId(customerId);

        return (response == null)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(response);
    }
}
