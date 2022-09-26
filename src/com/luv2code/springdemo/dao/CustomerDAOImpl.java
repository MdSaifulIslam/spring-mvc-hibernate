package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//have to inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		//get current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//make query
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer", Customer.class);
		
		//get the result 
		List<Customer> customers = 
				theQuery.getResultList();
		
		System.out.println(customers);
		
		//return the fetched result
		return customers;
		
	}

}
