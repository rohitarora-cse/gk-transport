package com.gk.enterprise.transport.listener;

import com.gk.enterprise.transport.bean.ExcelDocumentRow;

public interface ExcelDocumentRowListener {

	public void processRow(ExcelDocumentRow row);
}
