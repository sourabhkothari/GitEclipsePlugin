package com.psl.git.helper;

import java.io.File;
import java.io.PrintWriter;

import com.psl.git.model.RepositoryFile;


public class FileHelper {

	String LOCAL_REPOSITORY = "C:\\Users\\sourabh_kothari\\Desktop\\";

	public void createDirecory(RepositoryFile file) {
		String fileName = null;
		if (file != null
				&& (file.getRelativePath() == null || file.getRelativePath()
						.isEmpty())) {
			fileName = LOCAL_REPOSITORY + "\\" + file.getFileName();
		} else {
			fileName = LOCAL_REPOSITORY + "\\" + file.getRelativePath() + "\\"
					+ file.getFileName();
		}
		File newFile = new File(fileName);
		try {
			newFile.mkdir();
		} catch (Exception e) {
			System.out.println("Exception in creating directory : "
					+ e.getMessage());
		}
		if(file.isRootFile()){
			LOCAL_REPOSITORY = LOCAL_REPOSITORY  + file.getFileName();
		}
	}

	public void createFile(RepositoryFile file) {
		String fileName = null;

		if (file != null
				&& (file.getRelativePath() == null || file.getRelativePath()
						.isEmpty())) {
			fileName = LOCAL_REPOSITORY + "\\" + file.getFileName();
		} else {
			fileName = LOCAL_REPOSITORY + "\\" + file.getRelativePath() + "\\"
					+ file.getFileName();
		}
		File newFile = new File(fileName);
		PrintWriter out = null;
		try {
			if (newFile.createNewFile()) {
				out = new PrintWriter(newFile);
				out.print(file.getFileContent());
				out.flush();
			}
		} catch (Exception e) {
			System.out.println("Exception in downloading file : "
					+ e.getMessage());
		} finally {
			out.close();
		}
	}
}
