/**
 *
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.service;


import java.util.List;

import org.springframework.stereotype.Component;

import com.projects.communityhoa.model.Member;

@Component
public interface MemberService {
	
    public Member save(Member member);
    public void update(Member member);
    public void delete(Member member);
    public Member getMemberById(String Id);
    public List<Member> getAllMembers();
    public List<Member> getSearchMembers(String search_text);
}
