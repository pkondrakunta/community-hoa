package com.projects.communityhoa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.communityhoa.dao.InvoiceDAO;
import com.projects.communityhoa.model.Invoice;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceDAO invoiceDAO;

	@Override
	public void save(Invoice invoice) {
        this.invoiceDAO.save(invoice);
	}

	@Override
	public Invoice getInvoiceById(String Id) {
        return this.invoiceDAO.getInvoiceById(Id);
	}

	@Override
	public List<Invoice> getAllInvoices() {
		return this.invoiceDAO.getAllInvoices();
	}

	@Override
	public List<Invoice> getSearchInvoices(String search_text) {
		return this.invoiceDAO.getSearchInvoices(search_text);
	}

}
