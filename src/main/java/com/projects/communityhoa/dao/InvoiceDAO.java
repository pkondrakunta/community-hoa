package com.projects.communityhoa.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projects.communityhoa.model.Invoice;

@Component
public interface InvoiceDAO {
	public void save(Invoice i);

	// No updates can be made to an invoice. A new transaction has to be created
	// public void update(Invoice i);

	// No deletes can be made to an invoice. A new transaction has to be created for a refund.
	// public void delete(Invoice i);

	public Invoice getInvoiceById(String Id);

	public List<Invoice> getAllInvoices();
}
