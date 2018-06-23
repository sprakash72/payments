package balanceinquiry.exceptions;

public class CustomApiException extends RuntimeException
{
	Integer statusCode;
	Integer messageId;
	
	public CustomApiException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CustomApiException(String message, Integer statusCode, Integer messageId) {
		super(message);
		this.statusCode = statusCode;
		this.messageId = messageId;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	
	

}
