package com.projects.communityhoa.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projects.communityhoa.model.Member;

@Component
public interface MemberDAO {
	public void save(Member m);

	public void update(Member m);

	public void delete(Member m);

	public Member getMemberById(String Id);

	public List<Member> getAllMembers();
}
