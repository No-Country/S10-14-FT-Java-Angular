package s1014ftjavaangular.loansapplication.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.loansapplication.domain.model.dto.response.ConfirmDataLoanApplicationDto;
import s1014ftjavaangular.loansapplication.domain.usecase.ConfirmDataLoanApplicationUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loanapplication")
public class ConfirmLoanApplicationController {

    private final ConfirmDataLoanApplicationUseCase useCase;

    @GetMapping(value = "/confirm/{id}")
    public ResponseEntity<ConfirmDataLoanApplicationDto> findLoanApplication(@PathVariable("id") String id){
        var response = useCase.findById(id);
        return (response == null)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(response);
    }
}
