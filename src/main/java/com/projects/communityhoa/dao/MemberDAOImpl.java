package com.projects.communityhoa.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.projects.communityhoa.model.Member;
import com.projects.communityhoa.util.HibernateUtil;

@Repository
public class MemberDAOImpl implements MemberDAO {
	private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

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

	public Member getMemberById(String Id) {
		try (Session session = sessionFactory.openSession()) {
			Member m = session.get(Member.class, Id);
			return m;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Member> getAllMembers() {

		try (Session session = sessionFactory.openSession()) {
			List<Member> memberList = session.createQuery("FROM Member").list();
			return memberList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
