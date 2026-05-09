package smartbudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smartbudget.enitity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
