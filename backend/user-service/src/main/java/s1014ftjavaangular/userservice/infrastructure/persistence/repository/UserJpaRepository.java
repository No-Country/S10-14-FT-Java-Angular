package s1014ftjavaangular.userservice.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import s1014ftjavaangular.userservice.infrastructure.persistence.entities.UserEntity;

import java.util.List;
import java.util.Optional;


public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

    List<UserEntity> findAll();
    List<UserEntity> findAllByType(String type);
    Optional<UserEntity> findById(String id);

    @Query(value = """
            SELECT TOP 1 u.number
            FROM [user].dbo.users u
            WHERE u.[type] = :type
            ORDER BY CAST(SUBSTRING(u.number, 1, 4) AS INT) DESC, CAST(SUBSTRING(u.number, 6, 2) AS INT) DESC
    """, nativeQuery = true)
    String findByNumber(String type);
}
