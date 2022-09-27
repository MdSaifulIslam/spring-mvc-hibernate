package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//have to inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//get current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//make query
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by last_name", Customer.class);
		
		//get the result 
		List<Customer> customers = 
				theQuery.getResultList();
		
		System.out.println(customers);
		
		//return the fetched result
		return customers;
		
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		Session currentSession = 
				sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		Session currentSession = 
				sessionFactory.getCurrentSession();
		
		return currentSession.get(Customer.class, theId);
	}

	@Override
	public void deleteUser(int theId) {
		Session currentSession = 
				sessionFactory.getCurrentSession();
		
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId ");
		
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
	}

}
