package balanceinquiry.models;

public class AccountInfo 
{
	Long accountId;
	Double balance;
	String currency;
	
	public AccountInfo() 
	{
	}
	
	public AccountInfo(Long accountId, Double balance, String currency) {
		this.accountId = accountId;
		this.balance = balance;
		this.currency = currency;
		
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
		
}
