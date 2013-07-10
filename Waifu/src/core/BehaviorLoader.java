package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import core.Behavior;
import misc.FileClassLoader;

//  @ Project		: ProjectWaifu
//  @ File Name		: BehaviorLoader.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


public class BehaviorLoader implements Serializable {
	
	private static final long serialVersionUID = -4083336349416973787L;
	
	/**
	 * Ends with a java.io.File.separator
	 */
    private String behSource;
    
    public BehaviorLoader(String behSource) {
    	this.behSource = behSource;
    }
    
    public BehaviorContainer loadBehaviors(BehaviorContainer bc) {
    	
    	// create BehaviorContainer
    	if (bc == null) {
    		bc = new BehaviorContainer();
    	}
    	// else extend already existing BehaviorContainer
    	
    	// check if behSource folder exists
    	File behFolder = new File(behSource);
    	
    	if (behFolder.canRead()) {

    		List<File> behaviorFiles = new ArrayList<File>();
    		
    		this.FindAllBehaviorsInDirectory(behFolder, behaviorFiles);
    		
    		// iterate through behaviorFiles list loading classes
        	for (File behFile : behaviorFiles) {

        		Behavior beh = loadBehaviorClass(behFile);
        		if (beh != null) {
        			bc.addBehavior(beh);
        		}
        	}
    	}
    	
    	return bc;
    }
    
    public void setSource(String source) {
    	this.behSource = source;
    }
    
    private void FindAllBehaviorsInDirectory(final File behFolder, List<File> behaviorFiles) {

    	for (File behFile : behFolder.listFiles()) {
    		
    		if (behFile.isDirectory()) {
    			this.FindAllBehaviorsInDirectory(behFile, behaviorFiles);
    		}
    		else {
    			// check behFile extension
    			int i = behFile.getName().lastIndexOf('.');
    			if (i > 0) {
    			    String extension = behFile.getName().substring(i+1);

    			    if (extension.equals("class")) {
    			    	// add file behaviorFiles
    			    	behaviorFiles.add(behFile);
    			    }
    			}
    		}
    	}
    }
    
    @SuppressWarnings("unchecked")
	private Behavior loadBehaviorClass(File compiledFile) {
    	
    	FileClassLoader fcl = new FileClassLoader(BehaviorLoader.class.getClassLoader());
    	
    	Class<Behavior> behClass = null;
    	
    	// get the class
    	try {
    		behClass = fcl.loadClassFromFile(compiledFile);
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    		return null;
    	} catch (IOException e) {
    		e.printStackTrace();
    		return null;
    	}
    	
    	// get the constructor
    	Constructor<Behavior> behConstructor = null;
    	try {
    		behConstructor = behClass.getConstructor();
    	} catch (SecurityException e) {
    		e.printStackTrace();
    		return null;
    	} catch (NoSuchMethodException e) {
    		e.printStackTrace();
    		return null;
    	}
    	
    	// construct the player
    	Behavior beh = null;
    	try {
    		beh = behConstructor.newInstance();
    	} catch (IllegalArgumentException e) {
    		e.printStackTrace();
    		return null;
    	} catch (InstantiationException e) {
    		e.printStackTrace();
    		return null;
    	} catch (IllegalAccessException e) {
    		e.printStackTrace();
    		return null;
    	} catch (InvocationTargetException e) {
    		e.printStackTrace();
    		return null;
    	}
    	
    	return beh;
    }
}
