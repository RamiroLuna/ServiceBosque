package org.probosque.model;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.FileNotFoundException;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import org.probosque.dto.ReportPredioDTO;
import org.probosque.model.json.ReportPrediosRowJson;
import org.probosque.service.Configuration;

public class PDFReport {

    public PDFReport(ArrayList<ReportPrediosRowJson> listReport, OutputStream output) throws FileNotFoundException, DocumentException, BadElementException, IOException {
        Document document = new Document();

        
        PdfWriter.getInstance(document, output);
        
        document.open();

        String imageUrl = Configuration.IMG_PDF; 
        Image image = Image.getInstance(new URL(imageUrl));
        image.setAbsolutePosition(0, document.getPageSize().getHeight() - 72);
        document.add(image);

        Paragraph paragraph = new Paragraph(" ");
        Paragraph paragraph2 = new Paragraph(" ");
        Paragraph paragraph3 = new Paragraph(" ");
        document.add(paragraph);
        document.add(paragraph2);
        document.add(paragraph3);

        PdfPTable table = new PdfPTable(8); // 3 columns.

        for(ReportPrediosRowJson report : listReport){
            PdfPCell cellSub = new PdfPCell(new Paragraph(report.getAnio()));
            cellSub.setColspan(8);
            cellSub.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cellSub);
            
            
            PdfPCell cell1 = new PdfPCell(new Paragraph("Programa"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Unidad medida"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Cantidad"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Acción"));
            PdfPCell cell5 = new PdfPCell(new Paragraph("Monto de Apoyo Aprobado $"));
            PdfPCell cell6 = new PdfPCell(new Paragraph("Monto de Apoyo Pagado $"));
            PdfPCell cell7 = new PdfPCell(new Paragraph("Resultado"));
            PdfPCell cell8 = new PdfPCell(new Paragraph("Observaciones"));
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell7.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            table.addCell(cell8);
            
            for (ReportPredioDTO row : report.getData()) {
                PdfPCell cellProgram;
                cellProgram = new PdfPCell(new Phrase(row.getPrograma()));
                cellProgram.setRowspan(row.getRecordsSize());

                table.addCell(cellProgram);
                
                for(int i=0; i<row.getRecordsSize(); i++){
                    
                    table.addCell(row.getUnidadmedida().get(i));
                    table.addCell(row.getCantidad().get(i));
                    table.addCell(row.getAccion().get(i));
                    table.addCell(row.getMontoapoyoaprobado().get(i));
                    table.addCell(row.getMontoapoyopagado().get(i));
                    table.addCell(row.getResultado().get(i));
                    table.addCell(row.getObservaciones().get(i));
                }
            }
            
            PdfPCell space1 = new PdfPCell(new Paragraph(" "));
            PdfPCell space2 = new PdfPCell(new Paragraph(" "));
            PdfPCell space3 = new PdfPCell(new Paragraph(" "));
            PdfPCell space4 = new PdfPCell(new Paragraph(" "));
            PdfPCell space5 = new PdfPCell(new Paragraph(" "));
            PdfPCell space6 = new PdfPCell(new Paragraph(" "));
            PdfPCell space7 = new PdfPCell(new Paragraph(" "));
            PdfPCell space8 = new PdfPCell(new Paragraph(" "));
            
            space1.setBorder(Rectangle.NO_BORDER);   // removes border
            space2.setBorder(Rectangle.NO_BORDER);   // removes border
            space3.setBorder(Rectangle.NO_BORDER);   // removes border
            space4.setBorder(Rectangle.NO_BORDER);   // removes border
            space5.setBorder(Rectangle.NO_BORDER);   // removes border
            space6.setBorder(Rectangle.NO_BORDER);   // removes border
            space7.setBorder(Rectangle.NO_BORDER);   // removes border
            space8.setBorder(Rectangle.NO_BORDER);   // removes border
            
            table.addCell(space1);
            table.addCell(space2);
            table.addCell(space3);
            table.addCell(space4);
            table.addCell(space5);
            table.addCell(space6);
            table.addCell(space7);
            table.addCell(space8);
            
        }
        document.add(table);
        document.close();
    }
}
