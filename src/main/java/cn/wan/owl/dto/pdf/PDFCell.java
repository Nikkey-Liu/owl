package cn.wan.owl.dto.pdf;

import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;

public class PDFCell {
    PdfPCell cell;

    public PDFCell(String cellString) {
        cell = new PdfPCell(new Phrase(cellString));
    }

    public PdfPCell getCell() {
        return cell;
    }

    public void setCell(PdfPCell cell) {
        this.cell = cell;
    }
}
