package s1014ftjavaangular.security.infrastructure.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import s1014ftjavaangular.security.domain.enums.Rol;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class AccountEntity implements Serializable {
    private static final long SerialVersionUUID = 1L;

    @Id
    private String accountUuid;
    @Column(name = "email", nullable = false, length = 128, unique = true)
    @Email(message = "Please enter a valid email!")
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;
    @Column(name = "lastSession", nullable = false)
    private LocalDate lastSession;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Rol rol;

    @Transient
    private String token;

    public AccountEntity(String email, String password, Rol rol) {
        this.accountUuid = UUID.randomUUID().toString();
        this.email = email;
        this.password = password;
        this.createdAt = LocalDate.now();
        this.lastSession = LocalDate.now();
        this.active = true;
        this.rol = rol;
    }

}
