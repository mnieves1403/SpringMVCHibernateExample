package springmvc_example.dao;

import java.util.List;

import springmvc_example.model.Customer;

public interface CustomerDao {

	public List<Customer> listAllCustomers();
	
	public void saveOrUpdate(Customer customer);
	
	public Customer findCustomerById(int id);
	
	public void delete(int id);
	
	public Customer findCustomerByName(String Name);
	
}
