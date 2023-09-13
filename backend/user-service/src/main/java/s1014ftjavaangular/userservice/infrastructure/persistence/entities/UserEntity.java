package s1014ftjavaangular.userservice.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import s1014ftjavaangular.userservice.domain.model.dto.request.UserRequest;
import s1014ftjavaangular.userservice.domain.model.entity.User;
import s1014ftjavaangular.userservice.domain.model.enums.CivilStatus;
import s1014ftjavaangular.userservice.domain.model.enums.Genre;
import s1014ftjavaangular.userservice.domain.model.mapper.UserMapper;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity implements Serializable {

        @Id
        @Column(name = "user_id")
        private String userUuid;

        @Column(name = "identifier")
        private String identifier;
        @Column(name = "identifier_number")
        private String identifierNumber;

        @Column(name = "number", nullable = false)
        private String number;

        @Column(name = "type", nullable = false)
        private String type;

        @Enumerated(EnumType.STRING)
        @Column(name = "genre")
        private Genre genre;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "last_name", nullable = false)
        private String lastName;

        @Column(name = "birth_day")
        private LocalDate birthDay;

        @Column(name = "phone")
        private String phone;

        @Column(name = "nationality")
        private String nationality;

        @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private ResidenceDetailsEntity residenceDetails;

        @Column(name = "blacklist", nullable = false)
        private Boolean blackList;

        public static UserEntity modelToEntity(User model){
                var entity = new UserEntity();
                entity.setUserUuid(Optional.ofNullable( model.getId() ).orElse(null));
                entity.setName(Optional.ofNullable( model.getName() ).orElse(null));
                entity.setLastName(Optional.ofNullable( model.getLastName() ).orElse(null));
                entity.setType(Optional.ofNullable( model.getType() ).orElse(null));
                entity.setNumber(Optional.ofNullable( model.getNumber() ).orElse(null));
                entity.setBlackList(Optional.ofNullable( model.getBlackList() ).orElse(null));
                entity.setIdentifier(Optional.ofNullable( model.getIdentifier() ).orElse(null));
                entity.setIdentifierNumber(Optional.ofNullable( model.getIdentifierNumber() ).orElse(null));
                entity.setGenre(Optional.ofNullable( model.getGenre() ).orElse(null));
                entity.setBirthDay(Optional.ofNullable( model.getBirthDay() ).orElse(null));
                entity.setPhone(Optional.ofNullable( model.getPhone() ).orElse(null));
                entity.setNationality(Optional.ofNullable( model.getNationality() ).orElse(null));
                //entity.setCreateAt(LocalDate.now());
                entity.setResidenceDetails(
                        Optional.ofNullable( model.getResidenceDetails() )
                        .flatMap(residenceDetails1 ->
                                Optional.of( ResidenceDetailsEntity.modelToEntity(residenceDetails1) )
                                        .flatMap(residenceDetailsEntity -> {
                                                residenceDetailsEntity.setUser(entity);
                                                residenceDetailsEntity.setResidenceUuid(entity.getUserUuid());
                                                return Optional.of(residenceDetailsEntity);
                                        })
                        )
                        .orElse(null));
          return entity;
        }

        public UserEntity update(User model){
                this.setIdentifier(
                        Optional.ofNullable( model.getIdentifier() )
                        .filter(StringUtils::hasText)
                        .filter(identifier-> this.getIdentifier() == null)
                        .orElse(this.getIdentifier())
                );

                this.setIdentifierNumber(
                        Optional.ofNullable( model.getIdentifierNumber() )
                                .filter(StringUtils::hasText)
                                .filter(identifierNUmber-> this.getIdentifierNumber() == null)
                                .orElse(this.getIdentifierNumber())
                );

                this.setGenre(
                        Optional.ofNullable( model.getGenre() )
                                .filter(genre-> StringUtils.hasText(genre.name()))
                                .orElse(this.getGenre())
                );

                this.setName(
                        Optional.ofNullable( model.getName() )
                        .filter(StringUtils::hasText)
                        .orElse(this.getName())
                );

                this.setLastName(
                        Optional.ofNullable( model.getLastName() )
                        .filter(StringUtils::hasText)
                        .orElse(this.getLastName())
                );

                this.setBirthDay(
                        Optional.ofNullable( model.getBirthDay() ).orElse(this.getBirthDay())
                );
                this.setPhone(
                        Optional.ofNullable( model.getPhone() )
                                .filter(StringUtils::hasText)
                                .orElse(this.getPhone())
                );
                this.setNationality(
                        Optional.ofNullable( model.getNationality() )
                                .filter(StringUtils::hasText)
                                .orElse(this.getNationality())
                );
//
                this.setResidenceDetails(
                        Optional.ofNullable( model.getResidenceDetails() )
                                .flatMap(residence->
                                        (this.getResidenceDetails() != null)
                                                ? Optional.of( this.getResidenceDetails().update(residence) )
                                                : Optional.of( ResidenceDetailsEntity.modelToEntity(residence) )
                                ).orElse(this.getResidenceDetails())
                );

                this.getResidenceDetails().setResidenceUuid(this.getUserUuid());
                this.getResidenceDetails().setUser(this);
                return this;
        }
        //
        public User entityToModel(){
                return User.builder()
                        .id(this.getUserUuid())
                        .number(this.getNumber())
                        .identifier( this.getIdentifier() )
                        .identifierNumber( this.getIdentifierNumber() )
                        .type(this.getType())
                        .name(this.getName())
                        .lastName(this.getLastName())
                        .genre( this.getGenre() )
                        .birthDay( this.getBirthDay() )
                        .nationality( this.getNationality() )
                        .phone( this.getPhone() )
                        .blackList(this.getBlackList())
                        .residenceDetails(this.getResidenceDetails() != null ? this.getResidenceDetails().entityToModel() : null )
                        .build();
        }
}
