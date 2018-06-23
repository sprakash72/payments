package dbservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dbservice.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> 
{
	Account findById(Integer Id);
	List<Account> findByCustId(Integer custId);
	Account findByAccountNum(Long accNum);
	Double findBalanceByAccountNum(Long accNum);
	
	@Modifying(clearAutomatically = true)
    @Query("UPDATE Account a SET a.balance  = :balance WHERE a.accountNum = :accountNum")
    public Integer updateBalance(@Param("balance") Double balance, 
    		@Param("accountNum") Long accountNum);

}