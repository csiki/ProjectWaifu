package display;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SkinOptionsSkinPanel extends JPanel {

	private static final long serialVersionUID = 7052547593473235538L;
	
	private BufferedImage skinImg;
	private BufferedImage noImg;
	
	public SkinOptionsSkinPanel(String path, BufferedImage noImg) {
		this.noImg = noImg;
		this.setSkin(path);
		this.setPreferredSize(new Dimension(240, 320));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		this.setOpaque(false);
		
		Graphics2D g2 = (Graphics2D) g;
		
		if (this.skinImg != null) {
			g2.drawImage(this.skinImg, 0, 0, 240, 320, 0, 0, this.skinImg.getWidth(), this.skinImg.getHeight(), null);
		}
		else {
			g2.drawImage(this.noImg, 0, 0, 240, 320, 0, 0, this.noImg.getWidth(), this.noImg.getHeight(), null);
		}
	}
	
	public void setSkin(String path) {
		
		this.skinImg = null;
		if (path != null) {
			File skinImgFile = new File(path);
			
			if (skinImgFile.canRead()) {
				try {
					this.skinImg = ImageIO.read(skinImgFile);
				} catch (IOException e) {
					this.skinImg = null;
				}
			}
		}
		
		this.repaint();
	}
	
}
