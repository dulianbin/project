package com.zhaopin.report.util;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.zhaopin.report.mapper.model.ApplyCost;



public class ExcelReportUtil {
	
	/**
     * 将数据以EXCEL 的形式输出到指定IO设备上
     * 
     * @param title
     *            表格标题名
     * @param headers
     *            表格属性列名二维数组{英文列名，中文列头}
     * @param dataset
     *            List<Map>集合数据
     * @param out
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地或者网络中
     */
    public static void exportExcel(String title, String[][] headers, List<ApplyCost> list, HttpServletResponse response) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);
        sheet.setDefaultColumnWidth(18);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i][1]);
            cell.setCellValue(text);
        }
        
        createPlatFormActivitiesExcelRowData(list,workbook,sheet ,row, style2);

        try {
        	 response.reset();
             response.setCharacterEncoding("UTF-8");
             response.setContentType("application/vnd.ms-excel");
             response.setHeader("content-Disposition","attachment;filename="+URLEncoder.encode(title + ".xls","utf-8"));
             OutputStream out = response.getOutputStream();
             workbook.write(out);
             out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } 

    }
    
    
    
    
    
    
	/**
     * 将数据以EXCEL 的形式输出到指定IO设备上
     * 
     * @param title
     *            表格标题名
     * @param headers
     *            表格属性列名二维数组{英文列名，中文列头}
     * @param dataset
     *            List<Map>集合数据
     * @param out
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地或者网络中
     */
    public static void exportYearBudgetFeeExcel(String title, String[][] headers, List<Map<String, Object>> list, HttpServletResponse response) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);
        sheet.setDefaultColumnWidth(18);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i][1]);
            cell.setCellValue(text);
        }
        
        createYearBudgetFeeExcelRowData(list,workbook,sheet ,row, style2);

        try {
        	 response.reset();
             response.setCharacterEncoding("UTF-8");
             response.setContentType("application/vnd.ms-excel");
             response.setHeader("content-Disposition","attachment;filename="+URLEncoder.encode(title + ".xls","utf-8"));
             OutputStream out = response.getOutputStream();
             workbook.write(out);
             out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } 

    }
    
    
    
    
    
    
    
	/**
     * 将数据以EXCEL 的形式输出到指定IO设备上
     * 
     * @param title
     *            表格标题名
     * @param headers
     *            表格属性列名二维数组{英文列名，中文列头}
     * @param dataset
     *            List<Map>集合数据
     * @param out
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地或者网络中
     */
    public static void exportCityCostApplyExcel(String title, String[][] headers, List<Map<String, Object>> list, HttpServletResponse response) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);
        sheet.setDefaultColumnWidth(18);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i][1]);
            cell.setCellValue(text);
        }
        
        createCityCostApplyExcelRowData(list,workbook,sheet ,row, style2);

        try {
        	 response.reset();
             response.setCharacterEncoding("UTF-8");
             response.setContentType("application/vnd.ms-excel");
             response.setHeader("content-Disposition","attachment;filename="+URLEncoder.encode(title + ".xls","utf-8"));
             OutputStream out = response.getOutputStream();
             workbook.write(out);
             out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } 

    }
    
    
	/**
     * 将数据以EXCEL 的形式输出到指定IO设备上
     * 
     * @param title
     *            表格标题名
     * @param headers
     *            表格属性列名二维数组{英文列名，中文列头}
     * @param dataset
     *            List<Map>集合数据
     * @param out
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地或者网络中
     */
    public static void exportQuarterAllFeeExcel(String title, String[][] headers, List<Map<String, Object>> list, HttpServletResponse response) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);
        sheet.setDefaultColumnWidth(18);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i][1]);
            cell.setCellValue(text);
        }
        
        createQuarterAllFeeExcelRowData(list,workbook,sheet ,row, style2);

        try {
        	 response.reset();
             response.setCharacterEncoding("UTF-8");
             response.setContentType("application/vnd.ms-excel");
             response.setHeader("content-Disposition","attachment;filename="+URLEncoder.encode(title + ".xls","utf-8"));
             OutputStream out = response.getOutputStream();
             workbook.write(out);
             out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } 

    }
    
    private static void createYearBudgetFeeExcelRowData(List<Map<String, Object>>  list,HSSFWorkbook workbook,
    		HSSFSheet sheet ,HSSFRow row,HSSFCellStyle style2){
        if(null != list){
            for(int i=0; i<list.size(); i++){
                row = sheet.createRow(i + 1);
                String applyTimeStr= (String) list.get(i).get("city");   	 		//城市
                String applyQuarter= (String) list.get(i).get("apply_quarter");   	 		//申请季度
                BigDecimal sz= new BigDecimal(list.get(i).get("sz").toString());   	 		//申请季度
                
                BigDecimal gz= new BigDecimal(list.get(i).get("gz").toString()) ;   	 		//城市
                
                
                HSSFCell cell = row.createCell(0);
                HSSFRichTextString richString = new HSSFRichTextString(applyTimeStr);
                HSSFFont font_1 = workbook.createFont();
                richString.applyFont(font_1);
                cell.setCellValue(richString);
                cell.setCellStyle(style2);
                
                HSSFCell cell2 = row.createCell(1);
                HSSFRichTextString richString2 = new HSSFRichTextString(applyQuarter+"");
                HSSFFont font_2 = workbook.createFont();
                richString2.applyFont(font_2);
                cell2.setCellValue(richString2);
                cell2.setCellStyle(style2);
                
                HSSFCell cell3 = row.createCell(2);
                HSSFRichTextString richString3 = new HSSFRichTextString(sz+"");
                HSSFFont font_3 = workbook.createFont();
                richString3.applyFont(font_3);
                cell3.setCellValue(richString3);
                cell3.setCellStyle(style2);
                
                
                HSSFCell cell4 = row.createCell(3);
                HSSFRichTextString richString4 = new HSSFRichTextString(gz+"");
                HSSFFont font_4 = workbook.createFont();
                richString4.applyFont(font_4);
                cell4.setCellValue(richString4);
                cell4.setCellStyle(style2);
                
                
                
                
                BigDecimal deptName= new BigDecimal(list.get(i).get("cs").toString());  	 		//部门
                HSSFCell cell44 = row.createCell(4);
                HSSFRichTextString richString44 = new HSSFRichTextString(deptName+"");
                HSSFFont font_44 = workbook.createFont();
                richString44.applyFont(font_44);
                cell44.setCellValue(richString44);
                cell44.setCellStyle(style2);
                
                
                BigDecimal realName= new BigDecimal(list.get(i).get("fz").toString());   	 		//部门
                HSSFCell cell5 = row.createCell(5);
                HSSFRichTextString richString5 = new HSSFRichTextString(realName+"");
                HSSFFont font_5 = workbook.createFont();
                richString5.applyFont(font_5);
                cell5.setCellValue(richString5);
                cell5.setCellStyle(style2);
                
                
                BigDecimal costTypeName= new BigDecimal(list.get(i).get("nc").toString());   	 		//部门
                HSSFCell cell6 = row.createCell(6);
                HSSFRichTextString richString6 = new HSSFRichTextString(costTypeName+"");
                HSSFFont font_6 = workbook.createFont();
                richString6.applyFont(font_6);
                cell6.setCellValue(richString6);
                cell6.setCellStyle(style2);
                
                
                BigDecimal totalFee= new BigDecimal(list.get(i).get("fs").toString());   	 		//部门
                HSSFCell cell7 = row.createCell(7);
                HSSFRichTextString richString7 = new HSSFRichTextString(totalFee+"");
                HSSFFont font_7 = workbook.createFont();
                richString7.applyFont(font_7);
                cell7.setCellValue(richString7);
                cell7.setCellStyle(style2);
                
                
                
                
                BigDecimal xm= new BigDecimal(list.get(i).get("xm").toString());   	 		//部门
                HSSFCell cell8 = row.createCell(8);
                HSSFRichTextString richString8 = new HSSFRichTextString(xm+"");
                HSSFFont font_8 = workbook.createFont();
                richString8.applyFont(font_8);
                cell8.setCellValue(richString8);
                cell8.setCellStyle(style2);
               
                
                BigDecimal dg= new BigDecimal(list.get(i).get("dg").toString());   	 		//部门
                HSSFCell cell9 = row.createCell(9);
                HSSFRichTextString richString9 = new HSSFRichTextString(dg+"");
                HSSFFont font_9 = workbook.createFont();
                richString9.applyFont(font_9);
                cell9.setCellValue(richString9);
                cell9.setCellStyle(style2);
                
                BigDecimal qyyy= new BigDecimal(list.get(i).get("qyyy").toString());   	 		//备注
                HSSFCell cell10 = row.createCell(10);
                HSSFRichTextString richString10 = new HSSFRichTextString(qyyy+"");
                HSSFFont font_10 = workbook.createFont();
                richString10.applyFont(font_10);
                cell10.setCellValue(richString10);
                cell10.setCellStyle(style2);
                
                
                BigDecimal zh= new BigDecimal(list.get(i).get("zh").toString());   	 		//部门
                HSSFCell cell11 = row.createCell(11);
                HSSFRichTextString richString11 = new HSSFRichTextString(zh+"");
                HSSFFont font_11 = workbook.createFont();
                richString11.applyFont(font_11);
                cell11.setCellValue(richString11);
                cell11.setCellStyle(style2);
                
                BigDecimal hk= new BigDecimal(list.get(i).get("hk").toString());   	 		//备注
                HSSFCell cell12 = row.createCell(12);
                HSSFRichTextString richString12 = new HSSFRichTextString(hk+"");
                HSSFFont font_12 = workbook.createFont();
                richString12.applyFont(font_12);
                cell12.setCellValue(richString12);
                cell12.setCellStyle(style2);
              
            }
        }
    }
    
    
    
    
    private static void createCityCostApplyExcelRowData(List<Map<String, Object>>  list,HSSFWorkbook workbook,
    		HSSFSheet sheet ,HSSFRow row,HSSFCellStyle style2){
        if(null != list){
            for(int i=0; i<list.size(); i++){
                row = sheet.createRow(i + 1);
                String applyQuarter= (String) list.get(i).get("apply_quarter");   	 		//申请季度
                String applyArea= (String) list.get(i).get("apply_area");   	 		//申请季度
                String applyTimeStr= (String) list.get(i).get("city");   	 		//申请时间
                BigDecimal city= new BigDecimal(list.get(i).get("budget_fee1").toString()) ;   	 		//城市
               
                
                HSSFCell cell = row.createCell(0);
                HSSFRichTextString richString = new HSSFRichTextString(applyQuarter);
                HSSFFont font_1 = workbook.createFont();
                richString.applyFont(font_1);
                cell.setCellValue(richString);
                cell.setCellStyle(style2);
                
                HSSFCell cell2 = row.createCell(1);
                HSSFRichTextString richString2 = new HSSFRichTextString(applyArea+"");
                HSSFFont font_2 = workbook.createFont();
                richString2.applyFont(font_2);
                cell2.setCellValue(richString2);
                cell2.setCellStyle(style2);
                
                HSSFCell cell3 = row.createCell(2);
                HSSFRichTextString richString3 = new HSSFRichTextString(applyTimeStr+"");
                HSSFFont font_3 = workbook.createFont();
                richString3.applyFont(font_3);
                cell3.setCellValue(richString3);
                cell3.setCellStyle(style2);
                
                
                HSSFCell cell4 = row.createCell(3);
                HSSFRichTextString richString4 = new HSSFRichTextString(city+"");
                HSSFFont font_4 = workbook.createFont();
                richString4.applyFont(font_4);
                cell4.setCellValue(richString4);
                cell4.setCellStyle(style2);
                
                
                
                
                BigDecimal deptName= new BigDecimal(list.get(i).get("total_fee_sum").toString());  	 		//部门
                HSSFCell cell44 = row.createCell(4);
                HSSFRichTextString richString44 = new HSSFRichTextString(deptName+"");
                HSSFFont font_44 = workbook.createFont();
                richString44.applyFont(font_44);
                cell44.setCellValue(richString44);
                cell44.setCellStyle(style2);
                
                
                BigDecimal realName= new BigDecimal(list.get(i).get("real_fee_sum").toString());   	 		//部门
                HSSFCell cell5 = row.createCell(5);
                HSSFRichTextString richString5 = new HSSFRichTextString(realName+"");
                HSSFFont font_5 = workbook.createFont();
                richString5.applyFont(font_5);
                cell5.setCellValue(richString5);
                cell5.setCellStyle(style2);
                
                
                BigDecimal costTypeName= new BigDecimal(list.get(i).get("can_apply_fee").toString());   	 		//部门
                HSSFCell cell6 = row.createCell(6);
                HSSFRichTextString richString6 = new HSSFRichTextString(costTypeName+"");
                HSSFFont font_6 = workbook.createFont();
                richString6.applyFont(font_6);
                cell6.setCellValue(richString6);
                cell6.setCellStyle(style2);
                
                
                BigDecimal totalFee= new BigDecimal(list.get(i).get("real_shengyu_fee").toString());   	 		//部门
                HSSFCell cell7 = row.createCell(7);
                HSSFRichTextString richString7 = new HSSFRichTextString(totalFee+"");
                HSSFFont font_7 = workbook.createFont();
                richString7.applyFont(font_7);
                cell7.setCellValue(richString7);
                cell7.setCellStyle(style2);
                
                
              
            }
        }
    }
    
    
    private static void createQuarterAllFeeExcelRowData(List<Map<String, Object>>  list,HSSFWorkbook workbook,
    		HSSFSheet sheet ,HSSFRow row,HSSFCellStyle style2){
        if(null != list){
            for(int i=0; i<list.size(); i++){
                row = sheet.createRow(i + 1);
                String applyQuarter= (String) list.get(i).get("city");   	 		//申请季度
                String applyArea= (String) list.get(i).get("dept_name");   	 		//申请季度
                
                BigDecimal applyTimeStr= new BigDecimal(list.get(i).get("quarter_1").toString());   	 		//申请时间
                BigDecimal city= new BigDecimal(list.get(i).get("quarter_2").toString());  ;   	 		//城市
               
               
                HSSFCell cell = row.createCell(0);
                HSSFRichTextString richString = new HSSFRichTextString(applyQuarter);
                HSSFFont font_1 = workbook.createFont();
                richString.applyFont(font_1);
                cell.setCellValue(richString);
                cell.setCellStyle(style2);
                
                HSSFCell cell2 = row.createCell(1);
                HSSFRichTextString richString2 = new HSSFRichTextString(applyArea+"");
                HSSFFont font_2 = workbook.createFont();
                richString2.applyFont(font_2);
                cell2.setCellValue(richString2);
                cell2.setCellStyle(style2);
                
                HSSFCell cell3 = row.createCell(2);
                HSSFRichTextString richString3 = new HSSFRichTextString(applyTimeStr+"");
                HSSFFont font_3 = workbook.createFont();
                richString3.applyFont(font_3);
                cell3.setCellValue(richString3);
                cell3.setCellStyle(style2);
                
                
                HSSFCell cell4 = row.createCell(3);
                HSSFRichTextString richString4 = new HSSFRichTextString(city+"");
                HSSFFont font_4 = workbook.createFont();
                richString4.applyFont(font_4);
                cell4.setCellValue(richString4);
                cell4.setCellStyle(style2);
                
                
                
                
                BigDecimal deptName= new BigDecimal(list.get(i).get("quarter_3").toString());  	 		//部门
                
                HSSFCell cell44 = row.createCell(4);
                HSSFRichTextString richString44 = new HSSFRichTextString(deptName+"");
                HSSFFont font_44 = workbook.createFont();
                richString44.applyFont(font_44);
                cell44.setCellValue(richString44);
                cell44.setCellStyle(style2);
                
                BigDecimal realName= new BigDecimal(list.get(i).get("quarter_4").toString());
                
                HSSFCell cell5 = row.createCell(5);
                HSSFRichTextString richString5 = new HSSFRichTextString(realName+"");
                HSSFFont font_5 = workbook.createFont();
                richString5.applyFont(font_5);
                cell5.setCellValue(richString5);
                cell5.setCellStyle(style2);
                
                
                BigDecimal costTypeName= new BigDecimal(list.get(i).get("apply_allfee").toString());   	 		//部门
                HSSFCell cell6 = row.createCell(6);
                HSSFRichTextString richString6 = new HSSFRichTextString(costTypeName+"");
                HSSFFont font_6 = workbook.createFont();
                richString6.applyFont(font_6);
                cell6.setCellValue(richString6);
                cell6.setCellStyle(style2);
                String budget_fee_sum=list.get(i).get("budget_fee_sum").toString();
                String totalFee="";
                if(budget_fee_sum!=null && "年度预算费用缺失".equals(budget_fee_sum)){
                	totalFee=budget_fee_sum;
                }else{
                	totalFee= new BigDecimal(list.get(i).get("budget_fee_sum").toString())+"";
                }
                
                HSSFCell cell7 = row.createCell(7);
                HSSFRichTextString richString7 = new HSSFRichTextString(totalFee);
                HSSFFont font_7 = workbook.createFont();
                richString7.applyFont(font_7);
                cell7.setCellValue(richString7);
                cell7.setCellStyle(style2);
                
                String realFee= list.get(i).get("fee_rante").toString();   	 		//部门
                HSSFCell cell8 = row.createCell(8);
                HSSFRichTextString richString8 = new HSSFRichTextString(realFee+"");
                HSSFFont font_8 = workbook.createFont();
                richString8.applyFont(font_8);
                cell8.setCellValue(richString8);
                cell8.setCellStyle(style2);
              
            }
        }
    }
    
    
    private static void createPlatFormActivitiesExcelRowData(List<ApplyCost> list,HSSFWorkbook workbook,
    		HSSFSheet sheet ,HSSFRow row,HSSFCellStyle style2){
        if(null != list){
            for(int i=0; i<list.size(); i++){
                row = sheet.createRow(i + 1);
                String applyQuarter= list.get(i).getApplyQuarter();   	 		//申请季度
                String applyArea= list.get(i).getApplyArea();   	 		//申请季度
                String applyTimeStr= list.get(i).getApplyTimeStr();   	 		//申请时间
                String city= list.get(i).getCity();   	 		//城市
               
                
                HSSFCell cell = row.createCell(0);
                HSSFRichTextString richString = new HSSFRichTextString(applyQuarter);
                HSSFFont font_1 = workbook.createFont();
                richString.applyFont(font_1);
                cell.setCellValue(richString);
                cell.setCellStyle(style2);
                
                HSSFCell cell2 = row.createCell(1);
                HSSFRichTextString richString2 = new HSSFRichTextString(applyTimeStr+"");
                HSSFFont font_2 = workbook.createFont();
                richString2.applyFont(font_2);
                cell2.setCellValue(richString2);
                cell2.setCellStyle(style2);
                
                HSSFCell cell3 = row.createCell(2);
                HSSFRichTextString richString3 = new HSSFRichTextString(applyArea+"");
                HSSFFont font_3 = workbook.createFont();
                richString3.applyFont(font_3);
                cell3.setCellValue(richString3);
                cell3.setCellStyle(style2);
                
                
                HSSFCell cell4 = row.createCell(3);
                HSSFRichTextString richString4 = new HSSFRichTextString(city+"");
                HSSFFont font_4 = workbook.createFont();
                richString4.applyFont(font_4);
                cell4.setCellValue(richString4);
                cell4.setCellStyle(style2);
                
                
                String deptName= list.get(i).getDeptName();   	 		//部门
                HSSFCell cell44 = row.createCell(4);
                HSSFRichTextString richString44 = new HSSFRichTextString(deptName+"");
                HSSFFont font_44 = workbook.createFont();
                richString44.applyFont(font_44);
                cell44.setCellValue(richString44);
                cell44.setCellStyle(style2);
                
                
                String realName= list.get(i).getRealName();   	 		//部门
                HSSFCell cell5 = row.createCell(5);
                HSSFRichTextString richString5 = new HSSFRichTextString(realName+"");
                HSSFFont font_5 = workbook.createFont();
                richString5.applyFont(font_5);
                cell5.setCellValue(richString5);
                cell5.setCellStyle(style2);
                
                
                String costTypeName= list.get(i).getCostTypeName();   	 		//部门
                HSSFCell cell6 = row.createCell(6);
                HSSFRichTextString richString6 = new HSSFRichTextString(costTypeName+"");
                HSSFFont font_6 = workbook.createFont();
                richString6.applyFont(font_6);
                cell6.setCellValue(richString6);
                cell6.setCellStyle(style2);
                
                
                BigDecimal totalFee= list.get(i).getTotalFee();   	 		//部门
                HSSFCell cell7 = row.createCell(7);
                HSSFRichTextString richString7 = new HSSFRichTextString(totalFee.floatValue()+"");
                HSSFFont font_7 = workbook.createFont();
                richString7.applyFont(font_7);
                cell7.setCellValue(richString7);
                cell7.setCellStyle(style2);
                
                
                BigDecimal realFee= list.get(i).getRealFee();   	 		//部门
                HSSFCell cell8 = row.createCell(8);
                HSSFRichTextString richString8 = new HSSFRichTextString(realFee.floatValue()+"");
                HSSFFont font_8 = workbook.createFont();
                richString8.applyFont(font_8);
                cell8.setCellValue(richString8);
                cell8.setCellStyle(style2);
               
                
                BigDecimal shengyuFee= list.get(i).getShengyuFee();   	 		//部门
                HSSFCell cell9 = row.createCell(9);
                HSSFRichTextString richString9 = new HSSFRichTextString(shengyuFee.floatValue()+"");
                HSSFFont font_9 = workbook.createFont();
                richString9.applyFont(font_9);
                cell9.setCellValue(richString9);
                cell9.setCellStyle(style2);
                
                String remark= list.get(i).getRemark();   	 		//备注
                HSSFCell cell10 = row.createCell(10);
                HSSFRichTextString richString10 = new HSSFRichTextString(remark+"");
                HSSFFont font_10 = workbook.createFont();
                richString10.applyFont(font_10);
                cell10.setCellValue(richString10);
                cell10.setCellStyle(style2);
            }
        }
    }
    

    
	
}
