package com.gk.enterprise.transport.bean;

import java.util.Arrays;

/**
 * @author rohit
 *
 */
public class DocumentBean {

	private String documentId;
	private String[] emailIds;
	private String[] phoneNumbers;

	/**
	 * @return the documentId
	 */
	public String getDocumentId() {
		return documentId;
	}

	/**
	 * @param documentId
	 *            the documentId to set
	 */
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
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

	/**
	 * @return the phoneNumbers
	 */
	public String[] getPhoneNumbers() {
		return phoneNumbers;
	}

	/**
	 * @param phoneNumbers
	 *            the phoneNumbers to set
	 */
	public void setPhoneNumbers(String[] phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DocumentBean [documentId=" + documentId + ", emailIds="
				+ Arrays.toString(emailIds) + ", phoneNumbers="
				+ Arrays.toString(phoneNumbers) + "]";
	}

}
