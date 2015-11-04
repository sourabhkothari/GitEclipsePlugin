package com.psl.git.util;

import com.psl.git.model.RepositoryFile;

public class CommonGitUtils {

	public static boolean isEmpty(String value) {
		if (value == null || value.equals("") || value.equals("null")) {
			return true;
		}
		return false;
	}

	public static String getRelativeDirectoryPath(String rootFilePath,
			RepositoryFile file) {
		if (rootFilePath == null) {
			return GitConstants.LOCAL_WORKSPACE_LOCATION + file.getFileName();

		} else if (file.isRootFile() || rootFilePath == "") {
			return GitConstants.LOCAL_WORKSPACE_LOCATION
					+ file.getRelativePath() + "/" + file.getFileName();
		}else{
			return GitConstants.LOCAL_WORKSPACE_LOCATION
					+ (file.getRelativePath().replace(rootFilePath, "")) + "/"
					+ file.getFileName();
		}

	}
}
