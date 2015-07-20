package com.gk.enterprise.transport.desktop;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import my.cool.lib.ExcelUtil;

/**
 * @author rohit
 *
 */
public class DesktopApplication {
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel selectedFileLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	private File selectedFile;
	
	public static void main(String[] args) {
		DesktopApplication application = new DesktopApplication();
		application.run();
	}

	public DesktopApplication() {
		prepareGUI();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("Send Email and SMS");
		mainFrame.setSize(600, 600);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		headerLabel = new JLabel("", JLabel.CENTER);
		headerLabel.setText("Application to send Email and SMS");
		statusLabel = new JLabel("", JLabel.CENTER);
		statusLabel.setSize(350, 100);
		selectedFileLabel = new JLabel("No File Selected", JLabel.CENTER);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	private void run() {
		JButton okButton = new JButton("Open File");
		okButton.setActionCommand("Open File");
		okButton.addActionListener(new ButtonClickListener());

		JButton processButton = new JButton("Process File");
		processButton.setActionCommand("Process File");
		processButton.addActionListener(new ButtonClickListener());

		controlPanel.add(okButton);
		controlPanel.add(selectedFileLabel);
		controlPanel.add(processButton);

		mainFrame.setVisible(true);
	}
	
	private void chooseExcelFile() {
		final JFileChooser fileDialog = new JFileChooser();
		fileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
		//fileDialog.setAcceptAllFileFilterUsed(false);
		fileDialog.setFileFilter(new FileNameExtensionFilter("Excel Documents", "xlsx", "xls"));
		
		int returnVal = fileDialog.showOpenDialog(mainFrame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileDialog.getSelectedFile();
			selectedFileLabel.setText(selectedFile.getAbsolutePath());
			statusLabel.setText("File Selected :" + selectedFile.getName());
		} else {
			statusLabel.setText("Operation cancelled by user.");
		}
	}

	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("Open File")) {
				chooseExcelFile();
			} else if (command.equals("Process File")) {
				statusLabel.setText("Processing the selected file.");
				ExcelUtil.sendEmailsFromExcelFile(selectedFile);
				statusLabel.setText("Email sent successfully.");
			} else {
				statusLabel.setText("Unknow Operation Selected.");
			}
		}
	}
}
