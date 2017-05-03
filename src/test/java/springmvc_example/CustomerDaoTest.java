package springmvc_example;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.exception.DataException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import springmvc_example.config.HibernateConfig;
import springmvc_example.dao.CustomerDao;
import springmvc_example.model.Customer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateConfig.class})
@WebAppConfiguration
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class CustomerDaoTest {
	
	final static Logger logger = Logger.getLogger(CustomerDaoTest.class);

	@Autowired
	CustomerDao customerDao;
		
//	@Test
	@Ignore
	public void listAllCustomers() {
		List<Customer> lst = customerDao.listAllCustomers();
		
		assertTrue(lst.size() > 0);
	}
	
	/**
	 * variable indicador (1 - elimino correctamente / 2 - no se encontro datos para eliminar)
	 */
//	@Test
	@Ignore
	public void delete() {		
		Customer c = customerDao.findCustomerByName("%MARGARITA%");
		logger.info("Lastname: "+c.getLastname());
		
		if(c!=null&&c.getId()>0){
			logger.info("Lastname: "+c.getId());
			customerDao.delete(c.getId());
		}
		c = new Customer();
		c = customerDao.findCustomerByName("%MARGARITA%");
		logger.info("Lastname: "+(c==null?"No encontro data":c.getLastname()));
		assertNull(c);
	}
	
//	@Test(expected = DataException.class)
	@Ignore
	public void saveDataException() {
		Customer customer = new Customer();
		
		customer.setFirstname("MIGUEL ANGEL");
		customer.setLastname("NIEVES YZAGUIRRE");
		customer.setGender("MaleFemale");
		customer.setAddress("JR. BELLO HORIZONTE 2927");
		
		customerDao.saveOrUpdate(customer);
	}
	
//	@Test
	@Before
//	@Ignore
	public void save() {
		logger.info("-------------------INI INSERT-------------------");
		Customer customer = new Customer();
		
		customer.setFirstname("MIGUEL ANGEL");
		customer.setLastname("NIEVES YZAGUIRRE");
		customer.setGender("Male");
		customer.setAddress("JR. BELLO HORIZONTE 2927");
		
		customerDao.saveOrUpdate(customer);
		
		Customer c = customerDao.findCustomerByName("%MIGUEL ANGEL%");
		logger.info("Id: "+(c==null?"No encontro data":c.getId()));
		logger.info("-------------------FIN INSERT-------------------");
		assertNotNull(c);
	}
	
//	@Test
	@After
//	@Ignore
	public void update() {
		logger.info("-------------------INI UPDATE-------------------");
		Customer c = customerDao.findCustomerByName("%MIGUEL ANGEL%");
		logger.info("Id: "+(c==null?"No encontro data":c.getId()));
		
		c.setFirstname("ANTONIO");
		c.setLastname("NIEVES RODRIGUEZ");
		
		customerDao.saveOrUpdate(c);
		
		c = new Customer();
		c = customerDao.findCustomerByName("%MIGUEL ANGEL%");
		logger.info("Id: "+(c==null?"No encontro data":c.getId()));
		
		c = new Customer();
		c = customerDao.findCustomerByName("%ANTONIO%");
		logger.info("Firstname: "+(c==null?"No encontro data":c.getFirstname()));
		logger.info("Lastname: "+(c==null?"No encontro data":c.getLastname()));
		logger.info("-------------------FIN UPDATE-------------------");
		
		assertNotNull(c);
	}
	
	@Test
//	@Ignore
	public void findCustomerByName(){
		Customer customer = new Customer();
		
		customer = customerDao.findCustomerByName("%MARGARITA%");
		logger.info("Lastname: "+customer.getLastname());
		
		assertNotNull(customer);
	}
	
}
