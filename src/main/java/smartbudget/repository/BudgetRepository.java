package smartbudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smartbudget.enitity.Budget;
import smartbudget.enums.BudgetPeriod;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

	List<Budget> findByUserId(Long userId);
	boolean existsByUserIdAndCategoryIdAndPeriod(Long userId, Long categoryId, BudgetPeriod period);

}
