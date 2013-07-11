package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//  @ Project		: ProjectWaifu
//  @ File Name		: Serializer.java
//  @ Date			: 2013.07.02.
//  @ Author		: csiki
//  @ Copyright		: All rights reserved


public class Serializer {
	
	static String mainFolder = "serialized" + java.io.File.separator;
	
	private Serializer() {}
	
    public static void serialize(Behavior behavior, Serializable object, String filePathWithNameAndExtension) {
    	String path = mainFolder;
    	
    	// extend the path with behavior's name
    	if (behavior != null) {
    		path += behavior.getName() + java.io.File.separator;
    	}
    	else {
    		path += "misc" + java.io.File.separator;
    	}
    	
    	// create folder if does not exists
    	new File(path).mkdirs();
    	
    	// create file if does not exists
    	File file = new File(path + filePathWithNameAndExtension);
    	try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
    	
    	// serialize
    	try {
			FileOutputStream fout = new FileOutputStream(file, false);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(object);
			
			out.close();
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
    }
    
    public static Object deserialize(Behavior behavior, String filePathWithNameAndExtension) {
    	String path = mainFolder;
    	
    	// extend the path with behavior's name
    	if (behavior != null) {
    		path += behavior.getName() + java.io.File.separator;
    	}
    	else {
    		path += "misc" + java.io.File.separator;
    	}
    	
    	// deserialize
    	Object object = null;
    	
    	try {
			FileInputStream fin = new FileInputStream(path + filePathWithNameAndExtension);
			ObjectInputStream in = new ObjectInputStream(fin);
			object = in.readObject();
			
			in.close();
			fin.close();
		} catch (IOException | ClassNotFoundException e) {
			return null;
		}
    	
    	return object;
    }
}
