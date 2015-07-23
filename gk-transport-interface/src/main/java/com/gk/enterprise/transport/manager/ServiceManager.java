package com.gk.enterprise.transport.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.velocity.exception.VelocityException;

import com.gk.enterprise.transport.bean.Client;
import com.gk.enterprise.transport.bean.EmailBean;
import com.gk.enterprise.transport.bean.ExcelDocumentRow;
import com.gk.enterprise.transport.listener.ExcelDocumentRowListener;
import com.gk.enterprise.transport.service.EmailServiceImpl;
import com.gk.enterprise.transport.service.ExcelServiceImpl;
import com.gk.enterprise.transport.service.VelocityServiceImpl;


/**
 * @author rohit
 *
 */
public class ServiceManager implements ExcelDocumentRowListener{

	private ExcelServiceImpl excelService = new ExcelServiceImpl();
	private VelocityServiceImpl velocityService = new VelocityServiceImpl();
	private EmailServiceImpl emailService = new EmailServiceImpl();
	
	public void sendEmailsAndSmsFromExcelFile(File excelFile) throws FileNotFoundException, IOException {
		excelService.processFile(excelFile, this);
	}

	@Override
	public void processRow(ExcelDocumentRow row) throws VelocityException, IOException {
		System.out.println(row);
		/*DocumentBean bean = new DocumentBean();
		bean.setDocumentId(row.getDocumentId());
		bean.setEmailIds(row.getEmailIds().split(";"));
		bean.setPhoneNumbers(row.getPhoneNumbers().split(";"));*/
		
		Client client = new Client();
		client.setDocumentId(row.getDocumentId());
		
		String emailContent = velocityService.templateToString(client);
		System.out.println(emailContent);
		
		EmailBean emailBean = new EmailBean();
		emailBean.setEmailContent(emailContent);
		emailBean.setEmailIds(row.getEmailIds().split(";"));
		
		emailService.sendEmail(emailBean);
	}
}
