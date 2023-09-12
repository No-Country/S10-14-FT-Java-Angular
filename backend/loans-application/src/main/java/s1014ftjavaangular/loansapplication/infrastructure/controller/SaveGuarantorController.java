package s1014ftjavaangular.loansapplication.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.GuarantorDto;
import s1014ftjavaangular.loansapplication.domain.usecase.SaveGuarantorUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/guarantor")
public class SaveGuarantorController {

    private final SaveGuarantorUseCase useCase;

    @PostMapping
    private ResponseEntity<String> saveGuarantor(@Valid @RequestBody GuarantorDto request) {
        useCase.saveGuarantor(request);
        return ResponseEntity.noContent().build();
    }
}
