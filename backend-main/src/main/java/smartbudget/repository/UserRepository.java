package smartbudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smartbudget.enitity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
