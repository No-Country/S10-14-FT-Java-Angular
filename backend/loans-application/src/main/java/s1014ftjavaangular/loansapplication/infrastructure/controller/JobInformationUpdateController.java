package s1014ftjavaangular.loansapplication.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.JobInformationDto;
import s1014ftjavaangular.loansapplication.domain.usecase.UpdateJobInformationUseCase;

@RestController
@RequestMapping("/api/v1/jobinformation")
@RequiredArgsConstructor
public class JobInformationUpdateController {
    private final UpdateJobInformationUseCase updateJobInformationUseCase;

    @PutMapping
    private ResponseEntity<Void> updateJobInformation(@Valid @RequestBody JobInformationDto request) {
        updateJobInformationUseCase.updateJobInformation(request);
        return ResponseEntity.noContent().build();
    }
}
