package com.iccm.common.utils;

/**
 * Created by a on 2018/8/9.
 */
public class ImportSheetData {

    /**
     * 对应的属性名称（必须按sheet页中列表表头顺序排列）
     */
    private String[] fields;

    /**
     * 组装数据中key值
     */
    private String dataKey;

    /**
     * sheet页面名称
     */
    private String sheetName;

    /**
     * 读取数据开始行
     */
    private int rowStart;

    /**
     * 读取护具开始列
     */
    private int columnStart;

    /**
     * 列的数量
     */
    private int columnNum;

    /**
     * 数据类
     */
    private Class<?> claz;

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public int getRowStart() {
        return rowStart;
    }

    public void setRowStart(int rowStart) {
        this.rowStart = rowStart;
    }

    public int getColumnStart() {
        return columnStart;
    }

    public void setColumnStart(int columnStart) {
        this.columnStart = columnStart;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public Class<?> getClaz() {
        return claz;
    }

    public void setClaz(Class<?> claz) {
        this.claz = claz;
    }

    public ImportSheetData(String[] fields, String dataKey, String sheetName, int rowStart, int columnStart, int columnNum, Class<?> claz) {
        this.fields = fields;
        this.dataKey = dataKey;
        this.sheetName = sheetName;
        this.rowStart = rowStart;
        this.columnStart = columnStart;
        this.columnNum = columnNum;
        this.claz = claz;
    }

    public ImportSheetData() {
    }
}
