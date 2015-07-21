package com.gk.enterprise.transport.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.gk.enterprise.transport.bean.ExcelDocumentRow;
import com.gk.enterprise.transport.listener.ExcelDocumentRowListener;
import com.gk.enterprise.transport.service.ExcelServiceImpl;


/**
 * @author rohit
 *
 */
public class ServiceManager implements ExcelDocumentRowListener{

	private ExcelServiceImpl excelService = new ExcelServiceImpl();
	
	public void sendEmailsAndSmsFromExcelFile(File excelFile) throws FileNotFoundException, IOException {
		excelService.processFile(excelFile, this);
	}

	@Override
	public void processRow(ExcelDocumentRow row) {
		System.out.println(row);
	}
}
