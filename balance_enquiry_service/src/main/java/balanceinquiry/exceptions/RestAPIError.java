package balanceinquiry.exceptions;

import org.springframework.http.HttpStatus;

public class RestAPIError {
	// Integer messageId;
	// Integer statusCode;
	// String failureReason;

	private HttpStatus status;
	private String message;

	RestAPIError(HttpStatus status) {
		this.status = status;
	}

	RestAPIError(HttpStatus status, Throwable ex) {
		this.status = status;
		this.message = "Unexpected error";
		// this.debugMessage = ex.getLocalizedMessage();
	}

	RestAPIError(HttpStatus status, String message, Throwable ex) {
		this.status = status;
		this.message = message;
		// this.debugMessage = ex.getLocalizedMessage();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
