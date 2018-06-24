package fundtransfer.controllers;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fundtransfer.entities.Audit;
import fundtransfer.exception.CustomApiException;
import fundtransfer.models.MessageRequestTransfer;
import fundtransfer.models.MessageResponse;
import fundtransfer.service.AccountService;
import fundtransfer.service.AuditService;

@CrossOrigin
@RestController
public class FundTransferController 
{
	@Autowired
	AccountService accService;
	
	@Autowired
	AuditService audService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper mapper;
	
	@RequestMapping(value = "/core/fundtransfer", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse> fundTransfer(
			@RequestBody MessageRequestTransfer message)
	{
		Audit audit = new Audit();
		audit.setMessageInTime(new Date());
		
		System.out.println("Calling Fund Transfer");
		
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
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("message-id", message.getMessageId().toString());
		
		DbServiceMessage dbMessage = new DbServiceMessage();
		dbMessage.setSrvcType(message.getType());
		dbMessage.setFromAccount(message.getFromAccount());
		dbMessage.setToAccount(message.getToAccount());
		dbMessage.setToAccountBsb(Integer.parseInt(message.getToBsb()));
		dbMessage.setTxnAmount(message.getAmount());
				
		HttpEntity<DbServiceMessage> httpEntity = new HttpEntity<DbServiceMessage>(dbMessage, headers);
		
		ResponseEntity<MessageResponse> resp = restTemplate.postForEntity(
				"http://localhost:8085/core/fundtransfer", 
				httpEntity, 
				MessageResponse.class);
		
//		MessageResponse resp = accService.transferFunds(message);
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("message-id", message.getMessageId().toString());
//		
		audit.setStatus(HttpStatus.OK.value());
		audit.setReasonCode(0);
		audit.setMessageOutTime(new Date());
		audService.insertAuditLog(audit);
		
//		return new ResponseEntity<MessageResponse>(resp, headers, HttpStatus.OK);
		return resp;
		
	}
	
//	@ExceptionHandler(HttpClientErrorException.class)
//	public ResponseEntity<ExceptionResponse> handleClientError(HttpClientErrorException ex, Model model) throws JsonParseException, JsonMappingException, IOException {
//
//		System.out.println(ex.getResponseBodyAsString());
//		ExceptionResponse resp = mapper.readValue(ex.getResponseBodyAsByteArray(), ExceptionResponse.class);
//		HttpHeaders headers = ex.getResponseHeaders();
//		return new ResponseEntity<ExceptionResponse>(resp, headers, ex.getStatusCode());
//	}
	

}

class DbServiceMessage
{
	String srvcType; 
	Long fromAccount;
	Long toAccount;
	Integer toAccountBsb;
	Double txnAmount;
	
	public String getSrvcType() {
		return srvcType;
	}
	public void setSrvcType(String srvcType) {
		this.srvcType = srvcType;
	}
	public Long getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Long getToAccount() {
		return toAccount;
	}
	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
	}
	public Integer getToAccountBsb() {
		return toAccountBsb;
	}
	public void setToAccountBsb(Integer toAccountBsb) {
		this.toAccountBsb = toAccountBsb;
	}
	public Double getTxnAmount() {
		return txnAmount;
	}
	public void setTxnAmount(Double txnAmount) {
		this.txnAmount = txnAmount;
	}
	
}


class ExceptionResponse
{
	Integer statusCode;
	String reason;
	
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
