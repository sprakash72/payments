package balanceinquiry.exceptions;

public class BalanceInquiryErrorMessage{
	Integer messageId;
	Integer statusCode;
	String reason;
	
	public BalanceInquiryErrorMessage() {
	}

	public BalanceInquiryErrorMessage(Integer messageId, Integer statusCode, String reason) {
		this.messageId = messageId;
		this.statusCode = statusCode;
		this.reason = reason;
	}
	
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
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