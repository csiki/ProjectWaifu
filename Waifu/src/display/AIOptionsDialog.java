package display;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

import core.BehaviorContainer;
import core.BehaviorLoader;
import core.Serializer;
import core.Settings;


//  @ Project		: ProjectWaifu
//  @ File Name		: AIOptionsDialog.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved



public class AIOptionsDialog extends JDialog { // TODO no need for JDialog at the moment
	
	private static final long serialVersionUID = -9028400984896993344L;
	
	private BehaviorContainer behContainer;
    private BehaviorLoader behLoader;
    private JFileChooser chooser;
    private Settings settings;
    
    public AIOptionsDialog(BehaviorContainer behContainer, BehaviorLoader behLoader, Settings settings) {
    	this.behContainer = behContainer;
    	this.behLoader = behLoader;
    	this.settings = settings;
    	
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
    
    public void showD() {
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
    		this.settings.setBehaviorsPath(path);
    		
    		// serialize settings
    		Serializer.serialize(null, settings, "settings.waifu");
    	}
	}
}
