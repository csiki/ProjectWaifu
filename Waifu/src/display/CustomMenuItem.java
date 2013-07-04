package display;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JMenuItem;

import core.Settings;

public class CustomMenuItem extends JMenuItem {
	
	private static final long serialVersionUID = -8263172990363240210L;
	
	public CustomMenuItem(String name) {
		super(name);
		this.setBackground(Color.WHITE);
		this.setForeground(new Color(15, 15, 15));
		this.setFont(new Font("Calibri", 0, Settings.menuElementFontSize));
	}

}
