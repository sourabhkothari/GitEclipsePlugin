package com.psl.git.service;

import java.util.List;

import com.psl.git.helper.ExplorerHelper;
import com.psl.git.helper.FileHelper;
import com.psl.git.helper.GitHelper;
import com.psl.git.model.RepositoryFile;
import com.psl.git.model.User;
import com.psl.git.util.GitConstants;

public class GitService {

	GitHelper gitHelper = new GitHelper();
	ExplorerHelper explorerHelper = new ExplorerHelper();
	FileHelper fileHelper = new FileHelper();

	public void validateGitUser(String path, String userName, String password) {
		// connect to GIT Service
		// get JSON response ,
		// createGitExplorer()
		User user = gitHelper.getGitFiles(path, userName, password, true);
		System.out.println(user);
		System.out.println(user.getRepositoryFiles());
		explorerHelper.openGitFileExplorer(user);
	}

	public void openFileEditor(RepositoryFile file) {
		// get content , fileName
		// open Editor
	}

	public User createSubTree(String userName, String password, String path) {
		// on tree selection , fetch sub tree and add as a child to the branch
		User user = gitHelper.getGitFiles(path, userName, password, false);
		return user;
	}

	public void checkOutRepositoryFiles(String userName, String password,
			RepositoryFile file) {
		// download files to a specific location
		System.out.println("::::::::::::  -- check out starts --  ::::::::::");
		if (GitConstants.FILE.equals(file.getFileOrDirectory())) {
			fileHelper.createFile(file, null);
		} else {
			fileHelper.createDirecory(file, null);
			if (file.isRootFile()) {
				downloadRepositoryFiles(userName, password,
						file.getFileHtmlPath(), "");
			} else {
				downloadRepositoryFiles(userName, password,
						file.getFileHtmlPath(), file.getRelativePath());
			}
		}
		System.out
		.println("::::::::::::  -- check out completes --  ::::::::::");
	}

	public void downloadRepositoryFiles(String userName, String password,
			String path, String relativeRootDir) {
		User user = gitHelper.getGitFiles(path, userName, password, false);
		List<RepositoryFile> repositoryFiles = user.getRepositoryFiles();
		for (RepositoryFile file : repositoryFiles) {
			if (GitConstants.FILE.equals(file.getFileOrDirectory())) {
				fileHelper.createFile(file, relativeRootDir);
			} else {
				fileHelper.createDirecory(file, relativeRootDir);
				downloadRepositoryFiles(userName, password,
						file.getFileHtmlPath(), relativeRootDir);
			}
		}
	}

}
