package com.gk.enterprise.transport.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.mail.MessagingException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.velocity.exception.VelocityException;

import com.gk.enterprise.transport.bean.ExcelDocumentRow;
import com.gk.enterprise.transport.listener.ExcelDocumentRowListener;

public class ExcelServiceImpl {

	public void processFile(File file, ExcelDocumentRowListener processor) throws FileNotFoundException, IOException, VelocityException, MessagingException{
		Workbook workbook = null;
		if(file.getName().endsWith("xls")) {
			workbook = new HSSFWorkbook(new FileInputStream(file));
		} else if(file.getName().endsWith("xlsx")) {
			workbook = new XSSFWorkbook(new FileInputStream(file));
		}
		Sheet sheet = workbook.getSheetAt(0);
		ExcelDocumentRow documentRow;
		
		Iterator<Row> rowIterator = sheet.rowIterator();
		
		//Skipping first row because it contains headings.
		if(rowIterator.hasNext()) {
			rowIterator.next();
		}
		while(rowIterator.hasNext()) {
			documentRow = new ExcelDocumentRow();
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()) {
				cellIterator.next().setCellType(Cell.CELL_TYPE_STRING);
			}
			documentRow.setDocumentId(row.getCell(0).getStringCellValue());
			documentRow.setEmailIds(row.getCell(1).getStringCellValue());
			documentRow.setPhoneNumbers(row.getCell(2).getStringCellValue());
			
			processor.processRow(documentRow);
		}
		
	}
}
