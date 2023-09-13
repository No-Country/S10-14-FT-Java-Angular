package s1014ftjavaangular.loansapplication.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s1014ftjavaangular.loansapplication.domain.mapper.JobInformationMapper;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.JobInformationDto;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;
import s1014ftjavaangular.loansapplication.domain.repository.JobInformationRepository;
import s1014ftjavaangular.loansapplication.domain.repository.LoanApplicationRepository;
import s1014ftjavaangular.loansapplication.domain.usecase.UpdateJobInformationUseCase;

@Service
@RequiredArgsConstructor
public final class UpdateJobInformationUseCaseImp implements UpdateJobInformationUseCase {

    private final JobInformationRepository repository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final JobInformationMapper mapper;


    @Override
    public void updateJobInformation(JobInformationDto request) {
        //Recupera el Loan Application por ID (Arroja una excepcion si no existe)
        var loanApplication = loanApplicationRepository.findById(request.getLoanApplicationId());
        //Verifica que la solicitud este en estado de "INCOMPLETE"
        if (!loanApplication.getStatus().equals(Status.INCOMPLETE)) {
            throw new RuntimeException("It is not possible to update an application that is no longer Incomplete");
        }
        //Arroja un excepcion si no hay un registro de JobInformation con este ID
        if (loanApplication.getJobInformation() == null) {
            throw new RuntimeException("No Job Information record found with ID " + request.getLoanApplicationId());
        }

        //Se elimina el JobInformation actual
        repository.deleteJobInformation(loanApplication.getLoanApplicationId());
        //Mapeo el DTO al Modelo
        var model = mapper.dtoToModel.apply(request);
        //Vuelve a guardar el JobInformation
        repository.saveJobInformation(model, loanApplication);
    }
}
