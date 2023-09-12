package s1014ftjavaangular.loansapplication.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s1014ftjavaangular.loansapplication.domain.mapper.GuarantorMapper;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.GuarantorDto;
import s1014ftjavaangular.loansapplication.domain.model.enums.Status;
import s1014ftjavaangular.loansapplication.domain.repository.GuarantorRepository;
import s1014ftjavaangular.loansapplication.domain.repository.LoanApplicationRepository;
import s1014ftjavaangular.loansapplication.domain.usecase.UpdateGuarantorUseCase;

@Service
@RequiredArgsConstructor
public class UpdateGuarantorUseCaseImp implements UpdateGuarantorUseCase {
    private final GuarantorRepository repository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final GuarantorMapper mapper;

    @Override
    public void updateGuarantor(GuarantorDto request) {
        //Recupera el Loan Application por ID (Arroja una excepcion si no existe)
        var loanApplication = loanApplicationRepository.findById(request.getLoanApplicationId());

        //Verifica que la solicitud este en estado de "INCOMPLETE"
        if (!loanApplication.getStatus().equals(Status.INCOMPLETE)) {
            throw new RuntimeException("It is not possible to update an application that is no longer Incomplete");
        }

        //Arroja un excepcion si no hay un registro de JobInformation con este ID
        if (loanApplication.getGuarantor() == null) {
            throw new RuntimeException("No Guarantor record found with ID " + request.getLoanApplicationId());
        }

        //Se elimina el garante
        repository.deleteGuarantor(request.getLoanApplicationId());

        //Se mapea de DTO a Modelo
        var model = mapper.dtoToModel.apply(request);

        //Se vuelve a guardar el garante
        repository.saveGuarantor(model, loanApplication);
    }
}