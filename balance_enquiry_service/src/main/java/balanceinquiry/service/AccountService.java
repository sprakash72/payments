package balanceinquiry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import balanceinquiry.entities.Account;
import balanceinquiry.repositories.AccountRepository;

@Service
public class AccountService 
{
	@Autowired
	AccountRepository accRepo;
	
	public List<Account> findByCustId(Integer id){
		return accRepo.findByCustId(id);
	}

}
