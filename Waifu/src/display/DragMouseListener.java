package display;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import core.Serializer;
import core.Settings;

//@ Project			: ProjectWaifu
//@ File Name		: DragMouseListener.java
//@ Date			: 2013.07.02.
//@ Author			: csiki
//@ Copyright		: All rights reserved

class DragMouseListener implements MouseListener, MouseMotionListener {
	JFrame target;
	Point start_drag;
	Point start_loc;
	Settings settings;

	public DragMouseListener(JFrame target, Settings settings) {
		this.target = target;
		this.settings = settings;
	}

	Point getScreenLocation(MouseEvent e) {
		Point cursor = e.getPoint();
		Point target_location = this.target.getLocationOnScreen();
		return new Point((int) (target_location.getX() + cursor.getX()),
		(int) (target_location.getY() + cursor.getY()));
	}

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {
		this.start_drag = this.getScreenLocation(e);
		this.start_loc = this.target.getLocation();
	}

	public void mouseReleased(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {
		Point current = this.getScreenLocation(e);
		Point offset = new Point((int) current.getX() - (int) start_drag.getX(),
				(int) current.getY() - (int) start_drag.getY());
		Point new_location = new Point(
				(int) (this.start_loc.getX() + offset.getX()), (int) (this.start_loc
						.getY() + offset.getY()));
		target.setLocation(new_location);
		
		// change settings
		settings.setPosX(new_location.x);
		settings.setPosY(new_location.y);
				
		// serialize settings
		Serializer.serialize(null, settings, "settings.waifu");
	}

	public void mouseMoved(MouseEvent e) {}
}
