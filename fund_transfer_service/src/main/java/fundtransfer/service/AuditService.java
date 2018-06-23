package fundtransfer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fundtransfer.entities.Audit;
import fundtransfer.repositories.AuditRepository;

@Service
public class AuditService {
	
	@Autowired
	AuditRepository auditRepo;
	
	public Audit insertAuditLog(Audit audit){
		return auditRepo.saveAndFlush(audit);
	}
	
	public Optional<Audit> findByMessageId(Integer Id){
		return auditRepo.findByMessageId(Id);
	}
}
