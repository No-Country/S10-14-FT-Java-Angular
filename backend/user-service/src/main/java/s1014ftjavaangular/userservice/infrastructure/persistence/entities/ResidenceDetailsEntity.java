package s1014ftjavaangular.userservice.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.StringUtils;
import s1014ftjavaangular.userservice.domain.model.entity.ResidenceDetails;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "residence_details")
public class ResidenceDetailsEntity {
    @Id
    @Column(name = "user_id")
    private String residenceUuid;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "address")
    private String address;

    @Column(name = "apartment")
    private String apartment;

    @Column(name = "zipcode")
    private String zipCode;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @MapsId
    private UserEntity user;

    public ResidenceDetails entityToModel(){
        return ResidenceDetails.builder()
                .id(StringUtils.hasText(this.getResidenceUuid()) ?  this.getResidenceUuid() : null)
                .state(StringUtils.hasText(this.getState()) ? this.getState() : null)
                .city(StringUtils.hasText(this.getCity()) ? this.getCity() : null)
                .zipCode(StringUtils.hasText(this.getZipCode()) ? this.getZipCode() : null)
                .address(StringUtils.hasText(this.getAddress()) ? this.getAddress() : null)
                .apartment(StringUtils.hasText(this.getApartment()) ? this.getApartment() : null)
                .build();
    }

    public static ResidenceDetailsEntity modelToEntity(ResidenceDetails residenceDetails){
        return ResidenceDetailsEntity.builder()
                .city(Optional.of( residenceDetails.getCity() ).orElse(null))
                .state(Optional.of( residenceDetails.getState() ).orElse(null))
                .zipCode(Optional.of( residenceDetails.getZipCode() ).orElse(null))
                .address(Optional.of( residenceDetails.getAddress() ).orElse(null))
                .apartment(Optional.of( residenceDetails.getApartment() ).orElse(null))
                .build();
    }

    public ResidenceDetailsEntity update(ResidenceDetails residenceDetails){
        this.setAddress(Optional.of( residenceDetails.getAddress() ).orElse(this.getAddress()));
        this.setState(Optional.of( Optional.of( residenceDetails.getState() ).orElse(this.getState()) ).orElse(this.getState()));
        this.setZipCode(Optional.of( Optional.of( residenceDetails.getZipCode() ).orElse(this.getZipCode()) ).orElse(this.getZipCode()));
        this.setCity(Optional.of( residenceDetails.getCity() ).orElse(this.getCity()));
        this.setApartment(Optional.of( residenceDetails.getApartment() ).orElse(this.getApartment()));
        return this;
    }
}
