package s1014ftjavaangular.loansapplication.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s1014ftjavaangular.loansapplication.domain.exceptions.ResourceAlreadyExists;
import s1014ftjavaangular.loansapplication.domain.mapper.JobInformationMapper;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.JobInformationDto;
import s1014ftjavaangular.loansapplication.domain.repository.JobInformationRepository;
import s1014ftjavaangular.loansapplication.domain.repository.LoanApplicationRepository;
import s1014ftjavaangular.loansapplication.domain.usecase.SaveJobInformationUseCase;


@Service
@RequiredArgsConstructor
public class SaveJobInformationUseCaseImpl implements SaveJobInformationUseCase {

    private final JobInformationRepository repository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final JobInformationMapper mapper;

    @Override
    public void saveJobInformation(JobInformationDto request) {
        var loanApplication = loanApplicationRepository.findById(request.getLoanApplicationId());
        if(loanApplication == null)
            throw new ResourceAlreadyExists("ID "+request.getLoanApplicationId()+" is not registered for loan application");

        if (loanApplication.getJobInformation() != null)
            throw new ResourceAlreadyExists("Job information is already registered");

        repository.saveJobInformation(mapper.dtoToModel.apply(request), loanApplication);
    }
}
