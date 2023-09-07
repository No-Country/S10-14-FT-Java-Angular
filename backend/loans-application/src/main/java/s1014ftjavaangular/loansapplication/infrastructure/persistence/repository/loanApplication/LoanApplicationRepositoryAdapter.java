package s1014ftjavaangular.loansapplication.infrastructure.persistence.repository.loanApplication;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.LoanApplicationStatusDto;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;
import s1014ftjavaangular.loansapplication.domain.repository.LoanApplicationRepository;
import s1014ftjavaangular.loansapplication.infrastructure.persistence.entities.LoanApplicationEntity;

@RequiredArgsConstructor
@Repository
public class LoanApplicationRepositoryAdapter implements LoanApplicationRepository {
    private final LoanApplicationJpaRepository jpaRepository;

    @Transactional
    @Override
    public String findLastLoanApplicationNumber(){
        var lastNumber = jpaRepository.findLastLoanApplicationNumber();
        return lastNumber.isEmpty() ? "" : lastNumber.get();

    }

    @Override
    public void loanApplicationOnReview(String id) {
        var entity = jpaRepository.findById(id).get();
        if (entity.getJobInformation() == null && entity.getGuarantor() == null){
            throw new RuntimeException("Please fill the Job Information and Guarantor fields");
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
        if (!entity.getStatus().equals(Status.AUDITING)){
            throw new RuntimeException("It is not possible to update an application that is not being auditing");
        }
        entity.setStatus(status);
        jpaRepository.save(entity);
    }

    @Transactional
    @Override
    public void updateLoanApplication(Double updatedRequestedAmount, String id) {
        var entity = jpaRepository.findById(id).get();
        entity.setRequestedAmount(updatedRequestedAmount);
        jpaRepository.save(entity);
    }

    @Transactional
    @Override
    public Integer countOfInactiveOrAuditingLoanApplicatin(String identification) {
        if(identification == null) throw new IllegalArgumentException("Identification cannot be empty");

        return jpaRepository.countIncompleteOrAuditingStatusLoanApplication(identification);
    }

    @Transactional
    @Override
    public LoanApplication findById(String id) {
        if(id == null) throw new IllegalArgumentException("ID cannot be empty");

        var loan = jpaRepository.findById(id);

        if(loan.isEmpty()) throw new NotFoundException("Loan application with id " + id + " was not found.");

        var loanEntity = loan.get();

        var loanModel = loanEntity.entityToModel();

        return loanModel;
    }

    @Override
    public void saveLoanApplication(LoanApplication model) {
        if(model == null) throw new IllegalArgumentException("The request cannot be empty");

        jpaRepository.save(LoanApplicationEntity.modelToEntity(model));
    }

}
