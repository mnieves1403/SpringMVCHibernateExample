package springmvc_example.service;

import java.util.List;

import springmvc_example.model.Customer;

public interface CustomerService {
	public List<Customer> listAllCustomers();
	
	public void saveOrUpdate(Customer customer);
	
	public Customer findCustomerById(int id);
	
	public void delete(int id);
}
