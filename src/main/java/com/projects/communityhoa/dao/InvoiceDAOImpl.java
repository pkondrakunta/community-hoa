package com.projects.communityhoa.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.hibernate.query.Query;

import com.projects.communityhoa.model.Invoice;
import com.projects.communityhoa.util.HibernateUtil;

@Component
public class InvoiceDAOImpl implements InvoiceDAO {
	private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

	@Override
	public void save(Invoice i) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.persist(i);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Invoice getInvoiceById(String Id) {
		try (Session session = sessionFactory.openSession()) {
			Invoice i = session.get(Invoice.class, Id);
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Invoice> getAllInvoices() {
		try (Session session = sessionFactory.openSession()) {
			Query q = session.createQuery("FROM Invoice");
			List<Invoice> invoiceList = q.list();
			return invoiceList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
