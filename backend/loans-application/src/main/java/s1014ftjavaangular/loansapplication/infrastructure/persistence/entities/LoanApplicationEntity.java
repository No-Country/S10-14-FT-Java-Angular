package s1014ftjavaangular.loansapplication.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;

import java.time.LocalDate;
import java.util.Optional;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loans_application")
public class LoanApplicationEntity {
    @Id
    @Column(name = "loan_application_id")
    private String loanApplicationId;

    @Column(name = "customer_id", nullable = false)
    private String customersUuid;

    @Column(name = "loan_application_number", nullable = false, unique = true)
    private String loanApplicationNumber;

    @Column(name = "request_amount", nullable = false)
    private Double requestedAmount;

    @Column(name = "create_at", nullable = false)
    private LocalDate createAt;

    @OneToOne(mappedBy = "loansApplication", cascade = CascadeType.ALL)
    private JobInformationEntity jobInformation;

    @OneToOne(mappedBy = "loansApplication", cascade = CascadeType.ALL)
    private GuarantorEntity guarantor;

    @OneToOne(mappedBy = "loansApplication")
    private GeneralDataEntity generalData;

    @OneToOne(mappedBy = "loansApplicationId", cascade = CascadeType.ALL)
    private CreditAuditEntity creditAuditId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public static LoanApplicationEntity modelToEntity(LoanApplication model) {
        return LoanApplicationEntity.builder()
                .loanApplicationId(model.getLoanApplicationId())
                .loanApplicationNumber(model.getLoanApplicationNumber())
                .customersUuid(model.getCustomersUuid())
                .requestedAmount(model.getRequestedAmount())
                .createAt(model.getCreateAt())
                .status(model.getStatus())

                .jobInformation(
                        Optional.ofNullable( model.getJobInformation() )
                                .flatMap(jobInformation -> Optional.of(JobInformationEntity.modelToEntity.apply(jobInformation)))
                                .orElse(null)
                )
                .guarantor(
                        Optional.ofNullable( model.getGuarantor() )
                                .flatMap(guarantor -> Optional.of(GuarantorEntity.modelToEntity.apply(guarantor)))
                                .orElse(null)
                )
                .generalData(
                        Optional.ofNullable( model.getGeneralData() )
                        .flatMap(generalData -> Optional.of(GeneralDataEntity.modelToEntity.apply(generalData)))
                        .orElse(null)
                )
                .creditAuditId(Optional.ofNullable( model.getCreditAudit() )
                        .flatMap(creditAudit -> Optional.of(CreditAuditEntity.modelToEntity.apply(creditAudit)))
                        .orElse(null)
                )
                .build();
    }

    public LoanApplication entityToModel() {
        return LoanApplication.builder()
                .loanApplicationId(Optional.ofNullable( this.getLoanApplicationId() ).orElse(null))
                .loanApplicationNumber(Optional.ofNullable( this.getLoanApplicationNumber() ).orElse(null))
                .requestedAmount(Optional.ofNullable( this.getRequestedAmount() ).orElse(null))
                .customersUuid(Optional.ofNullable( this.getCustomersUuid() ).orElse(null))
                .status(Optional.ofNullable( this.getStatus() ).orElse(null))
                .createAt(Optional.ofNullable( this.getCreateAt() ).orElse(null))
                .generalData(Optional.ofNullable( this.getGeneralData() ).orElse(null).entityToModel())
                .jobInformation(
                        Optional.ofNullable( this.getJobInformation() )
                                .flatMap(jobInformationEntity-> Optional.of(jobInformationEntity.entityToModel()))
                                .orElse(null) )
                .guarantor(
                        Optional.ofNullable( this.getGuarantor() )
                                .flatMap(guarantorEntity-> Optional.of(guarantorEntity.entityToModel()))
                                .orElse(null)
                )
                .build();

    }

}
