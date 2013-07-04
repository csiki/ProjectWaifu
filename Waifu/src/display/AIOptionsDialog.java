package display;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import core.BehaviorContainer;
import core.BehaviorLoader;


//  @ Project		: ProjectWaifu
//  @ File Name		: AIOptionsDialog.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class AIOptionsDialog extends JDialog {
	
	private static final long serialVersionUID = -9028400984896993344L;
	
	private BehaviorContainer behContainer;
    private BehaviorLoader behLoader;
    private JFileChooser chooser;
    
    public AIOptionsDialog(BehaviorContainer behContainer, BehaviorLoader behLoader) {
    	this.behContainer = behContainer;
    	this.behLoader = behLoader;
    	
    	chooser = new JFileChooser( new File(".") )
    	{
			private static final long serialVersionUID = 3739877605561741067L;

			public void approveSelection()
    	    {
    	        if (getSelectedFile().isFile())
    	        {
    	            // wrong
    	            return;
    	        }
    	        else
    	            super.approveSelection();
    	    }
    	};
    	chooser.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
    	this.setVisible(false);
    }
    
    public void show() {
    	int chooseResult = this.chooser.showDialog(this.getParent(), "Choose directory of AI files");
    	
    	if (chooseResult == JFileChooser.APPROVE_OPTION) {
    		
    		String path;
			try {
				path = chooser.getSelectedFile().getCanonicalPath();
			} catch (IOException e) {
				return;
			}
			
    		this.behLoader.setSource(path);
    		this.behContainer.clearBehaviors();
    		this.behLoader.loadBehaviors(behContainer);
    	}
	}
}
