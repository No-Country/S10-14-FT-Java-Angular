package s1014ftjavaangular.loansapplication.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.GeneralDataDto;
import s1014ftjavaangular.loansapplication.domain.usecase.UpdateGeneralDataUseCase;

@RestController
@RequestMapping("/api/v1/generaldata")
@RequiredArgsConstructor
public class GeneralDataUpdateController {
    private final UpdateGeneralDataUseCase useCase;

    @PutMapping
    public ResponseEntity<Void> updateGeneralData(@Valid @RequestBody GeneralDataDto dto) {
        if (dto.getLoanApplicationId() == null)
            throw new IllegalArgumentException("The loan application ID cannot be empty");
//
        useCase.updateGeneralData(dto);
        return ResponseEntity.noContent().build();
    }
}
