package balanceinquiry.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{

	BalanceInquiryErrorMessage exceptionMessage;

	public RestExceptionHandler() {
	}

	public RestExceptionHandler(BalanceInquiryErrorMessage exceptionMessage){
		this.exceptionMessage = exceptionMessage;
	}
	
//	@ExceptionHandler(RuntimeException.class)
//	public final ResponseEntity<BalanceInquiryErrorMessage> exceptionOccurred(Exception ex){
//		BalanceInquiryErrorMessage exceptionResponse = new  BalanceInquiryErrorMessage();
//		exceptionResponse.setReason(ex.getMessage());
//		exceptionResponse.setStatusCode(101);
//		exceptionResponse.setMessageId(1001);
//		
//		return new ResponseEntity<BalanceInquiryErrorMessage>(exceptionResponse, 
//				HttpStatus.BAD_REQUEST);
//		
//	}
	
	@ExceptionHandler(CustomApiException.class)
	public final ResponseEntity<BalanceInquiryErrorMessage> exceptionOccurred(CustomApiException ex){
		BalanceInquiryErrorMessage exceptionResponse = new  BalanceInquiryErrorMessage();
		exceptionResponse.setReason(ex.getMessage());
		exceptionResponse.setStatusCode(ex.getStatusCode());
		exceptionResponse.setMessageId(ex.getMessageId());
		
		return new ResponseEntity<BalanceInquiryErrorMessage>(exceptionResponse, 
				HttpStatus.BAD_REQUEST);
		
	}


}
