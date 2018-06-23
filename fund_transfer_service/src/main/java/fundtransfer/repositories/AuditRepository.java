package fundtransfer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fundtransfer.entities.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer> 
{
	//Optinal not required but just added
	Optional<Audit> findByMessageId(Integer Id);

}
