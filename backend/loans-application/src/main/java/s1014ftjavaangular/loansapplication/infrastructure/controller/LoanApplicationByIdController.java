package s1014ftjavaangular.loansapplication.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;
import s1014ftjavaangular.loansapplication.domain.usecase.AllLoanApplicationFromCustomerUseCase;
import s1014ftjavaangular.loansapplication.domain.usecase.LoanApplicationByIdUseCase;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loanapplication")
public class LoanApplicationByIdController {
    private final LoanApplicationByIdUseCase useCase;

    @GetMapping(value = "/{id}")
    public ResponseEntity<LoanApplication> findLoansApplication(@PathVariable("id") String loanApplicationId) {

        var response = useCase.findById(loanApplicationId);

        return response == null
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(response);
    }
}
