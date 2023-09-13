package s1014ftjavaangular.loansapplication.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.JobInformationDto;
import s1014ftjavaangular.loansapplication.domain.usecase.SaveJobInformationUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobinformation")
public class SaveJobInformationController {

    private final SaveJobInformationUseCase useCase;

    @PostMapping
    private ResponseEntity<Void> saveJobInformation(@Valid @RequestBody JobInformationDto request) {
        useCase.saveJobInformation(request);
        return ResponseEntity.noContent().build();
    }
}
