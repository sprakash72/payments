package dbservice.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dbservice.models.FundTransferErrorMessage;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	public RestExceptionHandler() 
	{
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