package s1014ftjavaangular.loansapplication.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JobInformation {

    private String loanApplicationId;
    private String company;
    private String businessCategory;
    private String occupation;

    private Integer yearsInCompany;
    private Double monthlyIncome;

    private String city;
    private String state;
    private String address;
    private String apartment;
    private String zipcode;
    private String phone;
}
