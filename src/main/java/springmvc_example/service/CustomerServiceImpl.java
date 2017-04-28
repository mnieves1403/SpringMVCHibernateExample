package springmvc_example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springmvc_example.dao.CustomerDao;
import springmvc_example.model.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	CustomerDao customerDao;
	
	@Autowired
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public List<Customer> listAllCustomers() {
		return customerDao.listAllCustomers();
	}

	public void saveOrUpdate(Customer customer) {
		customerDao.saveOrUpdate(customer);
	}

	public Customer findCustomerById(int id) {
		return customerDao.findCustomerById(id);
	}

	public void delete(int id) {
		customerDao.delete(id);
	}

}
