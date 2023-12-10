package com.projects.communityhoa.util;
 
import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import jakarta.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.projects.communityhoa.model.Member;
 
 
public class MembersPDFExporter {
    private List<Member> memberList;
     
    public MembersPDFExporter(List<Member> memberList) {
        this.memberList = memberList;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Member ID", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Plan", font));
        table.addCell(cell);       
    }
     
    private void writeTableData(PdfPTable table) {
        for (Member member : memberList) {
            table.addCell(String.valueOf(member.getMemberID()));
            table.addCell(member.getEmail());
            table.addCell(member.getFirstName());
            table.addCell(member.getLastName());
            table.addCell(member.getSubscriptionPlan());
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
         
        Paragraph p = new Paragraph("List of Members", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3.0f, 4.0f, 2.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}