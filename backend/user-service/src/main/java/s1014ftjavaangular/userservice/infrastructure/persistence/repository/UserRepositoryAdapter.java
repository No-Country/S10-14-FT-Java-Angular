package s1014ftjavaangular.userservice.infrastructure.persistence.repository;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.StringUtils;
import s1014ftjavaangular.userservice.domain.model.entity.User;

import s1014ftjavaangular.userservice.domain.model.dto.request.AccountCreatedDto;
import s1014ftjavaangular.userservice.domain.model.dto.request.UserRequest;
import s1014ftjavaangular.userservice.domain.model.dto.response.UserResponse;

import s1014ftjavaangular.userservice.domain.model.mapper.UserMapper;
import s1014ftjavaangular.userservice.domain.repository.UserRepository;
import s1014ftjavaangular.userservice.infrastructure.persistence.entities.UserEntity;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final UserJpaRepository jpaRepository;

    @Override
    public String findLastUserNumber(String type){
        var number1 = jpaRepository.findByNumber(type);
        var number2 = "1";//jpaRepository.findNumberByType(type);
        log.info("Number 1: {}, Number 2: {}", number1, number2);
        return number1;
    }

    @Override
    public List<User> findAll() {
        var entities = jpaRepository.findAll();
        return entities
                .stream()
                .map(UserEntity::entityToModel)
                .toList();
    }

    @Override
    public List<User> findAllByType(String type) {
        var entities = jpaRepository.findAllByType(type);
        return entities
                .stream()
                .map(UserEntity::entityToModel)
                .toList();
    }

    @Override
    public Optional<User> findById(String id) {
        if(id.isEmpty()) throw new IllegalArgumentException("The id cannot be empty");
//
        return jpaRepository
                .findById(id)
                .flatMap(entity-> Optional.of( entity.entityToModel() ))
                .or(Optional::empty);

    }

    @Override
    public void saveUser(final User model){
        if(model == null) throw new IllegalArgumentException("The model to save the user cannot be empty");
        var user = UserEntity.modelToEntity(model);
        jpaRepository.save(user);
    }

    @Transactional
    @Override
    public void update(User model) {
        //Valido que el DTO no venga en null
        if(model == null) throw new IllegalArgumentException("The User cannot be empty in Update");
        var entity =jpaRepository.findById(model.getId())
                .flatMap(user->{
                    user.update(model);
                    return Optional.of(user);
                })
                .orElseThrow(()->{throw new RuntimeException("User with ID "+model.getId()+" not found");});
    }

}