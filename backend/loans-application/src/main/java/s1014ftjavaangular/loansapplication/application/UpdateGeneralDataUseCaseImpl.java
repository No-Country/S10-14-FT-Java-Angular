package s1014ftjavaangular.loansapplication.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import s1014ftjavaangular.loansapplication.domain.mapper.GeneralDataMapper;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.GeneralDataDto;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;
import s1014ftjavaangular.loansapplication.domain.repository.GeneralDataRepository;
import s1014ftjavaangular.loansapplication.domain.repository.LoanApplicationRepository;
import s1014ftjavaangular.loansapplication.domain.usecase.UpdateGeneralDataUseCase;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateGeneralDataUseCaseImpl implements UpdateGeneralDataUseCase {
    private final GeneralDataRepository generalDataRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final GeneralDataMapper mapper;

    @Override
    public void updateGeneralData(GeneralDataDto request) {
        //Recupera el Loan Application por ID (Arroja una excepcion si no existe)
        var loanApplication = loanApplicationRepository.findById(request.getLoanApplicationId());
        //Verifica que la solicitud este en estado de "INCOMPLETE"
        if (!loanApplication.getStatus().equals(Status.INCOMPLETE)) {
            throw new RuntimeException("It is not possible to update an application that is no longer Incomplete");
        }
        //Si el requested amount de la request es distinto al de la base, llamo al metodo para que se actualice
        if (request.getRequestedAmount() != loanApplication.getRequestedAmount()) {
            log.info("dentro del if update general data");
            loanApplicationRepository.updateLoanApplication(request.getRequestedAmount(), request.getLoanApplicationId());
            loanApplication.setRequestedAmount(request.getRequestedAmount());
        }

        //Se elimina el general data anterior
        generalDataRepository.deleteGeneralDataById(request.getLoanApplicationId());

        //Se mapea el DTO a Modelo
        var model = mapper.dtoToModel.apply(request);

        //Se vuelve a guardar el General Data
        generalDataRepository.saveGeneralData(model, loanApplication);
    }
}
