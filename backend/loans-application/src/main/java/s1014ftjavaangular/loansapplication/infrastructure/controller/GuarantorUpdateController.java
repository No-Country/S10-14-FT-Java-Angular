package s1014ftjavaangular.loansapplication.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.GuarantorDto;
import s1014ftjavaangular.loansapplication.domain.usecase.UpdateGuarantorUseCase;

@RestController
@RequestMapping("/api/v1/guarantor")
@RequiredArgsConstructor
public class GuarantorUpdateController {
    private final UpdateGuarantorUseCase updateGuarantorUseCase;

    @PutMapping
    private ResponseEntity<Void> updateGuarantor(@Valid @RequestBody GuarantorDto request) {
        updateGuarantorUseCase.updateGuarantor(request);
        return ResponseEntity.noContent().build();
    }
}
