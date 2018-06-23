package balanceinquiry.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import balanceinquiry.entities.Account;
import balanceinquiry.entities.Audit;
import balanceinquiry.exceptions.CustomApiException;
import balanceinquiry.models.AccountInfo;
import balanceinquiry.models.MessageRequest;
import balanceinquiry.models.MessageResponse;
import balanceinquiry.service.AccountService;
import balanceinquiry.service.AuditService;
import balanceinquiry.service.CustomerService;

@RestController
public class BalanceController {

	@Autowired
	CustomerService custService;
	@Autowired
	AccountService accService;
	@Autowired
	AuditService audService;
	
	@RequestMapping(value = "/api/balance", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageResponse> getBalance(@RequestBody MessageRequest message)
	{
		System.out.println("Inside Message");
		Audit audit = new Audit();
		audit.setMessageInTime(new Date());
		
		// null check
		if (message == null) {
			throw new CustomApiException("Invalid message", 999, null);
		}
		
		audit.setMessageId(message.getMessageid());
		
	
		if(audService.findByMessageId(message.getMessageid()).isPresent()){
			//Not logging duplicate message id since missage id in audit table is 
			//"UNIQUE" field
			throw new CustomApiException("Duplicate Message ID", 333, message.getMessageid());
		}
		
		// Integer messageId = message.getMessageID();
		Integer custId = message.getCustid();
		List<Account> accounts = null;
		if(custService.findByCustId(custId).isPresent()){
			accounts = accService.findByCustId(custId);
			
		}
		else
		{
			audit.setStatus(HttpStatus.BAD_REQUEST.value());
			audit.setReasonCode(899);
			audit.setMessageOutTime(new Date());
			
			audService.insertAuditLog(audit);
				
			throw new CustomApiException("Invalid customer ID", 899, message.getMessageid());

		}

		MessageResponse resp = new MessageResponse();

		resp.setCustID(message.getCustid());
		resp.setMessageId(message.getMessageid());
		
		List<AccountInfo> custAccounts = new ArrayList<AccountInfo>();
		
		for(int i=0; i< accounts.size(); i++){
			Account account = accounts.get(i);
			custAccounts.add(new AccountInfo(account.getAccountNum(), 
					account.getBalance(), account.getCurrency()));
		}
			
		audit.setMessageOutTime(new Date());
		audService.insertAuditLog(audit);
		
		resp.setAccounts(custAccounts);

		/// Invoke DB Service to get balance and update log
//		String url = "http://localhost:8082/getBalancefromDbService/" + custId;
//		try {
//			sendRequest(url);
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		return new ResponseEntity<MessageResponse>(resp, HttpStatus.OK);

	}

	@RequestMapping(value = "/api/balance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getBalance1() {
		return "Hello";
	}

	StringBuffer sendRequest(String url) throws IOException {
		URL obj;
		HttpsURLConnection con;
		obj = new URL(url);
		con = (HttpsURLConnection) obj.openConnection();
		con.setRequestMethod("POST");

		StringBuffer response = new StringBuffer();
		return response;
	}

}
