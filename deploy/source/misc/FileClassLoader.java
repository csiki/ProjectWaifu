package misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileClassLoader extends ClassLoader {
	
	public FileClassLoader(ClassLoader cl) {
		super(cl);
	}
	
	@SuppressWarnings({ "rawtypes" })
	public Class loadClassFromFile(File file) throws FileNotFoundException, IOException {
		
		FileInputStream input = new FileInputStream(file);
		byte bytes[] = new byte[(int)file.length()];
		input.read(bytes);
		input.close();
		
		// define the class
		return defineClass(null, bytes, 0, bytes.length);
	}
}
