package com.projects.communityhoa.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.hibernate.query.Query;

import com.projects.communityhoa.model.Member;
import com.projects.communityhoa.model.User;
import com.projects.communityhoa.util.HibernateUtil;

@Component
public class UserDAOImpl implements UserDAO {
	private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

	@Override
	public void save(User u) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.persist(u);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void update(User u) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.merge(u);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void delete(User u) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.remove(u);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public User getUserByUsername(String username) {
		try (Session session = sessionFactory.openSession()) {
			User u = session.get(User.class, username);
			return u;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		try (Session session = sessionFactory.openSession()) {
			Query q = session.createQuery("FROM User");
			List<User> userList = q.list();
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
