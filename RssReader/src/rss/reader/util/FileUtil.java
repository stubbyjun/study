package rss.reader.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtil {

	
	public static boolean isValidFile(String fileName) {
		
		return new File(Paths.get(fileName).toString()).isFile();
	}
	
	public static String read(String fileName) throws IOException {
		
		//Paths.get(System.getProperty("user.dir")).resolve(fileName);
		
		String content = new String(Files.readAllBytes(Paths.get(fileName)));
		
		return content;
	}
		
	public static void write(String fileName, String content) throws IOException {
		
		Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
	}
}
