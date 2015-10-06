package com.psl.git.model;

public class RepositoryFile {
	String fileName;
	boolean isExpanded = false;
	String relativePath;
	String fileHtmlPath;
	String fileOrDirectory;
	String fileContent;
	boolean isRootFile;

	public RepositoryFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileHtmlPath() {
		return fileHtmlPath;
	}

	public void setFileHtmlPath(String fileHtmlPath) {
		this.fileHtmlPath = fileHtmlPath;
	}

	public String getFileOrDirectory() {
		return fileOrDirectory;
	}

	public void setFileOrDirectory(String fileOrDirectory) {
		this.fileOrDirectory = fileOrDirectory;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public boolean isRootFile() {
		return isRootFile;
	}

	public void setRootFile(boolean isRootFile) {
		this.isRootFile = isRootFile;
	}

	@Override
	public String toString() {
		return fileName;
	}

}
