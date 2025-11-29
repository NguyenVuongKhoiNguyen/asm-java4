package com.poly.movies.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.http.Part;

public class Xfile {
	
	public static String saveFile(Part filePart, String uploadPath) throws IOException {
	    // extract file name
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

	    // create folder if not exists
	    File uploadDir = new File(uploadPath);
	    if (!uploadDir.exists()) {
	        uploadDir.mkdirs();
	    }

	    // save the file
	    filePart.write(uploadPath + File.separator + fileName);

	    return fileName; // return saved name
	}
	public static boolean deleteFile(String uploadPath, String fileName) throws IOException {
		
		if (fileName == null || fileName.trim().isEmpty()) {
	        return false;
	    }
		
		File file = new File(uploadPath, fileName);
		
		if (file.exists()) {
			return file.delete();
		}
		
		return false;
	}
}
