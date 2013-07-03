package core;

import de.ksquared.system.keyboard.GlobalKeyListener;
import de.ksquared.system.keyboard.KeyAdapter;
import de.ksquared.system.keyboard.KeyEvent;

//  @ Project		: ProjectWaifu
//  @ File Name		: KeyReader.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class KeyReader extends Sensor{
	
	volatile private int keyTyped;
	
    public KeyReader(String name) {
		super(name);
		this.keyTyped = 0;
		
		// already runs on a different thread
		GlobalKeyListener gkl = new GlobalKeyListener();
		gkl.addKeyListener(new KeyAdapter() {
			@Override public void keyReleased(KeyEvent event) {
				int keyCode = event.getVirtualKeyCode();
				if (turnedOn && keyCode >= 65 && keyCode <= 90) {
					keyTyped = keyCode;
					notifyAllSubs();
				}
			}
    	});
	}
    
    public int getKeyTyped() {
    	return this.keyTyped;
    }
    
    public void on() {
    	this.turnedOn = true;
    }
    
    public void off() {
    	this.turnedOn = false;
    }
}
