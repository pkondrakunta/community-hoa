package com.projects.communityhoa.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.hibernate.query.Query;

import com.projects.communityhoa.model.Member;
import com.projects.communityhoa.model.SubscriptionPlan;
import com.projects.communityhoa.util.HibernateUtil;

@Component
public class SubscriptionPlanDAOImpl implements SubscriptionPlanDAO {
	private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

	@Override
	public void save(SubscriptionPlan s) {
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
	public void update(SubscriptionPlan s) {
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
	public void delete(SubscriptionPlan s) {
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
	public SubscriptionPlan getSubscriptionPlanById(String Id) {
		try (Session session = sessionFactory.openSession()) {
			SubscriptionPlan s = session.get(SubscriptionPlan.class, Id);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<SubscriptionPlan> getAllSubscriptionPlans() {
		try (Session session = sessionFactory.openSession()) {
			Query q = session.createQuery("FROM SubscriptionPlan");
			List<SubscriptionPlan> subscriptionPlanList = q.list();
			return subscriptionPlanList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
