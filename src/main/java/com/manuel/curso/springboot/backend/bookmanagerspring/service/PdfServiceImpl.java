package com.manuel.curso.springboot.backend.bookmanagerspring.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.User;
import com.manuel.curso.springboot.backend.bookmanagerspring.pdf.FooterPdf;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService {

    @Override
    public ByteArrayInputStream exportUsersToPdf(List<User> users) {

        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {

            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            writer.setPageEvent(new FooterPdf());

            document.open();

            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Color.BLACK);

            Paragraph title = new Paragraph("Lista de Usuarios", font);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(90);

            // Encabezados con fondo gris y texto blanco
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);

            PdfPCell hcell;

            //? CELDA DE ID
            hcell = new PdfPCell(new Phrase("ID", headFont));
            hcell.setBackgroundColor(Color.DARK_GRAY);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            //? CELDA DE USERNAME
            hcell = new PdfPCell(new Phrase("Username", headFont));
            hcell.setBackgroundColor(Color.DARK_GRAY);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            //? CELDA DE Enabled
            hcell = new PdfPCell(new Phrase("Enabled", headFont));
            hcell.setBackgroundColor(Color.DARK_GRAY);
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (User user : users) {
                table.addCell(user.getId().toString());
                table.addCell(user.getUsername());
                table.addCell(user.isEnabled() ? "Activado" : "Desactivado");
            }

            document.add(table);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

}
