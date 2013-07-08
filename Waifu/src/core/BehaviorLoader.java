package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import behaviors.BehEx2;
import behaviors.BehEx3;
import behaviors.BehaviorExample;
import behaviors.JyggaMyNigga;
import core.Behavior;
import misc.FileClassLoader;

import javax.tools.*;

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
    	
    	// TODO ez még a compileos verzió
    	/*File behFile = new File(behSource+behPath);
    	if (behFile.exists()) {
    		
    		File compiledFile = compileBehavior(behSource+behPath);
    		
    		if (compiledFile != null && compiledFile.exists()) {
    			return this.loadBehaviorClass(compiledFile);
    		}
    	}*/
    	
    	// create BehaviorContainer
    	if (bc == null) {
    		bc = new BehaviorContainer();
    	}
    	// else extend already existing BehaviorContainer
    	
    	// check if behSource folder exists
    	File behFolder = new File(behSource);
    	
    	if (behFolder.exists()) {
    		
    		List<File> behaviorFiles = new ArrayList<File>();
    		
    		this.FindAllBehaviorsInDirectory(behFolder, behaviorFiles);
    		
    		// iterate through behaviorFiles list loading classes
        	for (final File behFile : behaviorFiles) {
        		Behavior beh = loadBehaviorClass(behFile);
        		
        		if (beh != null) {
        			bc.addBehavior(beh);
        		}
        	}
    	}
    	
    	// TODO próba hozzáadása!
    	//bc.addBehavior(new BehaviorExample("pl"));
    	//bc.addBehavior(new BehEx2("pl2"));
    	//bc.addBehavior(new BehEx3("pl3"));
    	bc.addBehavior(new JyggaMyNigga("jygga"));
    	
    	return bc;
    }
    
    public void setSource(String source) {
    	this.behSource = source;
    }
    
    private void FindAllBehaviorsInDirectory(final File behFolder, List<File> behaviorFiles) {
    	
    	for (final File behFile : behFolder.listFiles()) {
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
    
    private File compileBehavior(String behPath) {
    	
    	System.setProperty("java.home", "C:"+java.io.File.separator+"Program Files"+java.io.File.separator+"Java"+java.io.File.separator+"jdk1.7.0");
    	JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    	// TODO compiler null ha jre-vel fordítják, ez problémás lehet !
    	
    	int compilationResult = compiler.run(null, null, null, behPath, "-d", "behaviors");
    	
    	if (compilationResult == 0) {
    		return new File("behaviors"+java.io.File.separator+behPath);
        }
    	
    	return null;
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
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	// get the constructor
    	Constructor<Behavior> behConstructor = null;
    	try {
    		behConstructor = behClass.getConstructor();
    	} catch (SecurityException e) {
    		e.printStackTrace();
    	} catch (NoSuchMethodException e) {
    		e.printStackTrace();
    	}
    	
    	// construct the player
    	Behavior beh = null;
    	try {
    		beh = behConstructor.newInstance();
    	} catch (IllegalArgumentException e) {
    		e.printStackTrace();
    	} catch (InstantiationException e) {
    		e.printStackTrace();
    	} catch (IllegalAccessException e) {
    		e.printStackTrace();
    	} catch (InvocationTargetException e) {
    		e.printStackTrace();
    	}
    	
    	return beh;
    }
}
