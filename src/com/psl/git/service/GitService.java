package com.psl.git.service;

import javax.swing.tree.DefaultMutableTreeNode;

import com.psl.git.helper.GitHelper;
import com.psl.git.model.RepositoryFile;
import com.psl.git.model.User;

public class GitService {

	GitHelper gitHelper = new GitHelper();
	int fileCount = 0;

	public void validateGitUser(String userName, String password, String path) {
		//connect to GIT Service 
		//get JSON response , 
		//createGitExplorer()
		User user = gitHelper.getGitFiles(path, userName, password, true);
		System.out.println(user);
		System.out.println(user.getRepositoryFiles());
	}
	
	public void openFileEditor(RepositoryFile file) {
		//get content , fileName
		//open Editor
	}

	public void createSubTree(String userName, String password, String path,
			DefaultMutableTreeNode node) {
		// on tree selection , fetch sub tree and add as a child to the branch
		gitHelper.getGitFiles(path, userName, password, false);
	}

	public void checkOutRepositoryFiles(String userName, String password,
			RepositoryFile file) throws InterruptedException {
		//download files to a specific location
	}


	public void createFile(RepositoryFile file) {
		//fileHelper.createFile(file);
	}

	public void createDirecory(RepositoryFile file) {
		//fileHelper.createDirecory(file);
	}

}
