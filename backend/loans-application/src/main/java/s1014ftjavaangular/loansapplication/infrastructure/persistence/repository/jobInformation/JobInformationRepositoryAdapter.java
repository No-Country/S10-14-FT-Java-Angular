package s1014ftjavaangular.loansapplication.infrastructure.persistence.repository.jobInformation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import s1014ftjavaangular.loansapplication.domain.model.entity.JobInformation;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;
import s1014ftjavaangular.loansapplication.domain.repository.JobInformationRepository;
import s1014ftjavaangular.loansapplication.infrastructure.persistence.entities.JobInformationEntity;
import s1014ftjavaangular.loansapplication.infrastructure.persistence.entities.LoanApplicationEntity;


@Repository
@RequiredArgsConstructor
public class JobInformationRepositoryAdapter implements JobInformationRepository {
    private final JobInformationJpaRepository jpaRepository;

    @Override
    public void deleteJobInformation(String id) {
        jpaRepository.deleteById(id);

    }


    @Override
    public void saveJobInformation(JobInformation model, LoanApplication loanApplication) {
        if (model == null) throw new IllegalArgumentException("The request cannot be empty");

        var loanApplicationEntity = LoanApplicationEntity.modelToEntity(loanApplication);
        var jobInformationEntity = JobInformationEntity.modelToEntity.apply(model);
        jobInformationEntity.setLoansApplication(loanApplicationEntity);
        loanApplicationEntity.setJobInformation(jobInformationEntity);
        jpaRepository.save(jobInformationEntity);

    }
}