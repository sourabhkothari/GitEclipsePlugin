package com.psl.git.util;

public class GitConstants {

	//local workspace location where checkout should be done
	public static final String LOCAL_WORKSPACE_LOCATION = "C:\\Users\\sourabh_kothari\\Desktop\\";
	
	//change this url according to the actual REST service being pointed.
	public static final String REST_GIT_VALIDATION_URL = "http://localhost:8080/PSLEclipsePlugIn/validate";
	
	//change this url according to the actual REST service being pointed.
	public static final String REST_GIT_GETTREE_URL = "http://localhost:8080/PSLEclipsePlugIn/getRepostiorySubTree";
	
	public static final String FILE = "FILE";
	public static final String DIRECTORY = "DIRECTORY";
	
	//change if you are targeting to some other repository
	public static final String GIT_ROOT = "https://github.com/nikhildongre/PSLEclipsePlugIn";
	public static final String TREEVIEW_EXTENTION_ID = "com.psl.git.actions.FileExplorer";
	public static final String XML_EDITOR_PLUGIN_ID = "org.eclipse.wst.xml.ui.internal.tabletree.XMLMultiPageEditorPart";
	public static final String POM_EDITOR_PLUGIN_KEY = "MavenPomEditor";
	
	//change to actual project path i.e. the workspace location of this plugin project. 
	public static final String LOCAL_PROJECT_PATH = "D:\\IBM POC\\GitEclipsePlugin\\";
	
	public static final String FOLDER_ICON = "icons\\folderIcon.png";
	public static final String FILE_ICON = "icons\\fileIcon.png";
	
}
