package fundtransfer.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fundtransfer.exception.CustomApiException;
import fundtransfer.entities.Audit;
import fundtransfer.service.AccountService;
import fundtransfer.service.AuditService;
import fundtransfer.models.MessageRequestTransfer;
import fundtransfer.models.MessageResponseSuccess;

@RestController
public class FundTransferController 
{
	@Autowired
	AccountService accService;
	
	@Autowired
	AuditService audService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/core/fundtransfer", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponseSuccess> fundTransfer(
			@RequestBody MessageRequestTransfer message)
	{
		Audit audit = new Audit();
		audit.setMessageInTime(new Date());
		
		if(message == null)
			return null;
		
		if(message.getType().equalsIgnoreCase("balancetransfer") == false){
			throw new CustomApiException("Invalid Message Type", 800, message.getMessageId());
		}
		
		audit.setMessageId(message.getMessageId());
		
		if (audService.findByMessageId(message.getMessageId()).isPresent()) {

			throw new CustomApiException("Duplicate Message ID", 333, message.getMessageId());
		}
		
		System.out.println("Messsage ID: " + message.getMessageId());
		System.out.println("From Account: " + message.getFromAccount());
		
//		ResponseEntity resp = restTemplate.postForEntity("http://localhost:9999/core/fundtransfer", 
//				message, 
//				MessageResponseSuccess.class);
		
		MessageResponseSuccess resp = accService.transferFunds(message);
		HttpHeaders headers = new HttpHeaders();
		headers.set("message-id", message.getMessageId().toString());
		
		audit.setStatus(HttpStatus.OK.value());
		audit.setReasonCode(0);
		audit.setMessageOutTime(new Date());
		audService.insertAuditLog(audit);
		
		return new ResponseEntity<MessageResponseSuccess>(resp, headers, HttpStatus.OK);
		
	}
	
	

}
