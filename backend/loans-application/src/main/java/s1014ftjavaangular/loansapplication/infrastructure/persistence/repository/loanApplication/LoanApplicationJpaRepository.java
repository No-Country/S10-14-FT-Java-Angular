package s1014ftjavaangular.loansapplication.infrastructure.persistence.repository.loanApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import s1014ftjavaangular.loansapplication.domain.model.dto.LoanApplicationForCustomer;
import s1014ftjavaangular.loansapplication.infrastructure.persistence.entities.LoanApplicationEntity;

import java.util.List;
import java.util.Optional;

public interface LoanApplicationJpaRepository extends JpaRepository<LoanApplicationEntity, String> {

    @Query(value = """
            SELECT 
            la.loan_application_id AS loanApplicationId, 
            la.loan_application_number AS loanApplicationNumber,
            la.request_amount AS requestedAmount,
            la.create_at AS createAt,
            la.status
            FROM loans_application AS la
            WHERE la.customer_id = :customerId
            ORDER BY la.create_at DESC
            """, nativeQuery = true)
    Optional<List<LoanApplicationForCustomer>> findByCustomerId(String customerId);

    //
    @Query(value = """
            SELECT u.loan_application_number
            FROM public.loans_application u
            ORDER BY CAST(SUBSTRING(u.loan_application_number, 1, 4) AS INT) DESC, CAST(SUBSTRING(u.loan_application_number, 6, 2) AS INT) DESC
            LIMIT 1
            """, nativeQuery = true)
    Optional<String> findLastLoanApplicationNumber();

    @Query(value = """ 
            SELECT COUNT(*) 
            FROM loans_application AS la 
            INNER JOIN general_data AS gd ON  la.loan_application_id = gd.loan_application_id
            WHERE (la.status = 1 OR la.status = 0) AND gd.identification = :identification
            """, nativeQuery = true)
    Integer countIncompleteOrAuditingStatusLoanApplication(String identification);
}
