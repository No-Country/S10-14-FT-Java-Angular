package s1014ftjavaangular.loansapplication.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s1014ftjavaangular.loansapplication.domain.mapper.LoanApplicationMapper;
import s1014ftjavaangular.loansapplication.domain.model.dto.response.ConfirmDataLoanApplicationDto;
import s1014ftjavaangular.loansapplication.domain.model.entity.LoanApplication;
import s1014ftjavaangular.loansapplication.domain.repository.LoanApplicationRepository;
import s1014ftjavaangular.loansapplication.domain.usecase.ConfirmDataLoanApplicationUseCase;

@RequiredArgsConstructor
@Service
public class ConfirmLoanApplicationImplUseCase implements ConfirmDataLoanApplicationUseCase {
    private final LoanApplicationMapper loanApplicationMapper;

    private final LoanApplicationRepository loanApplicationRepository;


    @Override
    public ConfirmDataLoanApplicationDto findById(String id) {
        LoanApplication loanApplication  = loanApplicationRepository.findById(id);

        if(loanApplication  == null){
            throw new RuntimeException("Loan Application was not found");
        }

        return loanApplicationMapper.modelToDto.apply(loanApplication);
    }
}
