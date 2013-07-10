package display;

//@ Project			: ProjectWaifu
//@ File Name		: CheckBoxDialog.java
//@ Date			: 2013.07.02.
//@ Author			: csiki
//@ Copyright		: All rights reserved

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.JCheckBox;
import core.Settings;

public class CheckBoxDialog extends JDialog {
	
	private static final long serialVersionUID = -6641501166870458281L;
	
	private List<String> options;
	
	private final JPanel buttonPane = new JPanel();
	private final JPanel checkBoxPane = new JPanel();
	private final JButton cancelButton = new JButton("Cancel");
	private final JButton okButton = new JButton("OK");
	
	private int result = -1;
	private Settings settings;
	private final Action okAction = new OkAction();
	private final Action cancelAction = new CancelAction();
	
	private List<JCheckBox> checkBoxes;
	
	public int getResult() {
		return this.result;
	}
	
	public void addOptions(List<String> options) {
    	
		if (options.size() == 0) {
			return;
		}
		
		this.options = options;
    	this.checkBoxes = new ArrayList<JCheckBox>();
    	this.checkBoxPane.removeAll();
    	getContentPane().removeAll();
    	
    	JCheckBox tmp;
    	for (String op : options) {
    		tmp = new JCheckBox(op);
    		tmp.setBackground(Color.WHITE);
    		checkBoxes.add(tmp);
    	}
    }
	
	public List<Integer> getResponse() {
		
		if (result == -1 || result == JOptionPane.CANCEL_OPTION || this.options == null) {
			return null;
		}
		
		List<Integer> response = new ArrayList<Integer>();
		
		int i = 0;
		for (JCheckBox cb : this.checkBoxes) {
			if (cb.isSelected()) {
				response.add(i);
			}
			++i;
		}
    	
    	return response;
    }

	/**
	 * Create the dialog.
	 */
	public CheckBoxDialog(Settings settings, JFrame parent) {
		super(parent, true);
		this.settings = settings;
		this.options = null;
	}
	
	public void init() {
		
		if (this.options == null) {
			return; // no options added
		}
		
		setBounds(this.settings.getPosX() + 90, this.settings.getPosY() + 80, 150, 150);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.getRootPane().setOpaque(false);
		this.setUndecorated(true);
		this.getContentPane().setBackground(new Color (0, 0, 0, 0));
		this.setBackground(new Color (0, 0, 0, 0));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		getContentPane().setLayout(gridBagLayout);
		
		// check box panel
		GridBagLayout checkPanegridBagLayout = new GridBagLayout();
		checkBoxPane.setLayout(checkPanegridBagLayout);
		checkBoxPane.setOpaque(false);
		checkBoxPane.setBackground(new Color (0, 0, 0, 0));
		checkBoxPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		GridBagConstraints gbc_checkBoxPane = new GridBagConstraints();
		gbc_checkBoxPane.anchor = GridBagConstraints.NORTH;
		gbc_checkBoxPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_checkBoxPane.gridx = 1;
		gbc_checkBoxPane.gridy = 1;
		getContentPane().add(checkBoxPane, gbc_checkBoxPane);
		
		// checkbox grids
		int y = 0;
		
		for (JCheckBox cb : this.checkBoxes) {
			cb.setBorderPainted(true);
			cb.setMargin(new Insets(3, 3, 3, 3));
			GridBagConstraints gridBag = new GridBagConstraints();
			gridBag.insets = new Insets(0, 0, 5, 0);
			gridBag.gridx = 0;
			gridBag.gridy = y;
			checkBoxPane.add(cb, gridBag);
			++y;
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
		setVisible(true);
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
