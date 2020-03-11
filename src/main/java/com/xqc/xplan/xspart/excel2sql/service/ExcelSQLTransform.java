package com.xqc.xplan.xspart.excel2sql.service;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * In order to transform excel to sql/hsql,
 * otherwise, as same with sql/hsql to excel.
 */
public class ExcelSQLTransform {

    public static void readExcel(String filePath, String storePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        String suffix = filePath.substring(filePath.lastIndexOf("."));

        if (!".xls".equals(suffix) && !".xlsx".equals(suffix)) {
            return;
        }

        //返回值列
        List<Map> resultList = new ArrayList<>();
        resultList = readExcel2007(filePath, storePath);
    }

    public static List<Map> readExcel2007(String filePath, String storePath) throws IOException {
        List<Map> valueList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            XSSFWorkbook xwb = new XSSFWorkbook(fis);       // 构造 XSSFWorkbook 对象，strPath 传入文件路径

            int sheetNum = xwb.getNumberOfSheets();
            XSSFSheet sheet;
            XSSFRow row;
            StringBuilder sql;

            for (int s = 0; s <= sheetNum - 1; s++) {
                sheet = xwb.getSheetAt(s);
                String table_name = sheet.getSheetName();
                int totalRow = sheet.getLastRowNum();
                sql = new StringBuilder("-- Create table\nCREATE TABLE " + table_name + "\n(\n");
                for (int i = 1; i <= totalRow; i++) {
                    row = sheet.getRow(i);
                    StringBuilder a = new StringBuilder();
                    a.append("  ").append(row.getCell(0)).append(" ").append(row.getCell(1));
                    if (!("y".equalsIgnoreCase(String.valueOf(row.getCell(2))))) {
                        a.append(" not null");
                    }
                    if (String.valueOf(row.getCell(3)) != null && String.valueOf(row.getCell(3)).length() != 0 && !String.valueOf(row.getCell(3)).equals("null")) {
                        a.append(" default ").append(row.getCell(3));
                    }
                    if (i == totalRow) {
                        a.append("\n)\ntablespace CENTERDBT\n" +
                                "  pctfree 10\n" +
                                "  initrans 1\n" +
                                "  maxtrans 255\n" +
                                "  storage\n" +
                                "  (\n" +
                                "    initial 384K\n" +
                                "    next 8K\n" +
                                "    minextents 1\n" +
                                "    maxextents unlimited\n" +
                                "  );");
//                        a.append("\n);");
                    } else {
                        a.append(",\n");
                    }
                    sql.append(a);
                }
                row = sheet.getRow(0);
                sql.append("\n-- Add comments to the table");
                sql.append("\ncomment on table ").append(table_name).append("\n  is '").append(row.getCell(5)).append("';");
                sql.append("\n-- Add comments to the columns");
                for (int i = 1; i <= totalRow; i++) {
                    row = sheet.getRow(i);
                    StringBuilder a = new StringBuilder();
                    a.append("\ncomment on column ").append(table_name).append(".").append(row.getCell(0)).append("\n  is '").append(row.getCell(4)).append("';");
                    sql.append(a);
                }
                sql.append("\n-- Create/Recreate primary, unique and foreign key constraints");
                sql.append("\nalter table ").append(table_name);
                sql.append("\n  add constraint PK_").append(table_name).append(" primary key (ID)");
                sql.append("\n  using index \n" +
                        "  tablespace CENTERDBT\n" +
                        "  pctfree 10\n" +
                        "  initrans 2\n" +
                        "  maxtrans 255\n" +
                        "  storage\n" +
                        "  (\n" +
                        "    initial 64K\n" +
                        "    next 1M\n" +
                        "    minextents 1\n" +
                        "    maxextents unlimited\n" +
                        "  );");

                new SaveDataToFile().appendFile(storePath, sql.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return valueList;
    }

    public static void main(String[] args) throws Exception {
        String readurl = "C:\\迅雷下载\\1.xlsx";  //读取excel文件的路径
        String storeurl = "C:\\迅雷下载\\excel2sql.sql";                                           //保存sql命令的路径
        readExcel(readurl, storeurl);
    }
}
