package com.projects.communityhoa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projects.communityhoa.model.Member;

@Repository
public interface MemberDAO {
	public void save(Member m);

	public void update(Member m);

	public void delete(Member m);

	public Member getMemberById(String Id);

	public List<Member> getAllMembers();
}
