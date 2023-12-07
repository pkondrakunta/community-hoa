package com.projects.communityhoa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.projects.communityhoa.model.SubscriptionPlan;

@Repository
public interface SubscriptionPlanDAO {
	public void save(SubscriptionPlan s);

	public void update(SubscriptionPlan s);

	public void delete(SubscriptionPlan s);

	public SubscriptionPlan getSubscriptionPlanById(String Id);

	public List<SubscriptionPlan> getAllSubscriptionPlans();
}
