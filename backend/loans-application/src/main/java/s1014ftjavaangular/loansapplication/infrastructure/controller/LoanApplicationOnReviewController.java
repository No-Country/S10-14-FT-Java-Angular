package s1014ftjavaangular.loansapplication.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s1014ftjavaangular.loansapplication.domain.usecase.LoanApplicationOnReviewUseCase;

@RestController
@RequestMapping("/api/v1/onreviewstatus")
@RequiredArgsConstructor
public class LoanApplicationOnReviewController {
    private final LoanApplicationOnReviewUseCase useCase;

    @PutMapping("/{id}")
    private ResponseEntity<Void> loanApplicationOnReview(@PathVariable String id){
        useCase.LoanApplicationOnReview(id);
        return ResponseEntity.noContent().build();
    }
}
