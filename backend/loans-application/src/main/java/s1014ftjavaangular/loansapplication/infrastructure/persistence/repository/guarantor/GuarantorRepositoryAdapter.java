package s1014ftjavaangular.loansapplication.infrastructure.persistence.repository.guarantor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import s1014ftjavaangular.loansapplication.domain.model.entity.Guarantor;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;
import s1014ftjavaangular.loansapplication.domain.repository.GuarantorRepository;
import s1014ftjavaangular.loansapplication.infrastructure.persistence.entities.GuarantorEntity;
import s1014ftjavaangular.loansapplication.infrastructure.persistence.entities.LoanApplicationEntity;


@Repository
@RequiredArgsConstructor
public class GuarantorRepositoryAdapter implements GuarantorRepository {
    private final GuarantorJpaRepository jpaRepository;

    @Override
    public void saveGuarantor(Guarantor model, LoanApplication loanApplication) {
        if (model == null) throw new IllegalArgumentException("The request cannot be empty");

        var loanApplicationEntity = LoanApplicationEntity.modelToEntity(loanApplication);
        var guarantorEntity = GuarantorEntity.modelToEntity.apply(model);
        guarantorEntity.setLoansApplication(loanApplicationEntity);
        loanApplicationEntity.setGuarantor(guarantorEntity);

        jpaRepository.save(guarantorEntity);
    }


    @Transactional
    @Override
    public void deleteGuarantor(String id) {
        jpaRepository.deleteById(id);
    }
}
