package com.projects.communityhoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.communityhoa.dao.FeeDAO;
import com.projects.communityhoa.model.Fee;

@Service
public class FeeServiceImpl implements FeeService {
    @Autowired
    private FeeDAO feeDAO;
    
	@Override
	public void save(Fee fee) {
        this.feeDAO.save(fee);
	}

	@Override
	public void update(Fee fee) {
        this.feeDAO.update(fee);
	}

	@Override
	public void delete(Fee fee) {
        this.feeDAO.delete(fee);
	}

	@Override
	public Fee getFeeById(String Id) {
        return this.feeDAO.getFeeById(Id);
	}

	@Override
	public List<Fee> getAllFees() {
        return this.feeDAO.getAllFees();
	}

	@Override
	public List<Fee> getAllRequestFees() {
		// TODO Auto-generated method stub
		return this.feeDAO.getAllRequestFees();
	}

}
