package fundtransfer.models;

public class MessageResponseSuccess 
{
	Integer messageId;
	Integer txnId;
	Integer statusCode;
//	String failureReason;
	
	
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Integer getTxnId() {
		return txnId;
	}
	public void setTxnId(Integer txnId) {
		this.txnId = txnId;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public MessageResponseSuccess(Integer messageId, Integer txnId, Integer statusCode) {
		this.messageId = messageId;
		this.txnId = txnId;
		this.statusCode = statusCode;
	}
	public MessageResponseSuccess() {
	}
	
}
