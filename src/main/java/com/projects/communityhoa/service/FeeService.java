/**
 *
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.service;

import java.util.List;
import org.springframework.stereotype.Component;
import com.projects.communityhoa.model.Fee;

@Component
public interface FeeService {

	public void save(Fee fee);

	public void update(Fee fee);

	public void delete(Fee fee);

	public Fee getFeeById(String Id);

	public List<Fee> getAllFees();

}
