package display;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class MenuMouseListener implements MouseListener {
	
	private BufferedImage exitImg;
	private BufferedImage wrenchImg;
	private MenuPanel mp;
	
	private Dimension exitDim1;
	private Dimension exitDim2;
	private Dimension wrDim1;
	private Dimension wrDim2;
	
	public MenuMouseListener(MenuPanel mp, BufferedImage wrenchImg, BufferedImage exitImg) { // TODO helyette exit interface !
		super();
		this.mp = mp;
		this.wrenchImg = wrenchImg;
		this.exitImg = exitImg;
		
		this.wrDim1 = new Dimension(0,0);
		this.wrDim2 = new Dimension(this.wrenchImg.getWidth(), this.wrenchImg.getHeight());
		this.exitDim1 = new Dimension(this.wrenchImg.getWidth(), 0);
		this.exitDim2 = new Dimension(this.wrenchImg.getWidth() + this.exitImg.getWidth(), this.exitImg.getHeight());
	}
	
	private boolean isInInterval(MouseEvent arg, Dimension dim1, Dimension dim2) {
		if (arg.getX() >= dim1.getWidth()
				&& arg.getX() < dim2.getWidth()
				&& arg.getY() >= dim1.getHeight()
				&& arg.getY() < dim2.getHeight())
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// exit
		if (this.isInInterval(arg0, this.exitDim1, this.exitDim2)) {
			this.mp.exitClicked();
		}
		
		// wrench
		if (this.isInInterval(arg0, this.wrDim1, this.wrDim2)) {
			this.mp.wrenchClicked();
		}
	}
	
}
