package com.projects.communityhoa.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.communityhoa.dao.MemberDAO;
import com.projects.communityhoa.model.Member;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDAO memberDAO;
    
	@Override
	public Member save(Member member) {
		
		String plan = member.getSubscriptionPlan();
				
		member.setMemberID(generateNewMemberId(member));
		member.setLastPaid(LocalDateTime.now());
		
		if (plan == "Annually"){
			member.setSubscriptionExpiry(LocalDate.now().plusYears(1));
		}
		else {
			member.setSubscriptionExpiry(LocalDate.now().plusMonths(1));
		}

        this.memberDAO.save(member);
		return member;
	}

	@Override
	public void update(Member member) {
        this.memberDAO.update(member);
	}

	@Override
	public void delete(Member member) {
        this.memberDAO.delete(member);
	}

	@Override
	public Member getMemberById(String Id) {
        return this.memberDAO.getMemberById(Id);
	}

	@Override
	public List<Member> getAllMembers() {
        return this.memberDAO.getAllMembers();
	}

	@Override
	public List<Member> getSearchMembers(String search_text) {
        return this.memberDAO.getSearchMembers(search_text);
	}
	
	private static String generateNewMemberId(Member member) {
	    String creationTimeMillis_4ID = ""+System.currentTimeMillis()/1000;
	    String initials = member.getFirstName().substring(0,1).toUpperCase().concat(member.getLastName().substring(0,1).toUpperCase());
	    
		return creationTimeMillis_4ID.concat(initials);
	}

}
