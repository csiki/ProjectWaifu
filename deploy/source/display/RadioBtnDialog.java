package display;

//@ Project			: ProjectWaifu
//@ File Name		: RadioBtnDialog.java
//@ Date			: 2013.07.02.
//@ Author			: csiki
//@ Copyright		: All rights reserved

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import core.Settings;

public class RadioBtnDialog extends JDialog {

	private static final long serialVersionUID = -6641501166870458281L;
	
	private List<String> options;
	private JFrame parentFrame;
	
	private final JPanel buttonPane = new JPanel();
	private final JPanel radioBtnPane = new JPanel();
	private final JButton cancelButton = new JButton("Cancel");
	private final JButton okButton = new JButton("OK");
	
	private int result = -1;
	private Settings settings;
	private final Action okAction = new OkAction();
	private final Action cancelAction = new CancelAction();
	private ButtonGroup buttonGroup;
	
	private List<JRadioButton> radioBtns;
	
	public int getResult() {
		return this.result;
	}
	
	public void addOptions(List<String> options) {
    	
		if (options.size() == 0) {
			return;
		}
		
		this.options = options;
    	this.radioBtns = new ArrayList<JRadioButton>();
    	this.buttonGroup = new ButtonGroup();
    	getContentPane().removeAll();
    	this.radioBtnPane.removeAll();
    	
    	JRadioButton tmp;
    	for (String op : options) {
    		tmp = new JRadioButton(op);
    		tmp.setBackground(Color.WHITE);
    		radioBtns.add(tmp);
    		this.buttonGroup.add(tmp);
    	}
    	
    	this.radioBtns.get(0).setSelected(true);
    }
	
	public int getResponse() {
		
		if (result == -1 || result == JOptionPane.CANCEL_OPTION || this.options == null) {
			return -1;
		}
		
		int i = 0;
		for (JRadioButton rb : this.radioBtns) {
			if (rb.isSelected()) {
				return i;
			}
			++i;
		}
    	
    	return -1;
    }

	/**
	 * Create the dialog.
	 */
	public RadioBtnDialog(Settings settings, JFrame parentFrame) {
		super(parentFrame, true);
		this.parentFrame = parentFrame;
		this.settings = settings;
		this.options = null;
	}
	
	public void init() {
		
		if (this.options == null) {
			return; // no options added
		}
		
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.getRootPane().setOpaque(false);
		this.setUndecorated(true);
		this.getContentPane().setBackground(new Color (0, 0, 0, 0));
		this.setBackground(new Color (0, 0, 0, 0));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		getContentPane().setLayout(gridBagLayout);
		
		// radio btn panel
		GridBagLayout checkPanegridBagLayout = new GridBagLayout();
		radioBtnPane.setLayout(checkPanegridBagLayout);
		radioBtnPane.setOpaque(false);
		radioBtnPane.setBackground(new Color (0, 0, 0, 0));
		radioBtnPane.setLayout(new GridBagLayout());
		GridBagConstraints gbc_checkBoxPane = new GridBagConstraints();
		gbc_checkBoxPane.anchor = GridBagConstraints.NORTH;
		gbc_checkBoxPane.fill = GridBagConstraints.BOTH;
		gbc_checkBoxPane.gridx = 1;
		gbc_checkBoxPane.gridy = 1;
		getContentPane().add(radioBtnPane, gbc_checkBoxPane);
		
		// radio btn grids
		int x = 0;
		int y = 0;
		int numOfCharsInRow = 0;
		
		for (JRadioButton rb : this.radioBtns) {
			
			if (numOfCharsInRow > 20 || x == 5) {
				x = 0;
				++y;
				numOfCharsInRow = 0;
			}
			numOfCharsInRow += rb.getText().length();
			
			rb.setBorderPainted(true);
			rb.setMargin(new Insets(3, 3, 3, 3));
			GridBagConstraints gridBag = new GridBagConstraints();
			gridBag.insets = new Insets(0, 0, 5, 0);
			gridBag.gridx = x;
			gridBag.gridy = y;
			radioBtnPane.add(rb, gridBag);

			++x;
		}
		
		// clear
		this.okButton.setEnabled(true);
		this.cancelButton.setEnabled(true);
		
		buttonPane.setOpaque(false);
		buttonPane.setBackground(new Color (0, 0, 0, 0));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		GridBagConstraints gbc_buttonPane = new GridBagConstraints();
		gbc_buttonPane.anchor = GridBagConstraints.NORTH;
		gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPane.gridx = 1;
		gbc_buttonPane.gridy = 2;
		getContentPane().add(buttonPane, gbc_buttonPane);
		okButton.setAction(okAction);
		
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		cancelButton.setAction(cancelAction);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		this.pack();
		
		// position
		int offsetX = (this.settings.getCurrentSizing().cloudWidth - this.getSize().width) / 2;
		int offsetY = (this.settings.getCurrentSizing().cloudHeight - this.getSize().height) / 2;
		
		setBounds(parentFrame.getLocation().x + this.settings.getCurrentSizing().offsetInputDialogBoxX + offsetX,
				parentFrame.getLocation().y + this.settings.getCurrentSizing().offsetInputDialogBoxY + offsetY,
				this.getSize().width, this.getSize().height);
		
		setVisible(true);
	}
	
	@Override
	public void paintComponents(Graphics g) {
		// position
		int offsetX = (this.settings.getCurrentSizing().cloudWidth - this.getSize().width) / 2;
		int offsetY = (this.settings.getCurrentSizing().cloudHeight - this.getSize().height) / 2;
				
		setBounds(parentFrame.getLocation().x + this.settings.getCurrentSizing().offsetInputDialogBoxX + offsetX,
				parentFrame.getLocation().y + this.settings.getCurrentSizing().offsetInputDialogBoxY + offsetY,
				this.getSize().width, this.getSize().height);
	}

	private class OkAction extends AbstractAction {

		private static final long serialVersionUID = 2718147188036426104L;

		public OkAction() {
			putValue(NAME, "Ok");
			putValue(SHORT_DESCRIPTION, "Inform your Waifu!");
		}
		
		public void actionPerformed(ActionEvent e) {
			this.setEnabled(false);
			result = JOptionPane.YES_OPTION;
			dispose();
		}
	}
	
	private class CancelAction extends AbstractAction {

		private static final long serialVersionUID = 5132265699678512529L;
		
		public CancelAction() {
			putValue(NAME, "Cancel");
			putValue(SHORT_DESCRIPTION, ":(");
		}
		
		public void actionPerformed(ActionEvent e) {
			this.setEnabled(false);
			result = JOptionPane.CANCEL_OPTION;
			dispose();
		}
	}
}
