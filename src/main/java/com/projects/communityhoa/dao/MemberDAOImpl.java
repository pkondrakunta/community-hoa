package com.projects.communityhoa.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.hibernate.query.Query;

import com.projects.communityhoa.model.Member;
import com.projects.communityhoa.util.HibernateUtil;

@Component
public class MemberDAOImpl implements MemberDAO {
	private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

	@Override
	public void save(Member m) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.persist(m);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Member m) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.merge(m);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(Member m) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.getTransaction();
			transaction.begin();
			session.remove(m);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Member getMemberById(String Id) {
		try (Session session = sessionFactory.openSession()) {
			Member m = session.get(Member.class, Id);
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Member> getAllMembers() {
		try (Session session = sessionFactory.openSession()) {
			Query q = session.createQuery("FROM Member");
			List<Member> memberList = q.list();
			return memberList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
