package balanceinquiry.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import balanceinquiry.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	List<Account> findByCustId(Integer id);
}
