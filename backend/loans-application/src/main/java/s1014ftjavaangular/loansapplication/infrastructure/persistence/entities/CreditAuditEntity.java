package s1014ftjavaangular.loansapplication.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import s1014ftjavaangular.loansapplication.domain.model.entity.CreditAudit;

import java.time.LocalDate;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_audit")
public class CreditAuditEntity {

    @Id
    @Column(name = "credit_audit_id", nullable = false)
    private String creditAuditId;

    @Column(name = "audit_date", nullable = false)
    private LocalDate auditDate;

    @Column(name = "credit_auditor_id", nullable = false)
    private String creditAuditorId;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "loan_application_id", referencedColumnName = "loan_application_id")
    private LoanApplicationEntity loansApplicationId;

    public static final Function<CreditAudit, CreditAuditEntity> modelToEntity = (model) -> {
        CreditAuditEntity entity = new CreditAuditEntity();
        entity.setCreditAuditId(model.getCreditAuditId());
        entity.setAuditDate(model.getAuditDate());
        entity.setCreditAuditorId(model.getCreditAuditorId());

        return entity;
    };

}