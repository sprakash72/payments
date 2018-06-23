package fundtransfer.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction_audit")
public class Audit 
{
	@Id @GeneratedValue
	Integer id;
	@Column(name="IMESSAGE")
	Integer messageId;
	@Column(name="MESSGINTIME")
	Date messageInTime;
	Integer status;
	@Column(name="reason_code")
	Integer reasonCode;
	@Column(name="MESSGOUTTIME")
	Date messageOutTime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Date getMessageInTime() {
		return messageInTime;
	}
	public void setMessageInTime(Date messageInTime) {
		this.messageInTime = messageInTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(Integer reasonCode) {
		this.reasonCode = reasonCode;
	}
	public Date getMessageOutTime() {
		return messageOutTime;
	}
	public void setMessageOutTime(Date messageOutTime) {
		this.messageOutTime = messageOutTime;
	}
	

}
