package s1014ftjavaangular.loansapplication.domain.mapper;

import org.springframework.stereotype.Component;
import s1014ftjavaangular.loansapplication.domain.model.dto.request.GuarantorDto;
import s1014ftjavaangular.loansapplication.domain.model.entity.Guarantor;

import java.util.function.Function;

@Component
public class GuarantorMapper {
    public final Function<Guarantor, GuarantorDto> modelToDto = (model) ->
            GuarantorDto.builder()
                    .loanApplicationId(model.getLoanApplicationId())
                    .name(model.getName())
                    .lastname(model.getLastname())
                    .identificationType(model.getIdentificationType())
                    .identification(model.getIdentification())
                    .city(model.getCity())
                    .state(model.getState())
                    .address(model.getAddress())
                    .apartment(model.getApartment())
                    .phone(model.getPhone())
                    .zipcode(model.getZipcode())
                    .company(model.getCompany())
                    .businessCategory(model.getBusinessCategory())
                    .occupation(model.getOccupation())
                    .timeInCompany(model.getTimeInCompany())
                    .monthlyIncome(model.getMonthlyIncome())
                    .companyCity(model.getCompanyCity())
                    .companyState(model.getCompanyState())
                    .companyAddress(model.getCompanyAddress())
                    .companyApartment(model.getCompanyApartment())
                    .companyZipcode(model.getCompanyZipcode())
                    .companyPhone(model.getCompanyPhone())
                    .build();

    public final Function<GuarantorDto, Guarantor> dtoToModel = (dto) ->
            Guarantor.builder()
                    .loanApplicationId(dto.getLoanApplicationId())
                    .name(dto.getName())
                    .lastname(dto.getLastname())
                    .identificationType(dto.getIdentificationType())
                    .identification(dto.getIdentification())
                    .city(dto.getCity())
                    .state(dto.getState())
                    .address(dto.getAddress())
                    .apartment(dto.getApartment())
                    .phone(dto.getPhone())
                    .zipcode(dto.getZipcode())
                    .company(dto.getCompany())
                    .businessCategory(dto.getBusinessCategory())
                    .occupation(dto.getOccupation())
                    .timeInCompany(dto.getTimeInCompany())
                    .monthlyIncome(dto.getMonthlyIncome())
                    .companyCity(dto.getCompanyCity())
                    .companyState(dto.getCompanyState())
                    .companyAddress(dto.getCompanyAddress())
                    .companyApartment(dto.getCompanyApartment())
                    .companyZipcode(dto.getCompanyZipcode())
                    .companyPhone(dto.getCompanyPhone())
                    .build();

}
