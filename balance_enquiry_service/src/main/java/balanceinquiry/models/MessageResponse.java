package balanceinquiry.models;

import java.util.ArrayList;
import java.util.List;

public class MessageResponse 
{
	Integer messageId;
	Integer custID;
	List <AccountInfo> accounts = new ArrayList<>();
	
	public Integer getCustID() {
		return custID;
	}
	public void setCustID(Integer custID) {
		this.custID = custID;
	}
	public List<AccountInfo> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<AccountInfo> accounts) {
		this.accounts = accounts;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	

}
