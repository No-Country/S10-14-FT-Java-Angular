package s1014ftjavaangular.loansapplication.infrastructure.persistence.repository.loanApplication;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import s1014ftjavaangular.loansapplication.domain.model.dto.LoanApplicationForCustomer;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;
import s1014ftjavaangular.loansapplication.domain.repository.LoanApplicationRepository;
import s1014ftjavaangular.loansapplication.infrastructure.persistence.entities.LoanApplicationEntity;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
@Slf4j
public class LoanApplicationRepositoryAdapter implements LoanApplicationRepository {
    private final LoanApplicationJpaRepository jpaRepository;

    @Transactional
    @Override
    public String findLastLoanApplicationNumber() {
        var lastNumber = jpaRepository.findLastLoanApplicationNumber();
        return lastNumber.isEmpty() ? "" : lastNumber.get();
    }

    @Override
    public void loanApplicationOnReview(String id) {
        var entity = jpaRepository.findById(id).get();
        if (entity.getJobInformation() == null){
            throw new RuntimeException("Please fill the Job Information fields");
        }
        if (!entity.getStatus().equals(Status.INCOMPLETE)){
            throw new RuntimeException("You cannot update the loan application");
        }
        entity.setStatus(Status.AUDITING);
        jpaRepository.save(entity);
    }

    @Override
    public void updateLoanApplicationStatus(String id, Status status) {
        var entity = jpaRepository.findById(id).get();
        if (!entity.getStatus().equals(Status.AUDITING)) {
            throw new RuntimeException("It is not possible to update an application that is not being auditing");
        }
        entity.setStatus(status);
        jpaRepository.save(entity);
    }

    @Transactional
    @Override
    public void updateLoanApplication(Double updatedRequestedAmount, String id) {
        jpaRepository.findById(id).map(entity->{
            entity.setRequestedAmount(updatedRequestedAmount);
            return entity;
        });

    }

    @Override
    public List<LoanApplicationForCustomer> findByCustomerId(String customerId) {
        if (customerId == null) throw new IllegalArgumentException("Identification cannot be empty");

        var response = jpaRepository.findByCustomerId(customerId);
        return response.isEmpty() ? List.of() : response.get();
    }

    @Transactional
    public Integer countOfInactiveOrAuditingLoanApplication(String identification, String customerId) {
        if (identification == null) throw new IllegalArgumentException("Identification cannot be empty");

        return jpaRepository.countIncompleteOrAuditingStatusLoanApplication(identification, customerId);
    }

    @Override
    public LoanApplication findById(String id) {
        if (id == null) throw new IllegalArgumentException("ID cannot be empty");

        return jpaRepository
                .findById(id)
                .flatMap(loanApplicationEntity -> {
                    log.info("LOAN APP ENTITY: {}", loanApplicationEntity);
                    return Optional.of(loanApplicationEntity.entityToModel());
                })
                .orElse(null);
    }

    @Override
    public void saveLoanApplication(LoanApplication model) {
        if (model == null) throw new IllegalArgumentException("The request cannot be empty");

        jpaRepository.save(LoanApplicationEntity.modelToEntity(model));
    }

}
