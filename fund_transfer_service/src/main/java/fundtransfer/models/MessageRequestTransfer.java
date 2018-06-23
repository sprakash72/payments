package fundtransfer.models;

public class MessageRequestTransfer 
{
	String type; 
	Integer messageId;
	Long fromAccount;
	Long toAccount;
	String toBsb;
	Double amount;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
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
	public String getToBsb() {
		return toBsb;
	}
	public void setToBsb(String toBsb) {
		this.toBsb = toBsb;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	

}
