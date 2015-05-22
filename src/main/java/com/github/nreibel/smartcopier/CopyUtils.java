package com.github.nreibel.smartcopier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CopyUtils {

	public static void deleteRecursive(File from) throws IOException {
		if (from.isDirectory()) {
			for(File f : from.listFiles()) {
				deleteRecursive(f);
			}
		}
		from.delete();
	}

	public static void copyRecursive(File from, File to) throws IOException {

		if (!to.exists()) {
			to.mkdirs();
		}

		if (from.isDirectory()) {
			for(String fname : from.list()) {
				File src = new File(from, fname);
				File dst = new File(to, fname);
				copyRecursive(src, dst);
			}
		}
		else {
			Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
	}
	
	public static void moveRecursive(File from, File to) throws IOException {
		
		if (from.isDirectory()) {
			
			if (!to.exists()) to.mkdirs();
			
			for(String f : from.list()) {
				File childFrom = new File(from, f);
				File childTo = new File(to, f);
				
				moveRecursive(childFrom, childTo);
			}
			
			Files.delete(from.toPath());
		}
		else Files.move(from.toPath(), to.toPath(), StandardCopyOption.ATOMIC_MOVE);
	}
}
