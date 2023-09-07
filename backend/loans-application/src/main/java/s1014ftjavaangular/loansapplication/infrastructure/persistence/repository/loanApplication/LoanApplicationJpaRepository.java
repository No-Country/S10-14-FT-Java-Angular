package s1014ftjavaangular.loansapplication.infrastructure.persistence.repository.loanApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import s1014ftjavaangular.loansapplication.domain.model.dto.LoanApplicationForCustomer;
import s1014ftjavaangular.loansapplication.infrastructure.persistence.entities.LoanApplicationEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LoanApplicationJpaRepository extends JpaRepository<LoanApplicationEntity, String> {

    @Query(value = """
            SELECT 
            la.loan_application_id AS loanApplicationId, 
            la.loan_application_number AS loanApplicationNumber,
            la.request_amount AS requestedAmount,
            la.create_at AS createAt,
            la.status
            FROM loans_application la
            WHERE la.customer_id = :customerId
            ORDER BY la.created_at DESC
            """, nativeQuery = true)
    Optional<List<LoanApplicationForCustomer>> findByCustomerId(String customerId);
//
    @Query(value = "SELECT MAX(l.loan_application_number) FROM loans_application AS l", nativeQuery = true)
    Optional<String> findLastLoanApplicationNumber();

    @Query(value = """ 
            SELECT COUNT(*) 
            FROM loans_application AS la 
            INNER JOIN general_data AS gd ON  la.loan_application_id = gd.loan_application_id 
            WHERE la.status IN (0, 1)  AND gd.identification = :identification
            """, nativeQuery = true)
    Integer countIncompleteOrAuditingStatusLoanApplication(String identification);
}
