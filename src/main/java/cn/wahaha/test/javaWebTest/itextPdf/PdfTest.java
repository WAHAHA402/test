package cn.wahaha.test.javaWebTest.itextPdf;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import java.io.IOException;
import java.util.Random;

import static cn.wahaha.test.javaWebTest.itextPdf.PdfUtil.*;


/**
 * @Description: PdfTest
 * @Author: zhangrenwei
 * @Date: 2020/8/22 11:29 上午
 */

public class PdfTest {


    public static final String DEST = "/Users/zhangrenwei/Desktop/test/baoGao" + new Random().nextInt(100000) + ".pdf";
    public static PdfFont font = null;

    static {
        try {
            font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        PdfTest test = new PdfTest();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST));
        Document doc = new Document(pdfDoc);

        createCenterParagraph(doc, "区块链系统安全检测标准", 24);
        createRightParagraph(doc, "计划编号：_________");
        createPlanInfo(doc);

        for (int i = 0; i < 3; i++) {
            createCenterParagraph(doc, "测试用例", 20);
            createCaseInfo(doc);
        }

        for (int i = 0; i < 3; i++) {
            createCenterParagraph(doc, "所需接口", 20);
            createParamInfo(doc);
        }

        createLeftParagraph(doc, "接口响应示例：");
        createLeftParagraph(doc, "{     \n" +
                "\t\"code\": 0,      \n" +
                "\t\"message\": \"请求成功\",     \n" +
                "\t \"data\": true / false\n" +
                "}");


        doc.close();

    }
}
