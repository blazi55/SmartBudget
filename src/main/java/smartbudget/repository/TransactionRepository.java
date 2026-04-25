package smartbudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smartbudget.enitity.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByUserId(Long userId);
	List<Transaction> findByCategoryId(Long categoryId);
	List<Transaction> findByUserIdAndCategoryId(Long userId, Long categoryId);
}
