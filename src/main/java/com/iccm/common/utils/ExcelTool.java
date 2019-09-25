package com.iccm.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by a on 2018/8/6.
 */
public class ExcelTool {

    private final static Logger log = LoggerFactory.getLogger(ExcelTool.class);

    /**
     * 列属性key值
     */
    public static final String COLUMNTITLE = "columnTitle";

    public static final String COLUMNFIELD = "columnField";

    public static final String COLUMNWIDTH = "columnWidth";

    /**
     * 排列方向
     */
    public static final int CENTER = 0;

    public static final int LEFT = 1;

    public static final int RIGHT = 2;

    /**
     * 单元格样式key值
     */
    public static final String TOPTITLESTYLE = "topTitle";

    public static final String TITLELINESTYLE = "titleLine";

    public static final String DATALINESTYLE = "dataLine";

    /**
     * 主标题行高
     */
    public static final short TOPTITLEHEIGHT = 35*20;

    /**
     * 导出excel
     * @param request
     * @param response
     * @param fileName 文件名
     * @param sheetName sheet页名称
     * @param topTitle 表格主标题
     * @param columnData 列属性
     * @param data 数据
     * @param wb 工作薄
     * @param topTitleNormal 默认标题样式
     * @param titleLineNormal 默认表头样式
     * @param dataLineNormal 默认数据样式
     * @param styleMap 样式集合
     * @throws Exception
     */
//    public static void export(HttpServletRequest request, HttpServletResponse response, String fileName, String sheetName, String topTitle, Map columnData, List data, HSSFWorkbook wb, boolean topTitleNormal, boolean titleLineNormal, boolean dataLineNormal, Map styleMap)throws Exception{
//        HSSFWorkbook workbook = ExcelTool.getHSSFWorkbook(sheetName,topTitle,columnData,data,wb,topTitleNormal,titleLineNormal,dataLineNormal,styleMap);
//        response.setContentType("multipart/form-data");
//        fileName = CommonUtil.getFileName(fileName+".xls",request);
//        response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
//        OutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);
//        outputStream.close();
//        outputStream.flush();
//    }

    /**
     * 生成工作簿
     * @param sheetName sheet页名称
     * @param topTitle 表格主标题
     * @param columnData 列属性
     * @param data 数据
     * @param wb 工作薄
     * @param topTitleNormal 默认标题样式
     * @param titleLineNormal 默认表头样式
     * @param dataLineNormal 默认数据样式
     * @param styleMap 样式集合
     * @return
     */
//    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String topTitle, Map columnData, List data, HSSFWorkbook wb, boolean topTitleNormal, boolean titleLineNormal, boolean dataLineNormal, Map styleMap){
//
//        String[] title = (String[]) columnData.get(COLUMNTITLE);
//        String[] field = (String[]) columnData.get(COLUMNFIELD);
//        int[] widhts = (int[])columnData.get(COLUMNWIDTH);
//        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
//        if(wb == null){
//            wb = new HSSFWorkbook();
//        }
//
//        int rowIndex = 0;//当前第几行
//        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
//        HSSFSheet sheet = wb.createSheet(sheetName);
//        HSSFCellStyle topTitleStyle = null;
//        HSSFCellStyle titleLineStyle = null;
//        HSSFCellStyle dataLineStyle = null;
//        if(topTitleNormal){
//            topTitleStyle = getStyle(wb,new CellStyler(CENTER,true,true,true,"微软雅黑",16));
//        }else{
//            if(styleMap!=null&&styleMap.get(TOPTITLESTYLE)!=null){
//                CellStyler cellStyler = (CellStyler) styleMap.get(TOPTITLESTYLE);
//                topTitleStyle = getStyle(wb,cellStyler);
//            }
//        }
//        if(titleLineNormal){
//            titleLineStyle =getStyle(wb,new CellStyler(CENTER,true,true,true,"微软雅黑",12));
//        }else{
//            if(styleMap!=null&&styleMap.get(TITLELINESTYLE)!=null){
//                CellStyler cellStyler = (CellStyler) styleMap.get(TITLELINESTYLE);
//                titleLineStyle = getStyle(wb,cellStyler);
//            }
//        }
//        if(dataLineNormal){
//            dataLineStyle = getStyle(wb,new CellStyler(CENTER,true,true,false,"微软雅黑",12));
//        }else{
//            if(styleMap!=null&&styleMap.get(DATALINESTYLE)!=null){
//                CellStyler cellStyler = (CellStyler) styleMap.get(DATALINESTYLE);
//                dataLineStyle = getStyle(wb,cellStyler);
//            }
//        }
//        if(StringUtils.isNotBlank(topTitle)){
//            sheet.addMergedRegion(new CellRangeAddress(0,0,0,title.length-1));
//            HSSFRow row = sheet.createRow(rowIndex);
//            rowIndex++;
//            Cell firstCell = row.createCell(0);
//            firstCell.setCellValue(topTitle);
//            if(topTitleStyle!=null){
//                firstCell.setCellStyle(topTitleStyle);
//            }
//            row.setHeight(TOPTITLEHEIGHT);
//        }
//        HSSFRow titleRow = sheet.createRow(rowIndex);
//        rowIndex++;
//        for(int i = 0;i < title.length;i++){
//            Cell titleCell = titleRow.createCell(i);
//            titleCell.setCellValue(title[i]);
//            sheet.setColumnWidth(i,widhts[i]*256);
//            if(titleLineStyle!=null){
//                titleCell.setCellStyle(titleLineStyle);
//            }else{
//                if(styleMap!=null&&styleMap.get(TITLELINESTYLE+i)!=null){
//                    CellStyler cellStyler = (CellStyler) styleMap.get(TITLELINESTYLE+i);
//                    titleCell.setCellStyle(getStyle(wb,cellStyler));
//                }
//            }
//        }
//        for(int i =0;i < data.size();i++){
//            HSSFRow dataRow = sheet.createRow(rowIndex);
//            rowIndex++;
//            Object object = data.get(i);
//            for(int j = 0; j < title.length;j++){
//                Cell dataCell = dataRow.createCell(j);
//                dataCell.setCellValue((String)CommonUtil.getFieldValueByName(field[j],object));
//                if(dataLineStyle!=null){
//                    dataCell.setCellStyle(dataLineStyle);
//                }else{
//                    if(styleMap!=null&&styleMap.get(DATALINESTYLE+j)!=null){
//                        CellStyler cellStyler = (CellStyler) styleMap.get(DATALINESTYLE+j);
//                        dataCell.setCellStyle(getStyle(wb,cellStyler));
//                    }
//                }
//            }
//        }
//        return wb;
//    }

    /**
     * 生成单元格样式
     * @param wb
     * @return
     */
//    public static HSSFCellStyle getStyle(HSSFWorkbook wb, CellStyler cellStyler){
//        HSSFCellStyle style = wb.createCellStyle();
//        if(cellStyler.getBorder()){
//            style.setBorderBottom(CellStyle.BORDER_THIN); //下边框
//            style.setBorderLeft(CellStyle.BORDER_THIN);//左边框
//            style.setBorderTop(CellStyle.BORDER_THIN);//上边框
//            style.setBorderRight(CellStyle.BORDER_THIN);//右边框
//        }
//        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//设置垂直居中
//        switch (cellStyler.getAlign()){
//            case CENTER:
//                style.setAlignment(CellStyle.ALIGN_CENTER);
//                break;
//            case LEFT:
//                style.setAlignment(CellStyle.ALIGN_LEFT);
//                break;
//            case RIGHT:
//                style.setAlignment(CellStyle.ALIGN_RIGHT);
//                break;
//            default:
//                style.setAlignment(CellStyle.ALIGN_CENTER);
//        }
//        style.setFillForegroundColor(cellStyler.getBackground());
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        style.setFont(getFont(wb,cellStyler));
//        return style;
//    }

//    /**
//     * 生成字体样式
//     * @param cellStyler
//     * @return
//     */
//    public static Font getFont(HSSFWorkbook wb, CellStyler cellStyler){
//        Font fontStyle = wb.createFont(); // 字体样式
//        if(cellStyler.getBold()){
//            fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
//        }
//        fontStyle.setFontName(cellStyler.getFontName()); // 字体
//        fontStyle.setFontHeightInPoints((short) cellStyler.getFontSize()); // 大小
//        fontStyle.setColor(cellStyler.getFontColor());
//        return fontStyle;
//    }

    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel

    /**
     * 上传数据
     * @param request
     * @param importSheetData
     * @return
     */
    public static Map<String,List> uploadData(HttpServletRequest request, List<ImportSheetData> importSheetData)throws Exception{
        Map<String,List> map = null;
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        //判断 request 是否有文件上传,即多部分请求
//        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            MultipartFile file = multiRequest.getFile(iter.next());
            if(file != null){
                //取得当前上传文件的文件名称
                String myFileName = file.getOriginalFilename();
                //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                if(myFileName.trim() !=""){
                    InputStream in = null;
                    in = file.getInputStream();
                    map = getBankListByExcel(in,myFileName,importSheetData);
                }
            }
//        }
        return map;
    }

    /**
     * 组装数据
     * @param in
     * @param fileName
     * @param importSheetData sheet页信息
     * @return
     */
    public static Map<String,List> getBankListByExcel(InputStream in, String fileName,List<ImportSheetData> importSheetData) throws Exception{
        Map map = new HashMap();
        //创建Excel工作薄
        Workbook work = getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        //遍历Excel中所有的sheet
        for (int i = 0; i < importSheetData.size(); i++) {
            List<Object> dataList = new ArrayList<>();
            sheet = work.getSheet(importSheetData.get(i).getSheetName());
            if(sheet==null){continue;}
            //遍历当前sheet中的所有行
            boolean ifnext = true;
            for (int j = importSheetData.get(i).getRowStart(); j < sheet.getLastRowNum()+1; j++) {
                if(!ifnext){
                    break;
                }
                boolean ifRowEmpty = true;//判断此行是否为空
                row = sheet.getRow(j);
//                if(row==null||row.getFirstCellNum()==j){continue;}
                //遍历所有的列
                Object object = importSheetData.get(i).getClaz().newInstance();
                for (int y = importSheetData.get(i).getColumnStart(); y < (importSheetData.get(i).getColumnStart()+importSheetData.get(i).getColumnNum()); y++) {
                    try{
                        cell = row.getCell(y);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        String value = cell.getStringCellValue();
                        if(StringUtils.isNotBlank(value)){
                            ifRowEmpty = false;
                            AssignValueForAttributeUtil.setAttrributeValue(object,importSheetData.get(i).getFields()[y-importSheetData.get(i).getColumnStart()],value);
                        }
                    }catch (Exception e){
                        //不做处理
                    }
                }
                if(!ifRowEmpty){
                    dataList.add(object);
                }else{
                    ifnext = false;
                }
            }
            map.put(importSheetData.get(i).getDataKey(),dataList);
        }
        work.close();
        return map;
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

}
