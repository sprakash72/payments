package dbservice.models;

public class MessageRequestTransfer 
{
	String srvcType; 
//	Integer messageId;
	Long fromAccount;
	Long toAccount;
	Integer toAccountBsb;
	Double txnAmount;
	
	public String getSrvcType() {
		return srvcType;
	}
	public void setSrvcType(String srvcType) {
		this.srvcType = srvcType;
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
	public Integer getToAccountBsb() {
		return toAccountBsb;
	}
	public void setToAccountBsb(Integer toAccountBsb) {
		this.toAccountBsb = toAccountBsb;
	}
	public Double getTxnAmount() {
		return txnAmount;
	}
	public void setTxnAmount(Double txnAmount) {
		this.txnAmount = txnAmount;
	}
	
	
		

}
