package com.gk.enterprise.transport.bean;

/**
 * @author rohit
 *
 */
public class EmailBean {

	private String emailContent;
	private String[] emailIds;

	/**
	 * @return the emailContent
	 */
	public String getEmailContent() {
		return emailContent;
	}

	/**
	 * @param emailContent
	 *            the emailContent to set
	 */
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

	/**
	 * @return the emailIds
	 */
	public String[] getEmailIds() {
		return emailIds;
	}

	/**
	 * @param emailIds
	 *            the emailIds to set
	 */
	public void setEmailIds(String[] emailIds) {
		this.emailIds = emailIds;
	}

}
