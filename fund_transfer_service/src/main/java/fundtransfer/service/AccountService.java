package fundtransfer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fundtransfer.entities.Account;
import fundtransfer.exception.CustomApiException;
import fundtransfer.models.MessageRequestTransfer;
import fundtransfer.models.MessageResponse;
import fundtransfer.repositories.AccountRepository;

@Service
public class AccountService 
{
	@Autowired
	AccountRepository accRepo;
	
//	public List<Account> findByCustId(Integer id){
//		return accRepo.findByCustId(id);
//	}
	
	public Double findBalanceByAccountNum(Long accNum){
		Account account = accRepo.findBalanceByAccountNum(accNum); 
		return account.getBalance();
	}
	
	@Transactional
	public MessageResponse transferFunds(MessageRequestTransfer message)
	{
		Long debitAccNum = message.getFromAccount();
		Long creditAccNum = message.getToAccount();
		String bsb = message.getToBsb();
		Double amount = message.getAmount();
		
		if(accRepo.findByAccountNum(debitAccNum) == null){
			throw new CustomApiException("Payer Account doesnt exist", 121, message.getMessageId());
		}
		
		Double debitAccBalance = findBalanceByAccountNum(debitAccNum);
		System.out.println("Acc Num: " + debitAccNum + " Amount: " + debitAccBalance);
		
			
		if(debitAccBalance < amount){
			throw new CustomApiException("Insufficient funds", 121, message.getMessageId());
		}
		debitAccBalance -= amount;
		
		if(accRepo.findByAccountNum(creditAccNum) == null){
			throw new CustomApiException("Payee Account doesnt exist", 121, message.getMessageId());
		}
		
		Double creditAccBalance = findBalanceByAccountNum(creditAccNum);
		creditAccBalance += amount;
				
		updateAccountBalance(debitAccBalance, debitAccNum);
		updateAccountBalance(creditAccBalance, creditAccNum);
		
//		Audit audit = new Audit();
//		audit.setMessageId(message.getMessageId());
//		audService.insertAuditLog(audit)
		
		return new MessageResponse(message.getMessageId(), 123, 0);
	}

	public Integer updateAccountBalance(Double balance, Long accNum){
		return accRepo.updateBalance(balance, accNum);
	}
}
