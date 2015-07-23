package com.gk.enterprise.transport.listener;

import java.io.IOException;

import org.apache.velocity.exception.VelocityException;

import com.gk.enterprise.transport.bean.ExcelDocumentRow;

public interface ExcelDocumentRowListener {

	public void processRow(ExcelDocumentRow row) throws VelocityException, IOException;
}
