package s1014ftjavaangular.loansapplication.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.loansapplication.domain.usecase.AllLoanApplicationFromCustomerUseCase;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loansapplication")
public class LoanApplicationByIdController {
    private final AllLoanApplicationFromCustomerUseCase useCase;

    @GetMapping(value = "/customers/{id}")
    public Set<?> findLoansApplication(@PathVariable("id") String customerId){

        useCase.findByCustomerId(customerId);
        return null;
    }
}
