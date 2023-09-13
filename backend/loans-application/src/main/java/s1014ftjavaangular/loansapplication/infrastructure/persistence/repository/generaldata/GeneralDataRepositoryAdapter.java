package s1014ftjavaangular.loansapplication.infrastructure.persistence.repository.generaldata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import s1014ftjavaangular.loansapplication.domain.model.entity.GeneralData;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;
import s1014ftjavaangular.loansapplication.domain.repository.GeneralDataRepository;
import s1014ftjavaangular.loansapplication.infrastructure.persistence.entities.GeneralDataEntity;
import s1014ftjavaangular.loansapplication.infrastructure.persistence.entities.LoanApplicationEntity;

@Repository
@RequiredArgsConstructor
public class GeneralDataRepositoryAdapter implements GeneralDataRepository {
    private final GeneralDataJpaRepository jpaRepository;

    @Override
    public void deleteGeneralDataById(String id) {
        jpaRepository.deleteById(id);
    }


    @Override
    public void saveGeneralData(GeneralData model, LoanApplication loanApplication) {
        if (model == null) throw new IllegalArgumentException("The request cannot be empty");

        var loanApplicationEntity = LoanApplicationEntity.modelToEntity(loanApplication);
        var generalDataEntity = GeneralDataEntity.modelToEntity.apply(model);
        generalDataEntity.setLoansApplication(loanApplicationEntity);
        loanApplicationEntity.setGeneralData(generalDataEntity);

        jpaRepository.save(generalDataEntity);
    }

}
