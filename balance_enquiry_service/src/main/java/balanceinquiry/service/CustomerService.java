package balanceinquiry.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import balanceinquiry.entities.Customer;
import balanceinquiry.repositories.CustomerRepository;

@Service
public class CustomerService 
{
	@Autowired
	CustomerRepository custRepo;
	
	public Optional<Customer> findByCustId(Integer Id){
		return custRepo.findById(Id);
	}

}
