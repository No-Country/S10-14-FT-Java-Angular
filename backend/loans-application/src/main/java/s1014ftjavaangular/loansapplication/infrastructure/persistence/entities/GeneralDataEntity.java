package s1014ftjavaangular.loansapplication.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import s1014ftjavaangular.loansapplication.domain.model.entity.GeneralData;
import s1014ftjavaangular.loansapplication.domain.model.enums.IdentificationType;

import java.time.LocalDate;
import java.util.function.Function;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "general_data")
public class GeneralDataEntity {

    @Id
    @Column(name = "loan_application_id")
    private String loanApplicationId;

    @Column(name = "identification", nullable = false)
    private String identification;

    @Column(name = "identification_type", nullable = false)
    private IdentificationType identificationType;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Column(name = "nationality", nullable = false)
    private String nationality;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "apartment", nullable = false)
    private String apartment;

    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Column(name = "phone", nullable = false)
    private String phone;

    @JsonIgnore
    @ToString.Exclude
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "loan_application_id", referencedColumnName = "loan_application_id")
    private LoanApplicationEntity loansApplication;

    public static final Function<GeneralData, GeneralDataEntity> modelToEntity = (model) -> {

        GeneralDataEntity entity = new GeneralDataEntity();
        entity.setLoanApplicationId(model.getLoanApplicationId());
        entity.setIdentification(model.getIdentification());
        entity.setIdentificationType(model.getIdentificationType());
        entity.setName(model.getName());
        entity.setLastname(model.getLastname());
        entity.setGenre(model.getGenre());
        entity.setBirthdate(model.getBirthdate());
        entity.setNationality(model.getNationality());
        entity.setCity(model.getCity());
        entity.setState(model.getState());
        entity.setAddress(model.getAddress());
        entity.setApartment(model.getApartment());
        entity.setZipcode(model.getZipcode());
        entity.setPhone(model.getPhone());

        return entity;
    };

    public GeneralData entityToModel() {
        return GeneralData.builder()
                .loanApplicationId(this.getLoanApplicationId())
                .identification(this.getIdentification())
                .identificationType(this.getIdentificationType())
                .name(this.getName())
                .lastname(this.getLastname())
                .genre(this.getGenre())
                .birthdate(this.getBirthdate())
                .nationality(this.getNationality())
                .state(this.getState())
                .city(this.getCity())
                .address(this.getAddress())
                .apartment(this.getApartment())
                .zipcode(this.getZipcode())
                .phone(this.getPhone())
                .build();
    }
}
