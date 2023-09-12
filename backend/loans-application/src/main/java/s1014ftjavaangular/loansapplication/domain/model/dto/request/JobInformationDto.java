package s1014ftjavaangular.loansapplication.domain.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobInformationDto {
    @NotEmpty(message = "Field required")
    private String loanApplicationId;
    @NotEmpty(message = "Field required")
    private String company;
    @NotEmpty(message = "Field required")
    private String businessCategory;
    @NotEmpty(message = "Field required")
    private String occupation;
    @NotNull(message = "Field required")
    private Integer yearsInCompany;
    @NotNull(message = "Field required")
    private Double monthlyIncome;
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
