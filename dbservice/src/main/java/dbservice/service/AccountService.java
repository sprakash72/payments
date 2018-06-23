package dbservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbservice.entities.Account;
import dbservice.exceptions.CustomApiException;
import dbservice.models.MessageRequestTransfer;
import dbservice.models.MessageResponse;
import dbservice.repositories.AccountRepository;

@Service
public class AccountService 
{
	@Autowired
	AccountRepository accRepo;
	
	public List<Account> findByCustId(Integer id){
		return accRepo.findByCustId(id);
	}

	public MessageResponse transferFunds(MessageRequestTransfer message, Integer messageId) 
	{
		Long debitAccNum = message.getFromAccount();
		Long creditAccNum = message.getToAccount();
		Integer bsb = message.getToAccountBsb();
		Double amount = message.getTxnAmount();
		
		if(accRepo.findByAccountNum(debitAccNum) == null){
			throw new CustomApiException("Payer Account doesnt exist", 121, messageId);
		}
		
		Double debitAccBalance = accRepo.findBalanceByAccountNum(debitAccNum);
		System.out.println("Acc Num: " + debitAccNum + " Amount: " + debitAccBalance);
		
			
		if(debitAccBalance < amount){
			throw new CustomApiException("Insufficient funds", 121, messageId);
		}
		debitAccBalance -= amount;
		
		if(accRepo.findByAccountNum(creditAccNum) == null){
			throw new CustomApiException("Payee Account doesnt exist", 121, messageId);
		}
		
		Double creditAccBalance = accRepo.findBalanceByAccountNum(creditAccNum);
		creditAccBalance += amount;
				
		accRepo.updateBalance(debitAccBalance, debitAccNum);
		accRepo.updateBalance(creditAccBalance, creditAccNum);

		return new MessageResponse(messageId, 123, 0);
	}

}
