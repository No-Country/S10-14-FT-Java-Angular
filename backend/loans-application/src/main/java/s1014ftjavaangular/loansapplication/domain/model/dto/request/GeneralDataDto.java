package s1014ftjavaangular.loansapplication.domain.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s1014ftjavaangular.loansapplication.domain.model.enums.IdentificationType;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GeneralDataDto {
    private String loanApplicationId;

    @NotEmpty(message = "Field required")
    private String customersUuid;

    @NotEmpty(message = "Field required")
    private String identification;

    @NotNull(message = "Field required")
    private IdentificationType identificationType;

    @NotEmpty(message = "Field required")
    private String name;

    @NotEmpty(message = "Field required")
    private String lastname;

    @NotEmpty(message = "Field required")
    private String genre;

    @NotNull(message = "Field required")
    private LocalDate birthdate;

    @NotEmpty(message = "Field required")
    private String nationality;

    @NotNull(message = "Field required")
    private Double requestedAmount;

    @NotEmpty(message = "Field required")
    private String city;

    @NotEmpty(message = "Field required")
    private String state;

    @NotEmpty(message = "Field required")
    private String address;

    @NotEmpty(message = "Field required")
    private String apartment;

    @NotEmpty(message = "Field required")
    private String zipcode;

    @NotEmpty(message = "Field required")
    private String phone;
}
