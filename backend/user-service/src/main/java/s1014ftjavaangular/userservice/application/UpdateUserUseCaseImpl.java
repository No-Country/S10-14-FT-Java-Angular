package s1014ftjavaangular.userservice.application;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import s1014ftjavaangular.userservice.domain.model.dto.request.UserRequest;
import s1014ftjavaangular.userservice.domain.model.exception.ResourceAlreadyExists;
import s1014ftjavaangular.userservice.domain.model.exception.UserNotFoundException;
import s1014ftjavaangular.userservice.domain.model.mapper.UserMapper;
import s1014ftjavaangular.userservice.domain.repository.UserRepository;
import s1014ftjavaangular.userservice.domain.usecase.UpdateUserUseCase;


@RequiredArgsConstructor
@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository repository;
    private final UserMapper mapper;

//
    @Override
    public void update(UserRequest request) {

     /*
        var modelOptional = repository.findById(request.getId());
        if(modelOptional.isEmpty()) throw new UserNotFoundException("User with ID "+request.getId()+" not found");
        var model = modelOptional.get();

        if(model.getIdentifier() != null && request.getIdentifier() != null && request.getIdentifier() != model.getIdentifier()){
            throw new ResourceAlreadyExists("Identification cannot be updated once added");
        }

        if(model.getIdentifier() == null) {
            model.setIdentifier(request.getIdentifier());
            model.setIdentifierNumber(request.getIdentifierNumber());
        }
        if(request.getResidenceDetails() != null) model.setResidenceDetails(mapper.residenceDetailsDtoToModel(request.getResidenceDetails()));
        if(request.getName() != null) model.setName(request.getName());
        if(request.getLastName() != null) model.setLastName(request.getLastName());
        if(request.getGenre() != null) model.setGenre(request.getGenre());
        if(request.getBirthDay() != null) model.setBirthDay(request.getBirthDay());
        if(request.getPhone() != null) model.setPhone(request.getPhone());
        if(request.getNationality() != null) model.setNationality(request.getPhone());
     */

        repository.update(mapper.userDtoToModel(request));
    }
}
