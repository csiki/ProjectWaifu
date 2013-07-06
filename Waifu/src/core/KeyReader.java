package core;

import de.ksquared.system.keyboard.GlobalKeyListener;
import de.ksquared.system.keyboard.KeyAdapter;
import de.ksquared.system.keyboard.KeyEvent;

//  @ Project		: ProjectWaifu
//  @ File Name		: KeyReader.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class KeyReader extends Sensor {
	
	volatile private int keyTyped;
	private GlobalKeyListener gkl;
	
	public KeyReader(String name) {
		super(name);
		this.keyTyped = 0;

		this.gkl = new GlobalKeyListener();
		this.gkl.addKeyListener(new KeyAdapter() {
			@Override public void keyPressed(KeyEvent event) {}
			
			@Override public void keyReleased(KeyEvent event) {
				int keyCode = event.getVirtualKeyCode();

				if (turnedOn && keyCode >= 65 && keyCode <= 90 && !(keyCode == KeyEvent.VK_C && event.isCtrlPressed())) {
					System.out.println(keyCode);
					keyTyped = keyCode;
					notifyAllSubs();
				}
			}
		});
	}
	
    public int getKeyTyped() {
    	return this.keyTyped;
    }
    
    @Override
    public void turnOff() {
    	this.turnedOn = false;
    	this.gkl.removeAllKeyListeners();
    	this.gkl = null;
    }
}
