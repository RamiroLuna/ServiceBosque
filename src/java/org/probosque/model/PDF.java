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
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import org.probosque.service.Configuration;

public class PDF {

    public PDF(PDFcontent content, OutputStream output) throws FileNotFoundException, DocumentException, BadElementException, IOException {
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

        PdfPTable table = new PdfPTable(2); // 3 columns.

        for (int iRow = 0; iRow < content.getRows().size(); iRow++) {
            PdfPCell cell1 = new PdfPCell(new Paragraph("Columna"));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Valor"));
            cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell1);
            table.addCell(cell2);
            for (int i = 0; i < content.getColumns().size(); i++) {
                if (content.getColumns().get(i).getDatatype().equals("records")) {
                    //pintar subregistros
                    //correcto
                    PDFcontent subContent = (PDFcontent) content.getRows().get(iRow).getValues().get(i);
                    int size = subContent.getRows().size();
                    PdfPCell cellSub = new PdfPCell(new Paragraph(content.getColumns().get(i).getLabel() + " " + size + " registros"));
                    cellSub.setColspan(2);
                    cellSub.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    table.addCell(cellSub);
                    
                    for (int isubRow = 0; isubRow < subContent.getRows().size(); isubRow++) {
                        PdfPCell subCell1 = new PdfPCell(new Paragraph("Columna"));
                        PdfPCell subCell2 = new PdfPCell(new Paragraph("Valor"));
                        subCell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        subCell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        table.addCell(subCell1);
                        table.addCell(subCell2);
                        for (int isub = 0; isub < subContent.getColumns().size(); isub++) {
                            table.addCell(subContent.getColumns().get(isub).getLabel());
                            table.addCell(String.valueOf(subContent.getRows().get(isubRow).getValues().get(isub)));
                        }
                    }

                } else {
                    table.addCell(content.getColumns().get(i).getLabel());
                    table.addCell(String.valueOf(content.getRows().get(iRow).getValues().get(i)));
                }
            }
            PdfPCell space1 = new PdfPCell(new Paragraph(" "));
            PdfPCell space2 = new PdfPCell(new Paragraph(" "));
            space1.setBorder(Rectangle.NO_BORDER);   // removes border
            space2.setBorder(Rectangle.NO_BORDER);   // removes border
            table.addCell(space1);
            table.addCell(space2);
        }
        document.add(table);
        document.close();
    }
}