package display;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import core.Serializer;
import core.SkinContainer;
import core.Emotion;
import core.Settings;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

//  @ Project		: ProjectWaifu
//  @ File Name		: SkinOptionsDialog.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


public class SkinOptionsDialog extends JDialog {
	
	private static final long serialVersionUID = -5947340462492700774L;
	
	private SkinContainer skinContainer;
	private List<String> skinsTemp;
	private List<SkinOptionsSkinPanel> skinPanels;
	private JFileChooser chooser = new JFileChooser( new File(".") )
	{
		private static final long serialVersionUID = 417509353185433544L;

		public void approveSelection()
	    {
	        if (getSelectedFile().isFile()) {
	        	
	        	// check extension
    			int i = getSelectedFile().getName().lastIndexOf('.');
    			if (i > 0) {
    			    String extension = getSelectedFile().getName().substring(i+1);
    			    
    			    if (extension.equals("png")) {
    			    	super.approveSelection();
    			    }
    			}
	        }
	    }
	};
	
	private final JPanel buttonPane = new JPanel();
	private final JButton cancelButton = new JButton("Cancel");
	private final JButton okButton = new JButton("OK");
	private final Action okAction = new OkAction();
	private final Action cancelAction = new CancelAction();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JPanel skinsPanel = new JPanel();
	
	public SkinOptionsDialog(SkinContainer skinContainer, JFrame parent) {
		super(parent, true);
		this.skinContainer = skinContainer;
		this.skinsTemp = new ArrayList<String>();
		this.skinPanels = new ArrayList<SkinOptionsSkinPanel>();
		
		// copy skins to skinsTemp
		for (int i = 0; i < Settings.maxNumOfSkins; ++i) {
			this.skinsTemp.add(this.skinContainer.getSkin(i));
		}
		
		// fill skinPanels
		BufferedImage noWaifuImg = null;
		try {
			noWaifuImg = ImageIO.read(new File("img" + java.io.File.separator + "nowaifu.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < Settings.maxNumOfSkins; ++i) {
			this.skinPanels.add(new SkinOptionsSkinPanel(this.skinsTemp.get(i), noWaifuImg));
		}
		
		init();
	}
	
	public void init() {
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Skin options");
		this.setBounds(0, 0, 800, 460);
		this.setResizable(false);
		
		// clear
		this.okButton.setEnabled(true);
		this.cancelButton.setEnabled(true);
		buttonPane.setOpaque(false);
		buttonPane.setBackground(new Color (0, 0, 0, 0));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		GridBagConstraints gbc_buttonPane = new GridBagConstraints();
		gbc_buttonPane.anchor = GridBagConstraints.NORTH;
		gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_buttonPane.gridx = 0;
		gbc_buttonPane.gridy = 1;
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		okButton.setAction(okAction);
				
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		cancelButton.setAction(cancelAction);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(skinsPanel);
		GridBagLayout gbl_skinsPanel = new GridBagLayout();
		gbl_skinsPanel.columnWidths = new int[]{0, 0};
		gbl_skinsPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_skinsPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_skinsPanel.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		skinsPanel.setLayout(gbl_skinsPanel);
		
		// add skin options
		int i = 0;
		for (Emotion em : Emotion.values()) {
			
			// skin panel
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = i;
			gbc_panel.gridy = 0;
			skinsPanel.add(this.skinPanels.get(i), gbc_panel);
			
			// emotion label
			GridBagConstraints gbc_lblEmotion = new GridBagConstraints();
			gbc_lblEmotion.insets = new Insets(0, 0, 5, 0);
			gbc_lblEmotion.gridx = i;
			gbc_lblEmotion.gridy = 1;
			skinsPanel.add(new JLabel(em.toString()), gbc_lblEmotion);
			
			// change button
			JButton jb = new JButton("Change skin");
			jb.setAction(new ChangeSkinAction(i));
			GridBagConstraints gbc_btnChangeSkin = new GridBagConstraints();
			gbc_btnChangeSkin.gridx = i;
			gbc_btnChangeSkin.gridy = 2;
			skinsPanel.add(jb, gbc_btnChangeSkin);
			
			++i;
		}
	}
    
	public void showD() {
		this.okButton.setEnabled(true);
		this.cancelButton.setEnabled(true);
		
		for (int i = 0; i < Settings.maxNumOfSkins; ++i) {
			
			String set = this.skinContainer.getSkin(i);
			// restore skinsTemp
			this.skinsTemp.set(i, set);
			
			// set skinPanels
			this.skinPanels.get(i).setSkin(set);
 		}
		
		this.setVisible(true);
	}
	
	private class ChangeSkinAction extends AbstractAction {

		private static final long serialVersionUID = -4001330188135587471L;
		
		private int nth;
		
		ChangeSkinAction(int nth) {
			this.nth = nth;
			putValue(NAME, "Change skin");
			putValue(SHORT_DESCRIPTION, "Specify your own skins!");
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int chooseResult = chooser.showDialog(getParent(), "Choose a picture");
			
			if (chooseResult == JFileChooser.APPROVE_OPTION) {
				
				String path = null;
				try {
					path = chooser.getSelectedFile().getCanonicalPath();
				} catch (IOException e) {
					return;
				}
				
				skinsTemp.set(nth, path);
				skinPanels.get(nth).setSkin(path);
			}
		}
		
	}
	
	private class OkAction extends AbstractAction {

		private static final long serialVersionUID = 4623275957298708592L;

		public OkAction() {
			putValue(NAME, "Ok");
			putValue(SHORT_DESCRIPTION, "Inform your Waifu!");
		}
		
		public void actionPerformed(ActionEvent e) {
			this.setEnabled(false);
			
			// update skin container
			for (int i = 0; i < Settings.maxNumOfSkins; ++i) {
				skinContainer.addSkin(i, skinsTemp.get(i));
			}
			
			// serialize skin container
			Serializer.serialize(null, skinContainer, "skincontainer.waifu");
			
			dispose();
		}
	}
	
	private class CancelAction extends AbstractAction {
		
		private static final long serialVersionUID = -6470808910237258184L;

		public CancelAction() {
			putValue(NAME, "Cancel");
			putValue(SHORT_DESCRIPTION, ":(");
		}
		
		public void actionPerformed(ActionEvent e) {
			this.setEnabled(false);
			dispose();
		}
	}
	
}
