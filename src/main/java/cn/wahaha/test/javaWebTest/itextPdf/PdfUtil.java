package cn.wahaha.test.javaWebTest.itextPdf;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.IOException;

/**
 * @Description: PdfUtil
 * @Author: zhangrenwei
 * @Date: 2020/8/24 1:08 下午
 */

public class PdfUtil {

    public static PdfFont font = null;

    static {
        try {
            font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建一个水平居中的文字段落
     * @param content 内容
     * @return
     */
    public static Document createCenterParagraph(Document document, String content, int fontSize) {
        Paragraph paragraph = new Paragraph(content);
        paragraph.setTextAlignment(TextAlignment.CENTER).setFont(font).setFontSize(fontSize).setBold();
        document.add(paragraph);
        return document;
    }

    /**
     * 创建一个向右对齐的文字段落
     * @param content 内容
     * @return
     */
    public static Document createRightParagraph(Document document, String content) {
        Paragraph paragraph = new Paragraph(content);
        paragraph.setTextAlignment(TextAlignment.RIGHT).setFont(font);
        document.add(paragraph);
        return document;
    }

    /**
     * 创建一个向左对齐的文字段落
     * @param content 内容
     * @return
     */
    public static Document createLeftParagraph(Document document, String content) {
        Paragraph paragraph = new Paragraph(content);
        paragraph.setTextAlignment(TextAlignment.LEFT).setFont(font);
        document.add(paragraph);
        return document;
    }

    /**
     * 创建一个水平居中的表
     * @return
     */
    public static Document createPlanInfo(Document document) {
        Table table = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();

        Cell name = new Cell(1, 1).add(getChineseParagraph("计划名称："));
        table.addCell(name);
        Cell name1 = new Cell(1, 5);
        table.addCell(name1);

        Cell org = new Cell(1,1).add(getChineseParagraph("检测机构："));
        table.addCell(org);
        Cell org1 = new Cell(1,5);
        table.addCell(org1);

        Cell project = new Cell(1,1).add(getChineseParagraph("检测项目："));
        table.addCell(project);
        Cell project1 = new Cell(1,5);
        table.addCell(project1);

        Cell principal = new Cell(2,1).add(getChineseParagraph("项目负责人：\n" +
                "（委托人）"));
        table.addCell(principal);
        Cell principal1 = new Cell(2,2);
        table.addCell(principal1);

        Cell phone = new Cell(2,1).add(getChineseParagraph("联系方式："));
        table.addCell(phone);
        Cell phone1 = new Cell(2,2);
        table.addCell(phone1);

        Cell desc = new Cell(1,1).add(getChineseParagraph("计划描述："));
        table.addCell(desc);
        Cell desc1 = new Cell(1,5);
        table.addCell(desc1);

        Cell requirement = new Cell(2,1).add(getChineseParagraph("测试要求："));
        table.addCell(requirement);
        Cell requirement1 = new Cell(2,5).add(getChineseParagraph("请仔细阅读测试用例描述及步骤，按照所需接口实现相应接口功能，最终提供服务器地址进行检测。"));
        table.addCell(requirement1);

        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(table);

        return document;
    }

    public static Document createCaseInfo(Document document) {
        Table table = new Table(UnitValue.createPercentArray(6)).useAllAvailableWidth();

        Cell name = new Cell(1, 1).add(getChineseParagraph("用例ID："));
        table.addCell(name);
        Cell name1 = new Cell(1, 2);
        table.addCell(name1);

        Cell org = new Cell(1,1).add(getChineseParagraph("用例名称："));
        table.addCell(org);
        Cell org1 = new Cell(1,2);
        table.addCell(org1);

        Cell project = new Cell(1,1).add(getChineseParagraph("所属分类："));
        table.addCell(project);
        Cell project1 = new Cell(1,5);
        table.addCell(project1);

        Cell principal = new Cell(3,1).add(getChineseParagraph("用例描述"));
        table.addCell(principal);
        Cell principal1 = new Cell(3,5);
        table.addCell(principal1);

        Cell phone = new Cell(3,1).add(getChineseParagraph("测试步骤："));
        table.addCell(phone);
        Cell phone1 = new Cell(3,5);
        table.addCell(phone1);

        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(table);

        return document;
    }

    public static Document createParamInfo(Document document) {
        Table table = new Table(UnitValue.createPercentArray(10)).useAllAvailableWidth();

        Cell name = new Cell(1, 2).add(getChineseParagraph("接口ID："));
        table.addCell(name);
        Cell name1 = new Cell(1, 3);
        table.addCell(name1);

        Cell org = new Cell(1,2).add(getChineseParagraph("接口名称："));
        table.addCell(org);
        Cell org1 = new Cell(1,3);
        table.addCell(org1);

        Cell project = new Cell(1,2).add(getChineseParagraph("接口地址："));
        table.addCell(project);
        Cell project1 = new Cell(1,3);
        table.addCell(project1);

        Cell principal = new Cell(1,2).add(getChineseParagraph("请求方式："));
        table.addCell(principal);
        Cell principal1 = new Cell(1,3);
        table.addCell(principal1);

        Cell phone = new Cell(1,2).add(getChineseParagraph("接口描述："));
        table.addCell(phone);
        Cell phone1 = new Cell(1,8);
        table.addCell(phone1);

        Cell param0 = new Cell(1,10).add(getChineseParagraph("接口参数").setHorizontalAlignment(HorizontalAlignment.CENTER));
        table.addCell(param0);
        Cell param = new Cell(1,2).add(getChineseParagraph("参数名").setHorizontalAlignment(HorizontalAlignment.CENTER));
        table.addCell(param);
        Cell param1 = new Cell(1,2).add(getChineseParagraph("参数类型").setHorizontalAlignment(HorizontalAlignment.CENTER));
        table.addCell(param1);
        Cell param2 = new Cell(1,2).add(getChineseParagraph("默认值").setHorizontalAlignment(HorizontalAlignment.CENTER));
        table.addCell(param2);
        Cell param3 = new Cell(1,2).add(getChineseParagraph("是否必填").setHorizontalAlignment(HorizontalAlignment.CENTER));
        table.addCell(param3);
        Cell param4 = new Cell(1,2).add(getChineseParagraph("描述").setHorizontalAlignment(HorizontalAlignment.CENTER));
        table.addCell(param4);

        createSimpleTable(3, 5, table);

        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        document.add(table);

        Cell returnParam = new Cell(1,10).add(getChineseParagraph("响应JSON字段").setHorizontalAlignment(HorizontalAlignment.CENTER));
        table.addCell(returnParam);
        Cell returnParam1 = new Cell(1,10).add(getChineseParagraph("code"));
        table.addCell(returnParam1);
        Cell returnParam2 = new Cell(1,10).add(getChineseParagraph("0：请求成功； 1：请求失败"));
        table.addCell(returnParam2);
        Cell returnParam3 = new Cell(1,10).add(getChineseParagraph("message"));
        table.addCell(returnParam3);
        Cell returnParam4 = new Cell(1,10).add(getChineseParagraph("请求成功 / 请求失败"));
        table.addCell(returnParam4);
        Cell returnParam5 = new Cell(1,10).add(getChineseParagraph("data"));
        table.addCell(returnParam5);
        Cell returnParam6 = new Cell(1,10).add(getChineseParagraph("true / false"));
        table.addCell(returnParam6);

        return document;
    }

    public static Table createSimpleTable(int row, int col, Table table){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Cell name = new Cell(1, 2);
                table.addCell(name);
            }
        }

        return table;
    }

    public static Paragraph getChineseParagraph(String content){
        return new Paragraph(content).setFont(font);
    }
}
