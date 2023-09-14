package s1014ftjavaangular.loansapplication.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.LoanApplicationStatusDto;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;
import s1014ftjavaangular.loansapplication.domain.usecase.UpdateStatusUseCase;

@RestController
@RequestMapping("/api/v1/loansapplication")
@RequiredArgsConstructor
public class LoanApplicationStatusUpdate {
    private final UpdateStatusUseCase updateStatusUseCase;

    @PutMapping("/status")
    private ResponseEntity<Void> updateStatus(@Valid @RequestBody LoanApplicationStatusDto request){
        updateStatusUseCase.updateStatus(request);
        return ResponseEntity.noContent().build();
    }
}
