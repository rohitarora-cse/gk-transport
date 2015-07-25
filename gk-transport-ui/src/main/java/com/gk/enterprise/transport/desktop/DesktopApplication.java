package com.gk.enterprise.transport.desktop;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.velocity.exception.VelocityException;

import com.gk.enterprise.transport.manager.ServiceManager;

/**
 * @author rohit
 *
 */
public class DesktopApplication {
	
	private ServiceManager serviceManager = new ServiceManager();

	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel selectedFileLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JButton processButton;
	
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
		controlPanel.setLayout(new GridLayout(2, 1));

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	private void run() {
		JButton okButton = new JButton("Open File");
		okButton.setActionCommand("Open File");
		okButton.addActionListener(new ButtonClickListener());

		processButton = new JButton("Process File");
		processButton.setActionCommand("Process File");
		processButton.addActionListener(new ButtonClickListener());
		processButton.setEnabled(false);

		JPanel fileSelector = new JPanel();
		fileSelector.setLayout(new FlowLayout());

		JPanel processContainer = new JPanel();
		processContainer.setLayout(new FlowLayout());

		fileSelector.add(okButton);
		fileSelector.add(selectedFileLabel);
		processContainer.add(processButton);
		
		controlPanel.add(fileSelector);
		controlPanel.add(processContainer);

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
			processButton.setEnabled(true);
			selectedFileLabel.setText(selectedFile.getAbsolutePath());
			statusLabel.setText("File Selected :" + selectedFile.getName());
		} else {
			processButton.setEnabled(false);
			selectedFileLabel.setText("No File Selected");
			statusLabel.setText("Operation cancelled by user.");
		}
	}

	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("Open File")) {
				chooseExcelFile();
			} else if (command.equals("Process File")) {
				processButton.setEnabled(false);
				statusLabel.setText("Processing the selected file.");
				Runnable processExcelFile = new Runnable() {
					@Override
					public void run() {
						try {
							serviceManager.sendEmailsAndSmsFromExcelFile(selectedFile);
							statusLabel.setText("Email sent successfully.");
						} catch (FileNotFoundException e1) {
							statusLabel.setText("File Not Found.");
							e1.printStackTrace();
						} catch (IOException e1) {
							statusLabel.setText("Invalid Data in Excel File.");
							e1.printStackTrace();
						} catch (VelocityException e1) {
							statusLabel.setText("Velocity Engine Failed.");
							e1.printStackTrace();
						} catch (MessagingException e1) {
							statusLabel.setText("Unable to sent mail, Probabaly Network issue.");
							e1.printStackTrace();
						} finally {
							processButton.setEnabled(true);
						}						
					}
				};
				Thread processExcelFileThread = new Thread(processExcelFile);
				processExcelFileThread.start();
			} else {
				statusLabel.setText("Unknow Operation Selected.");
			}
		}
	}
}
