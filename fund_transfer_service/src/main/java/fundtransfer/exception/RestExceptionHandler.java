package fundtransfer.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import fundtransfer.service.AuditService;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	AuditService audService;

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

}

class FundTransferErrorMessage {
	// Integer messageId;
	Integer statusCode;
	String reason;

	// public Integer getMessageId() {
	// return messageId;
	// }
	// public void setMessageId(Integer messageId) {
	// this.messageId = messageId;
	// }
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