package com.psl.git.component.swt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import com.psl.git.model.RepositoryFile;
import com.psl.git.util.GitConstants;

public class TreeLabelProvider extends BaseLabelProvider implements
ITableLabelProvider {
	private Image fileImg;
	private Image folderImg;

	public TreeLabelProvider() {
		try {
			fileImg = new Image(null, new FileInputStream(
					GitConstants.LOCAL_PROJECT_PATH + GitConstants.FILE_ICON));
			folderImg = new Image(null, new FileInputStream(
					GitConstants.LOCAL_PROJECT_PATH + GitConstants.FOLDER_ICON));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if ("FILE".equals(((RepositoryFile) element).getFileOrDirectory())) {
			return fileImg;
		} else {
			return folderImg;
		}
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		return ((RepositoryFile) element).getFileName();
	}

}