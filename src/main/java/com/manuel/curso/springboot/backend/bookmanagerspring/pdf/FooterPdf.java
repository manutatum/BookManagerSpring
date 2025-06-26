package com.manuel.curso.springboot.backend.bookmanagerspring.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;

public class FooterPdf extends PdfPageEventHelper {
    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        Font font = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE,9, Color.GRAY);

        Phrase footer = new Phrase("Manuel Jiménez Bravo", font);

        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_CENTER,
                footer,
                (document.right() - document.rightMargin()),
                document.bottom() - 10, 0);
    }
}
