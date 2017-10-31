package com.fussen.nlp.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.fussen.nlp.util.*;

public class MainGUI extends JFrame {

	public static void main(String[] args) {
		/*
		String[] strs = {"are", "bat", "ear", "code", "tab", "era"};
		System.out.println(StringUtil.groupAnagrams(strs));
		*/
		
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainGUI().setVisible(true);
			}
		});
		
	}
	
	public MainGUI() {
		initComponents();
		getRootPane().setDefaultButton(analysisButton);
		pack();
		inputSentence.requestFocusInWindow();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		setLocation(new Point((screenSize.width - frameSize.width) /2,
							 (screenSize.height - frameSize.width) /2));
	}
	
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;
		
		mainPanel = new javax.swing.JPanel();
		mainMenu = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		aboutMenuItem = new javax.swing.JMenuItem();
		exitMenuItem = new javax.swing.JMenuItem();
		inputLabel = new javax.swing.JLabel();
		inputSentence = new javax.swing.JTextField();
		resultsLabel = new javax.swing.JLabel();
		resultsField = new javax.swing.JTextField();
		buttonsPanel = new javax.swing.JPanel();
		analysisButton = new javax.swing.JButton();
		synthesisButton = new javax.swing.JButton();
		
		setTitle("SynIntent");
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});
		mainPanel.setLayout(new java.awt.GridBagLayout());
		mainPanel.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(12, 12, 20, 20)));
		//mainPanel.setMinimumSize(new java.awt.Dimension(200, 200));
		
		inputLabel.setText("Input Sentence:");
		inputLabel.setLabelFor(inputSentence);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 6);
		mainPanel.add(inputLabel, gridBagConstraints);
		
		inputSentence.setColumns(40);
		inputSentence.setEditable(true);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		//gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
		mainPanel.add(inputSentence, gridBagConstraints);
		
		resultsLabel.setText("Results:");
		resultsLabel.setLabelFor(resultsField);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 6);
		mainPanel.add(resultsLabel, gridBagConstraints);

		resultsField.setColumns(40);
		resultsField.setEditable(false);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 1.0;
		//gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 20, 0);
		mainPanel.add(resultsField, gridBagConstraints);

		buttonsPanel.setLayout(new java.awt.GridBagLayout());
		
		analysisButton.setMnemonic('A');
		analysisButton.setText("Analysis");
		analysisButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				analysisActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 6);
		buttonsPanel.add(analysisButton, gridBagConstraints);

		synthesisButton.setMnemonic('S');
		synthesisButton.setText("Synthesis");
		synthesisButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				synthesisActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
		gridBagConstraints.weighty = 1.0;
		buttonsPanel.add(synthesisButton, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
		gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weighty = 1.0;
		mainPanel.add(buttonsPanel, gridBagConstraints);
		
		getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);
		
		fileMenu.setMnemonic('F');
		fileMenu.setText("File");
		aboutMenuItem.setMnemonic('A');
		aboutMenuItem.setText("About");
		aboutMenuItem.setToolTipText("About");
		aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				aboutMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(aboutMenuItem);
		
		exitMenuItem.setMnemonic('E');
		exitMenuItem.setText("Exit");
		exitMenuItem.setToolTipText("Quit");
		exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitMenuItemActionPerformed(evt);
			}
		});
		fileMenu.add(exitMenuItem);
		mainMenu.add(fileMenu);
		setJMenuBar(mainMenu);

	}
	
	private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		new About(this).setVisible(true);
	}
	
	private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}
	
	private void exitForm(java.awt.event.WindowEvent evt) {
		System.exit(0);
	}
	
	private void analysisActionPerformed(java.awt.event.ActionEvent evt) {
		// update result text window
		String inputString = inputSentence.getText();
		resultsField.setText(inputString);
	}

	private void synthesisActionPerformed(java.awt.event.ActionEvent evt) {
		// update result text window
		String inputString = inputSentence.getText();
		String newstr = StringUtil.reverseSentence(inputString);
		resultsField.setText(newstr);
	}

	private javax.swing.JPanel mainPanel;
	private javax.swing.JMenuBar mainMenu;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JMenuItem aboutMenuItem;
	private javax.swing.JMenuItem exitMenuItem;
	private javax.swing.JLabel inputLabel;
	private javax.swing.JTextField inputSentence;
	private javax.swing.JLabel resultsLabel;
	private javax.swing.JTextField resultsField;
	private javax.swing.JPanel buttonsPanel;
	private javax.swing.JButton analysisButton;
	private javax.swing.JButton synthesisButton;
}
