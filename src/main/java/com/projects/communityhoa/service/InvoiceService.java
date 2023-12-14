/**
 *
 * @author Pragnya Kondrakunta
 */

package com.projects.communityhoa.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projects.communityhoa.model.Invoice;

@Component
public interface InvoiceService {
	public Invoice save(Invoice invoice);

	// No updates can be made to an invoice. A new transaction has to be created
	// public void update(Invoice invoice);

	// No deletes can be made to an invoice. A new transaction has to be created for a refund.
	// public void delete(Invoice invoice);

	public Invoice getInvoiceById(String Id);

	public List<Invoice> getAllInvoices();

	public List<Invoice> getSearchInvoices(String search_text);
}
