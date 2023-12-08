package com.projects.communityhoa.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projects.communityhoa.model.Fee;

@Component
public interface FeeDAO {
	public void save(Fee s);

	public void update(Fee s);

	public void delete(Fee s);

	public Fee getFeeById(String Id);

	public List<Fee> getAllFees();
}
