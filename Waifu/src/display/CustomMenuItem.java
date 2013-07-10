package display;

//@ Project			: ProjectWaifu
//@ File Name		: CustomMenuItem.java
//@ Date			: 2013.07.02.
//@ Author			: csiki
//@ Copyright		: All rights reserved

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JMenuItem;
import core.Sizing;

public class CustomMenuItem extends JMenuItem {
	
	private static final long serialVersionUID = -8263172990363240210L;
	
	public CustomMenuItem(Sizing sizing, String name) {
		super(name);
		this.setPreferredSize(new Dimension(sizing.menuElementWidth, sizing.menuElementHeight));
		this.setBackground(Color.WHITE);
		this.setForeground(new Color(15, 15, 15));
		this.setFont(new Font("Arial", 0, sizing.menuElementFontSize));
	}

}
