package fundtransfer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fundtransfer.entities.Account;


public interface AccountRepository extends JpaRepository<Account, Long> {
//	List<Account> findByCustId(Integer id);
	
	Account findByAccountNum(Long accNum);
	Account findBalanceByAccountNum(Long accNum);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Account a SET a.balance  = :balance WHERE a.accountNum = :accountNum")
    public Integer updateBalance(@Param("balance") Double balance, 
    		@Param("accountNum") Long accountNum);

}