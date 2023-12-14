package com.projects.communityhoa.util;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.http.HttpServletResponse;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;

import com.projects.communityhoa.model.Invoice;

public class InvoicePDFExporter {
	private Invoice invoice;

	public InvoicePDFExporter(Invoice invoice) {
		this.invoice = invoice;
	}

	private void writeChargesHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLACK);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Description", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Charges", font));
		table.addCell(cell);
	}

	private void writeChargesData(PdfPTable table) {

		table.getDefaultCell().setFixedHeight(35f);
		table.getDefaultCell().setBorder(Rectangle.TOP);
//		Rectangle.
//		table.getDefaultCell().setBorderTop(new SolidBorder(Color.BLACK, 1));

		// Water
		table.addCell("Water Charges");
		table.addCell(invoice.getWater().toString());

		// Trash
		table.addCell("Trash Charges");
		table.addCell(invoice.getTrash().toString());

		// Snow Removal
		if (invoice.getSnowRemoval() != 0) {
			table.addCell("Snow Removal Charges");
			table.addCell(invoice.getSnowRemoval().toString());
		}

		// Lawn Mowing
		if (invoice.getLawnMowing() != 0) {
			table.addCell("Lawn Mowing Charges");
			table.addCell(invoice.getLawnMowing().toString());
		}

		// Landscaping
		if (invoice.getLandscaping() != 0) {
			table.addCell("Landscaping Charges");
			table.addCell(invoice.getLandscaping().toString());
		}

		// Total
		Font total_font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		total_font.setSize(14);

		table.addCell(new Phrase("Total Charges", total_font));
		table.addCell(new Phrase(invoice.getTotal().toString(), total_font));
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		LineSeparator ls = new LineSeparator();

		document.open();
		Font title_font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		title_font.setSize(16);

		Font heading_font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		heading_font.setSize(12);

		Font text_font = FontFactory.getFont(FontFactory.HELVETICA);
		text_font.setSize(12);

		Font footer_font = FontFactory.getFont(FontFactory.HELVETICA);
		footer_font.setSize(9);

		document.add(new Paragraph("Community HOA Invoice\n\n", title_font));
		document.add(ls);

		document.add(new Paragraph("\nInvoice ID \n", heading_font));
		document.add(new Paragraph(invoice.getInvoiceID(), text_font));

		LocalDateTime ldt = (LocalDateTime) invoice.getDate();
		DateTimeFormatter formatter_datetime = DateTimeFormatter.ofPattern("MMM dd, YYYY  HH:mm");

		document.add(new Paragraph("\nBilled to Member ID \n", heading_font));
		document.add(new Paragraph(invoice.getMemberID(), text_font));

		document.add(new Paragraph("\nDate issued \n", heading_font));
		document.add(new Paragraph(ldt.format(formatter_datetime), text_font));

		DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("MMM dd, YYYY");

		if (invoice.getNewExpiry() != null) {
			document.add(new Paragraph("\nNew expiry date \n", heading_font));
			document.add(new Paragraph(invoice.getNewExpiry().format(formatter_date), text_font));
		}

		document.add(new Paragraph("\nCharges breakdown: ", heading_font));
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);

		writeChargesHeader(table);
		writeChargesData(table);

		document.add(table);

		document.add(new Paragraph("\n\n"));
		document.add(ls);
		document.add(new Phrase("This invoice is electronically generated.", footer_font));

		document.close();

	}
}