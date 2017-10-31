package com.fussen.nlp.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class About extends JDialog {

	public About(JFrame parent) {
		super(parent, true);
		initComponents();
		pack();
		Rectangle parentBounds = parent.getBounds();
		Dimension size = getSize();
		int x = Math.max(0, parentBounds.x + (parentBounds.width -size.width) /2);
		int y = Math.max(0, parentBounds.y + (parentBounds.height - size.height) /2);
		setLocation(new Point(x, y));
	}


	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;
		
		mainPanel = new javax.swing.JPanel();
		copyrightTextArea = new javax.swing.JTextArea();
		closeButton = new javax.swing.JButton();
		
		getContentPane().setLayout(new java.awt.GridBagLayout());
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("About Synintent");
		mainPanel.setLayout(new java.awt.GridBagLayout());
		
		mainPanel.setBorder(new javax.swing.border.EmptyBorder(11, 11, 12, 12));
		copyrightTextArea.setBackground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
		copyrightTextArea.setColumns(25);
		copyrightTextArea.setLineWrap(true);
		copyrightTextArea.setEditable(false);
		copyrightTextArea.setRows(8);
		copyrightTextArea.setText("Synintent\n\nCopyright (c) 2017 Fussen Technology");
		copyrightTextArea.setBorder(null);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(24, 0, 24, 0);
		mainPanel.add(copyrightTextArea, gridBagConstraints);
		
		closeButton.setText("Close");
		closeButton.setMnemonic('C');
		closeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				closeButtonActionPerformed(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
		mainPanel.add(closeButton, gridBagConstraints);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		getContentPane().add(mainPanel, gridBagConstraints);
	}

	private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		setVisible(false);
		dispose();
	}
	
	private javax.swing.JButton closeButton;
	private javax.swing.JTextArea copyrightTextArea;
	private javax.swing.JPanel mainPanel;
}