package s1014ftjavaangular.loansapplication.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import s1014ftjavaangular.loansapplication.domain.model.entity.Guarantor;
import s1014ftjavaangular.loansapplication.domain.model.enums.IdentificationType;

import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guarantor")
public class GuarantorEntity {
    @Id
    @Column(name = "loan_application_id")
    private String loanApplicationId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "identification_type", nullable = false)
    private IdentificationType identificationType;

    @Column(name = "identification", nullable = false)
    private String identification;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "apartment")
    private String apartment;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Column(name = "company", nullable = false, length = 60)
    private String company;

    @Column(name = "business_category", nullable = false, length = 60)
    private String businessCategory;

    @Column(name = "occupation", nullable = false, length = 60)
    private String occupation;

    @Column(name = "time_in_company")
    private Integer timeInCompany;

    @Column(name = "monthly_income", nullable = false)
    private Double monthlyIncome;

    @Column(name = "company_city", nullable = false, length = 60)
    private String companyCity;

    @Column(name = "company_state", nullable = false, length = 70)
    private String companyState;

    @Column(name = "company_address", nullable = false, length = 80)
    private String companyAddress;

    @Column(name = "company_apartment", nullable = false, length = 10)
    private String companyApartment;

    @Column(name = "company_zipcode", nullable = false, length = 16)
    private String companyZipcode;

    @Column(name = "company_phone", nullable = false, length = 30)
    private String companyPhone;

    @JsonIgnore
    @ToString.Exclude
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "loan_application_id", referencedColumnName = "loan_application_id")
    private LoanApplicationEntity loansApplication;

    public static final Function<Guarantor, GuarantorEntity> modelToEntity = (model) -> {

        GuarantorEntity entity = new GuarantorEntity();
        entity.setLoanApplicationId(model.getLoanApplicationId());
        entity.setName(model.getName());
        entity.setLastname(model.getLastname());
        entity.setIdentificationType(model.getIdentificationType());
        entity.setIdentification(model.getIdentification());
        entity.setCity(model.getCity());
        entity.setState(model.getState());
        entity.setAddress(model.getAddress());
        entity.setApartment(model.getApartment());
        entity.setPhone(model.getPhone());
        entity.setZipcode(model.getZipcode());
        entity.setCompany(model.getCompany());
        entity.setBusinessCategory(model.getBusinessCategory());
        entity.setOccupation(model.getOccupation());
        entity.setTimeInCompany(model.getTimeInCompany());
        entity.setMonthlyIncome(model.getMonthlyIncome());
        entity.setCompanyCity(model.getCompanyCity());
        entity.setCompanyState(model.getCompanyState());
        entity.setCompanyAddress(model.getCompanyAddress());
        entity.setCompanyApartment(model.getCompanyApartment());
        entity.setCompanyZipcode(model.getCompanyZipcode());
        entity.setCompanyPhone(model.getCompanyPhone());

        return entity;

    };

    public Guarantor entityToModel() {
        return Guarantor.builder()
                .loanApplicationId(this.getLoanApplicationId())
                .name(this.getName())
                .lastname(this.getLastname())
                .identificationType(this.getIdentificationType())
                .identification(this.getIdentification())
                .city(this.getCity())
                .state(this.getState())
                .address(this.getAddress())
                .apartment(this.getApartment())
                .phone(this.getPhone())
                .zipcode(this.getZipcode())
                .company(this.getCompany())
                .businessCategory(this.getBusinessCategory())
                .occupation(this.getOccupation())
                .timeInCompany(this.getTimeInCompany())
                .monthlyIncome(this.getMonthlyIncome())
                .companyCity(this.getCompanyCity())
                .companyState(this.getCompanyState())
                .companyAddress(this.getCompanyAddress())
                .companyApartment(this.getCompanyApartment())
                .companyZipcode(this.getCompanyZipcode())
                .companyPhone(this.getCompanyPhone())
                .build();
    }
}
