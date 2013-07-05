package core;

import de.ksquared.system.keyboard.GlobalKeyListener;
import de.ksquared.system.keyboard.KeyAdapter;
import de.ksquared.system.keyboard.KeyEvent;

//  @ Project		: ProjectWaifu
//  @ File Name		: KeyReader.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class KeyReader extends Sensor implements Runnable {
	
	volatile private int keyTyped;
	private GlobalKeyListener gkl;
	private KeyAdapter keyAdatper;
	
	public KeyReader(String name) {
		super(name);
		this.keyTyped = 0;
	}
	
	@Override
	public void run() {
		//this.gkl = new GlobalKeyListener();
		new GlobalKeyListener().addKeyListener(new KeyAdapter() {
			@Override public void keyPressed(KeyEvent event) { System.out.println(event); }
			@Override public void keyReleased(KeyEvent event) {
				System.out.println(event);
				if(event.getVirtualKeyCode()==KeyEvent.VK_ADD
				&& event.isCtrlPressed())
					System.out.println("CTRL+ADD was just released (CTRL is still pressed)");
			}
		});
		
		//this.gkl.addKeyListener(this.keyAdatper);
		
		while (this.turnedOn) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("[keyreaderend]");
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
