package s1014ftjavaangular.loansapplication.infrastructure.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.GeneralDataDto;
import s1014ftjavaangular.loansapplication.domain.usecase.SaveGeneralDataUseCase;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/generaldata")
public class SaveGeneralDataController {

    private final SaveGeneralDataUseCase useCase;

    @PostMapping
    private ResponseEntity<Map<String, String>> saveGeneralData(@Valid @RequestBody GeneralDataDto request) {
        var id = useCase.saveGeneralData(request);

        log.info("guardé registro");
        return ResponseEntity.ok(Map.of("loanApplicationId", id));
    }
}
