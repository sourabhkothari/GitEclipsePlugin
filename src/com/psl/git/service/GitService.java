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
	int fileCount = 0;

	public void validateGitUser(String path, String userName, String password) {
		//connect to GIT Service 
		//get JSON response , 
		//createGitExplorer()
		User user = gitHelper.getGitFiles(path, userName, password, true);
		System.out.println(user);
		System.out.println(user.getRepositoryFiles());
		explorerHelper.openGitFileExplorer(user);
	}
	
	public void openFileEditor(RepositoryFile file) {
		//get content , fileName
		//open Editor
	}

	public User createSubTree(String userName, String password, String path) {
		// on tree selection , fetch sub tree and add as a child to the branch
		User user = gitHelper.getGitFiles(path, userName, password, false);
		return user;
	}

	public void checkOutRepositoryFiles(String userName, String password,
			RepositoryFile file) {
		//download files to a specific location
		if (GitConstants.FILE.equals(file.getFileOrDirectory())) {
			createFile(file);
		} else {
			createDirecory(file);
			downloadRepositoryFiles(userName, password, file.getFileHtmlPath());
		}
	}


	public void downloadRepositoryFiles(String userName, String password,
			String path){
		User user = gitHelper.getGitFiles(path, userName, password, false);
		List<RepositoryFile> repositoryFiles = user.getRepositoryFiles();
		for (RepositoryFile file : repositoryFiles) {
			if (GitConstants.FILE.equals(file.getFileOrDirectory())) {
				createFile(file);
			} else {
				createDirecory(file);
				downloadRepositoryFiles(userName, password,
						file.getFileHtmlPath());
			}
		}
	}

	public void createFile(RepositoryFile file) {
		fileHelper.createFile(file);
	}

	public void createDirecory(RepositoryFile file) {
		fileHelper.createDirecory(file);
	}


}
