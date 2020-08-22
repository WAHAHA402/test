package cn.wahaha.test.javaWebTest.itextPdf;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.util.Random;


/**
 * @Description: PdfTest
 * @Author: zhangrenwei
 * @Date: 2020/8/22 11:29 上午
 */

public class PdfTest {


    public static final String DEST = "/Users/zhangrenwei/Desktop/test/baoGao" + new Random().nextInt(100000) + ".pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
//        file.getParentFile().mkdirs();

        new PdfTest().manipulatePdf(DEST);
    }

    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        String para1 = "哈哈哈哈";
        PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H");


        // Creating Paragraphs
        Paragraph paragraph1 = new Paragraph(para1).setFont(font);
        paragraph1.setTextAlignment(TextAlignment.CENTER);
        doc.add(paragraph1);

        // Creating a table
        float[] pointColumnWidths1 = {150f, 150f};
        Table table = new Table(pointColumnWidths1);

        // Populating row 1 and adding it to the table
        Cell cell1 = new Cell();
        cell1.add(new Paragraph("Name456456").setHorizontalAlignment(HorizontalAlignment.CENTER));
        table.addCell(cell1);

        Cell cell2 = new Cell();
        cell2.add(new Paragraph("Name"));
        table.addCell(cell2);

        // Creating nested table for contact
        float[] pointColumnWidths2 = {150f, 150f};
        Table nestedTable = new Table(pointColumnWidths2);

        // Populating row 1 and adding it to the nested table
        Cell nested1 = new Cell();
        nested1.add(new Paragraph("Name"));
        nestedTable.addCell(nested1);

        Cell nested2 = new Cell();
        nested2.add(new Paragraph("Name"));
        nestedTable.addCell(nested2);


        // Adding table to the cell
        Cell cell7 = new Cell();
        cell7.add(new Paragraph("Name"));
        table.addCell(cell7);

        Cell cell8 = new Cell();
        nestedTable.setBorder(Border.NO_BORDER);
        cell8.add(nestedTable);
        table.addCell(cell8);

        // Adding table to the document
        doc.add(table);


        // Closing the document
        doc.close();
        System.out.println("Nested Table Added successfully..");

    }
}
