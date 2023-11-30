package com.projects.communityhoa.dao;

import org.springframework.stereotype.Repository;

import com.projects.communityhoa.model.Member;

@Repository
public class MemberDAO extends DAO {
	
	public void saveMember(Member m) {
		begin();
		getSession().persist(m);
		commit();
	}
}
