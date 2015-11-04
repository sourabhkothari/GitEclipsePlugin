package com.psl.git.helper;

import java.io.File;
import java.io.PrintWriter;

import com.psl.git.model.RepositoryFile;
import com.psl.git.util.CommonGitUtils;

public class FileHelper {

	public void createDirecory(RepositoryFile file, String relativeRootDir) {
		String fileName = null;
		fileName = CommonGitUtils.getRelativeDirectoryPath(relativeRootDir,
				file);
		System.out.println("creating Dir ::: " + fileName);
		File newFile = new File(fileName);
		try {
			newFile.mkdir();
		} catch (Exception e) {
			System.out.println("Exception in creating directory : "
					+ e.getMessage());
		}
	}

	public void createFile(RepositoryFile file, String relativeRootDir) {
		String fileName = null;
		fileName = CommonGitUtils.getRelativeDirectoryPath(relativeRootDir,
				file);
		System.out.println("creating File ::: " + fileName);
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
