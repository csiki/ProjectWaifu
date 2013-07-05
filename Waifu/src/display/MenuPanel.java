package display;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import main.WaifuBuilder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//  @ Project		: ProjectWaifu
//  @ File Name		: MenuPanel.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -951173760082267556L;
	
	private BufferedImage settingsIcon;
    private BufferedImage exitIcon;
    private WaifuBuilder exitInterface;
    private JPopupMenu popupMenu;
    
    private CustomMenuItem skinOpMI;
    private CustomMenuItem settingsMI;
    private CustomMenuItem aiOpMI;
    
    SkinOptionsDialog skinOptionsDialog;
    AIOptionsDialog aiOptionsDialog;
    SettingsDialog settingsDialog;
    
    public MenuPanel(BufferedImage settingsIcon, BufferedImage exitIcon, WaifuBuilder exitInterface,
    		final SkinOptionsDialog skinOptionsDialog, final AIOptionsDialog aiOptionsDialog, final SettingsDialog settingsDialog) {
    	this.settingsIcon = settingsIcon;
    	this.exitIcon = exitIcon;
    	this.exitInterface = exitInterface;
    	this.skinOptionsDialog = skinOptionsDialog;
		this.aiOptionsDialog = aiOptionsDialog;
		this.settingsDialog = settingsDialog;
    	
    	this.setPreferredSize(new Dimension(300, 200)); // TODO set to sizing
    	
    	// popupmenu
    	this.popupMenu = new JPopupMenu();
    	this.skinOpMI = new CustomMenuItem("Skin options");
    	this.settingsMI = new CustomMenuItem("Settings..");
    	this.aiOpMI = new CustomMenuItem("A.I. options");
    	
    	this.popupMenu.add(this.skinOpMI);
    	this.popupMenu.add(this.settingsMI);
    	this.popupMenu.add(this.aiOpMI);
    	
    	// popupmenu elements mouse listeners
    	this.skinOpMI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				skinOptionsDialog.showD();
			}
		});
    	
    	this.settingsMI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				settingsDialog.showD();
			}
		});
    	
    	this.aiOpMI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				aiOptionsDialog.showD();
			}
		});
    	
    	// mouse listener
    	addMouseListener(new MenuMouseListener(this, settingsIcon, exitIcon));
    }
    
    public void exitClicked() {
    	// TODO
    	this.exitInterface.exit();
    }
    
    public void wrenchClicked() {
    	this.popupMenu.show(this, 0, this.settingsIcon.getHeight());
    	this.popupMenu.repaint();
    }
    
    @Override
	public void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	
    	// draw icons
    	g2.drawImage(settingsIcon,
    			0, 0, settingsIcon.getWidth(), settingsIcon.getHeight(),
    			0, 0, settingsIcon.getWidth(), settingsIcon.getHeight(), null);
    	g2.drawImage(exitIcon,
    			settingsIcon.getWidth() + 5, 0, exitIcon.getWidth() + settingsIcon.getWidth() + 5, exitIcon.getHeight(),
    			0, 0, exitIcon.getWidth(), exitIcon.getHeight(), null);
    }
}
