package report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import injectionDetection.Injection;
import injectionDetection.InjectionDetection;
import injectionDetection.Config;

public class PdfGenerator {
	public static void generateReport(ArrayList<Injection> injectionList) {

		try {
			// step 1
			 Document document = new Document(PageSize.A4.rotate());
	        // step 2
	        PdfWriter.getInstance(document, new FileOutputStream(Config.getRootDirectory() + "report/report.pdf"));
	        // step 3
	        document.open();
	        // step 4 - contains
	        
	        //title
	        Font title1Font = FontFactory.getFont("Times-Roman", 25, Font.BOLD);
	        Paragraph title1 =  new Paragraph("SECURE 360 @ Autodesk!", title1Font);
	        title1.setAlignment(Element.ALIGN_CENTER);
	        document.add(title1);
	        
	        Font title2Font = FontFactory.getFont("Times-Roman", 20, Font.BOLD);
	        Paragraph title2 = new Paragraph("Injection checking result", title2Font);
	        title2.setAlignment(Element.ALIGN_CENTER);
	        title2.setSpacingAfter(20);
	        document.add(title2);
	        
	        // Create table
	        PdfPTable table = new PdfPTable(new float[] { 1 , 9 , 5 , 14 , 3 });
	        table.setWidthPercentage(100f);
	        table.getDefaultCell().setPaddingBottom(3);
	        table.getDefaultCell().setPaddingBottom(7);
	        
	        // Header
	        Font headerFont = FontFactory.getFont("Times-Roman", 15, Font.BOLD);
	        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.getDefaultCell().setColspan(1);
	        table.getDefaultCell().setBackgroundColor(new BaseColor(0XEE, 0x6E, 0x73));
            table.addCell(new Phrase("", headerFont));
            table.addCell(new Phrase("URL", headerFont));
            table.addCell(new Phrase("Header", headerFont));
			table.addCell(new Phrase("Value", headerFont));
            table.addCell(new Phrase("Type", headerFont));
            table.setHeaderRows(1);

            // Table content
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            Font tableFont = FontFactory.getFont("Times-Roman", 10);
	        Font injectionFont = FontFactory.getFont("Times-Roman", 10, BaseColor.RED);
            int n = 1;
            for (Injection i : injectionList) {
            	table.getDefaultCell().setBackgroundColor((n%2 ==0)? BaseColor.WHITE : BaseColor.LIGHT_GRAY);
            	table.addCell(new Phrase(n +"", tableFont));
                table.addCell(new Phrase(i.getURL(), tableFont));
                table.addCell(new Phrase(i.getHeader(), tableFont));
                Paragraph value_p =  new Paragraph();
                String[] value_s = i.getValue();
                value_p.add(new Phrase(value_s[0], tableFont));
                value_p.add(new Phrase(value_s[1], injectionFont));
                value_p.add(new Phrase(value_s[2], tableFont));
                table.addCell(value_p);
                table.addCell(new Phrase(i.getType(), tableFont));
                n++;
            }
            
            document.add(table);
	        
	        
	        // step 5
	        document.close();
		}
		catch (DocumentException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		finally {
			
		}
		//return System.getProperty("user.dir");
	}
}
