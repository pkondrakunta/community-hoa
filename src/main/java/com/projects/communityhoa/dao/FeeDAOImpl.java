package com.projects.communityhoa.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.hibernate.query.Query;

import com.projects.communityhoa.model.Fee;
import com.projects.communityhoa.util.HibernateUtil;

@Component
public class FeeDAOImpl implements FeeDAO {
	private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

	@Override
	public void save(Fee s) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.persist(s);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void update(Fee s) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.merge(s);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void delete(Fee s) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.remove(s);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public Fee getFeeById(String Id) {
		try (Session session = sessionFactory.openSession()) {
			Fee s = session.get(Fee.class, Id);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Fee> getAllFees() {
		try (Session session = sessionFactory.openSession()) {
			Query q = session.createQuery("FROM Fee");
			List<Fee> feeList = q.list();
			return feeList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Fee> getAllRequestFees() {
		try (Session session = sessionFactory.openSession()) {
			Query q = session.createQuery("FROM Fee WHERE feeType = 'request'");
			List<Fee> feeList = q.list();
			return feeList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
