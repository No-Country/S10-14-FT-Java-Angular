package s1014ftjavaangular.userservice.domain.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s1014ftjavaangular.userservice.domain.model.entity.ResidenceDetails;
import s1014ftjavaangular.userservice.domain.model.enums.Genre;
import s1014ftjavaangular.userservice.domain.model.enums.CivilStatus;
import s1014ftjavaangular.userservice.domain.model.entity.PhoneDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRequest implements Serializable {
    @NotEmpty(message = "ID cannot be empty")
    private String id;
    private String identifier;
    private String identifierNumber;
    private Genre genre;
    private String name;
    private String lastName;
    private String nationality;
    private LocalDate birthDay;
    private String phone;
    private ResidenceDetailsDto residenceDetails;
}
