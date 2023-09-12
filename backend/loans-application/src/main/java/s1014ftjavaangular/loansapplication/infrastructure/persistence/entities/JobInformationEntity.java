package s1014ftjavaangular.loansapplication.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import s1014ftjavaangular.loansapplication.domain.model.entity.JobInformation;

import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job_information")
public class JobInformationEntity {

    @Id
    @Column(name = "loan_application_id")
    private String loanApplicationId;

    @Column(name = "company", nullable = false, length = 60)
    private String company;
    @Column(name = "business_category", nullable = false, length = 60)
    private String businessCategory;

    @Column(name = "occupation", nullable = false, length = 60)
    private String occupation;

    @Column(name = "years_in_company")
    private Integer yearsInCompany;

    @Column(name = "monthly_income", nullable = false)
    private Double monthlyIncome;

    @Column(name = "city", nullable = false, length = 60)
    private String city;

    @Column(name = "state", nullable = false, length = 70)
    private String state;

    @Column(name = "address", nullable = false, length = 80)
    private String address;

    @Column(name = "apartment", nullable = false, length = 10)
    private String apartment;

    @Column(name = "zipcode", nullable = false, length = 16)
    private String zipcode;

    @Column(name = "phone", nullable = false, length = 30)
    private String phone;

    @JsonIgnore
    @ToString.Exclude
    @MapsId
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_application_id", referencedColumnName = "loan_application_id")
    private LoanApplicationEntity loansApplication;


    public static final Function<JobInformation, JobInformationEntity> modelToEntity = (model) -> {

        JobInformationEntity entity = new JobInformationEntity();
        entity.setLoanApplicationId(model.getLoanApplicationId());
        entity.setCompany(model.getCompany());
        entity.setBusinessCategory(model.getBusinessCategory());
        entity.setOccupation(model.getOccupation());
        entity.setYearsInCompany(model.getYearsInCompany());
        entity.setMonthlyIncome(model.getMonthlyIncome());
        entity.setCity(model.getCity());
        entity.setState(model.getState());
        entity.setAddress(model.getAddress());
        entity.setApartment(model.getApartment());
        entity.setZipcode(model.getZipcode());
        entity.setPhone(model.getPhone());

        return entity;
    };

    public JobInformation entityToModel() {
        return JobInformation.builder()
                .loanApplicationId(this.getLoanApplicationId())
                .company(this.getCompany())
                .businessCategory(this.getBusinessCategory())
                .occupation(this.getOccupation())
                .yearsInCompany(this.getYearsInCompany())
                .monthlyIncome(this.getMonthlyIncome())
                .city(this.getCity())
                .state(this.getState())
                .address(this.getAddress())
                .apartment(this.getApartment())
                .zipcode(this.getZipcode())
                .phone(this.getPhone())
                .build();
    }
}
