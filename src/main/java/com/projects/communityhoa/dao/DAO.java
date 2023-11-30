package com.projects.communityhoa.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DAO {

	Configuration cfg = new Configuration();
	
	SessionFactory sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
	
	Session session =  sf.openSession();
	
	Transaction tx = session.beginTransaction();
	
	public Session getSession() {
		return sf.openSession();
	}
	
	public void begin() {
		session.beginTransaction();
	}
	
	public void commit() {
		session.getTransaction().commit();
	}
}
