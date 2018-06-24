package fundtransfer.exception;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fundtransfer.service.AuditService;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	AuditService audService;
	
	@Autowired
	private ObjectMapper mapper;

	public RestExceptionHandler() {
	}

	@ExceptionHandler(CustomApiException.class)
	public final ResponseEntity<FundTransferErrorMessage> exceptionOccurred(CustomApiException ex) {
		FundTransferErrorMessage exceptionResponse = new FundTransferErrorMessage();
		exceptionResponse.setReason(ex.getMessage());
		exceptionResponse.setStatusCode(ex.getStatusCode());

		HttpHeaders headers = new HttpHeaders();
		headers.add("message-id", ex.getMessageId().toString());

		return new ResponseEntity<FundTransferErrorMessage>(exceptionResponse, headers, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	public final ResponseEntity<FundTransferErrorMessage> handleClientError(HttpClientErrorException ex, Model model) throws JsonParseException, JsonMappingException, IOException {

		System.out.println(ex.getResponseBodyAsString());
		//get response body for exception
		FundTransferErrorMessage resp = mapper.readValue(ex.getResponseBodyAsByteArray(), FundTransferErrorMessage.class);

		//get exception response header, but this header cant be used in building HTTP
		//response since it is specific to HttpClientErrorException
		HttpHeaders headers  = ex.getResponseHeaders();
		List<String> message_id = headers.get("message-id");
		
		//Set "message-id" in header
		HttpHeaders newHeaders = new HttpHeaders();
		newHeaders.add("message-id", message_id.get(0).toString());
		
		//Build response here
		return new ResponseEntity<FundTransferErrorMessage>(resp, newHeaders, ex.getStatusCode());
	}

}

class FundTransferErrorMessage {
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