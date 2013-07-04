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
	private GlobalKeyListener gkl;
	private KeyAdapter keyAdatper;
	
    public KeyReader(String name) {
		super(name);
		this.keyTyped = 0;
		
		// already runs on a different thread
		this.gkl = new GlobalKeyListener();
		this.keyAdatper = new KeyAdapter() {
			@Override public void keyReleased(KeyEvent event) {
				int keyCode = event.getVirtualKeyCode();
				if (turnedOn && keyCode >= 65 && keyCode <= 90) {
					keyTyped = keyCode;
					notifyAllSubs();
				}
			}
		};
		
		this.gkl.addKeyListener(this.keyAdatper);
	}
    
    public int getKeyTyped() {
    	return this.keyTyped;
    }
    
    @Override
    public void turnOff() {
    	this.turnedOn = false;
    	this.gkl.removeKeyListener(this.keyAdatper);
    	this.keyAdatper = null;
    	this.gkl = null;
    }
}
