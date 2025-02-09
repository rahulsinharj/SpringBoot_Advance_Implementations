package com.toDownloadExcel.helper;

import com.toDownloadExcel.model.Category;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelHelper {

    public static String[] HEADERS = {
            "id",
            "title",
            "description",
            "coverImage"
    };

    public static String SHEET_NAME="category_data_sheet1";

    public static ByteArrayInputStream dataToExcel(List<Category> list) {
        // To convert data into Excel sheet, we require return type as "ByteArrayInputStream"
        // Create a Workbook
        Workbook workbook = new SXSSFWorkbook();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            // Create Sheet
            Sheet sheet = workbook.createSheet(SHEET_NAME);

            // Create Row : Header Row
            Row row = sheet.createRow(0);

            for(int i=0; i<HEADERS.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(HEADERS[i]);
            }

            // Value rows
            int rowIndex = 1;
            for(Category c : list)
            {
                Row dataRow = sheet.createRow(rowIndex);

                dataRow.createCell(0).setCellValue(c.getCategoryId());
                dataRow.createCell(1).setCellValue(c.getTitle());
                dataRow.createCell(2).setCellValue(c.getDescription());
                dataRow.createCell(3).setCellValue(c.getCoverImage());

                rowIndex++;
            }

            workbook.write(outputStream);

            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            System.out.println("Failed to import data into excel due to IO Exception");
            e.printStackTrace();
            return null;
        }
        catch (Exception e) {
            System.out.println("Failed to import data into excel");
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                workbook.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
