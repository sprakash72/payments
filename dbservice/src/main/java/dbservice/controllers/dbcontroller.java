package dbservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dbservice.models.Account;
import dbservice.models.MessageRequestTransfer;
import dbservice.models.MessageResponse;
import dbservice.service.AccountService;
import dbservice.models.FundTransferErrorMessage;

@CrossOrigin
@RestController
public class dbcontroller 
{
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/getcorebalance", method = RequestMethod.GET)
	public List<Account> getBalance(@PathVariable Integer custId) {
		return null;
	}
	
	@RequestMapping(value = "/core/fundtransfer", method = RequestMethod.POST)
	public ResponseEntity<MessageResponse> fundTransfer(@RequestHeader("message-id") Integer messageId,
			@RequestBody MessageRequestTransfer message) {
		
		System.out.println("DB Service invoked");
		
		MessageResponse resp = accountService.transferFunds(message, messageId);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("message-id", messageId.toString());

		return new ResponseEntity<MessageResponse>(resp, headers, HttpStatus.OK);
		
	}

}
