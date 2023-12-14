package com.projects.communityhoa.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.communityhoa.dao.InvoiceDAO;
import com.projects.communityhoa.model.Invoice;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceDAO invoiceDAO;

	@Override
	public Invoice save(Invoice invoice) {
		invoice.setInvoiceID(generateNewInvoiceId(invoice));
        this.invoiceDAO.save(invoice);
        return invoice;
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

	
	private static String generateNewInvoiceId(Invoice invoice) {
		String memberID = invoice.getMemberID();
	    String memIDLastChars = memberID.substring(8,12).toUpperCase();
	    Random random = new Random();
	    String rand = String.format("%03d", random.nextInt(10000));
	    
		return memIDLastChars.concat(rand);
	}
}
