package s1014ftjavaangular.userservice.domain.model.mapper;

import org.springframework.stereotype.Component;
import s1014ftjavaangular.userservice.domain.model.dto.request.ResidenceDetailsDto;
import s1014ftjavaangular.userservice.domain.model.dto.request.UserRequest;
import s1014ftjavaangular.userservice.domain.model.dto.response.UserResponse;
import s1014ftjavaangular.userservice.domain.model.entity.ResidenceDetails;
import s1014ftjavaangular.userservice.domain.model.entity.User;

import java.util.Optional;

@Component
public class UserMapper {

    public User userDtoToModel(UserRequest request) {

        return User.builder()
                .id(request.getId())
                .identifier(Optional.ofNullable( request.getIdentifier() ).orElse(""))
                .identifierNumber(Optional.ofNullable( request.getIdentifierNumber() ).orElse(""))
                .genre(Optional.ofNullable( request.getGenre() ).orElse(null))
                .name(Optional.ofNullable( request.getName() ).orElse(""))
                .lastName(Optional.ofNullable( request.getLastName() ).orElse(""))
                .nationality(Optional.ofNullable(request.getNationality() ).orElse(""))
                .birthDay(Optional.ofNullable(request.getBirthDay() ).orElse(null))
                .phone(Optional.ofNullable( request.getPhone() ).orElse(""))
                .residenceDetails(
                        Optional.ofNullable(request.getResidenceDetails())
                                .flatMap(residenceDetailsDto -> Optional.of(this.residenceDetailsDtoToModel(residenceDetailsDto)))
                                .orElse(null)
                )
                .build();
    }

    public UserResponse userModelToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .identifier(user.getIdentifier())
                .identifierNumber(user.getIdentifierNumber())
                .genre(user.getGenre())
                .type(user.getType())
                .number(user.getNumber())
                .name(user.getName())
                .lastName(user.getLastName())
                .nationality(user.getNationality())
                .birthDay(user.getBirthDay())
                .phone(user.getPhone())
                .residenceDetails(user.getResidenceDetails() != null ? this.residenceDetailsModelToDto(user.getResidenceDetails()) : null)
                .build();
    }

    public ResidenceDetails residenceDetailsDtoToModel(ResidenceDetailsDto residenceDetailsDto){
        return ResidenceDetails.builder()
                .city(Optional.ofNullable( residenceDetailsDto.getCity() ).orElse(""))
                .state(Optional.ofNullable( residenceDetailsDto.getState() ).orElse(""))
                .address(Optional.ofNullable( residenceDetailsDto.getAddress() ).orElse(""))
                .apartment(Optional.ofNullable( residenceDetailsDto.getApartment() ).orElse(""))
                .zipCode(Optional.ofNullable( residenceDetailsDto.getZipCode() ).orElse(""))
                .build();
    }

    public ResidenceDetailsDto residenceDetailsModelToDto(ResidenceDetails residenceDetails){
        return ResidenceDetailsDto.builder()
                .city(residenceDetails.getCity())
                .state(residenceDetails.getState())
                .address(residenceDetails.getAddress())
                .apartment(residenceDetails.getApartment())
                .zipCode(residenceDetails.getZipCode())
                .build();
    }
}
