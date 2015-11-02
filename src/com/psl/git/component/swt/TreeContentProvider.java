package com.psl.git.component.swt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.psl.git.model.RepositoryFile;
import com.psl.git.model.User;
import com.psl.git.service.GitService;
import com.psl.git.util.GitConstants;

public class TreeContentProvider implements ITreeContentProvider {
	
	GitService service = new GitService();

	private static final Object[] EMPTY_ARRAY = new Object[0];

	public Object[] getChildren(Object parentElement) {  //every time tree is expanding...
		System.out.println("getChildren" +parentElement.toString());
		if(parentElement instanceof Collection){
			return ((List) parentElement).toArray();
		}else{
			//call the Gitservice and get child files of the respective direcotry.
			//need to find out a way to remove username/pwd hardcoding
			RepositoryFile file = ((RepositoryFile)parentElement);
			User user = service.createSubTree("sourabhkothari", "willpower@123", file.getFileHtmlPath());
			return user.getRepositoryFiles().toArray();
		}
	}

	public Object getParent(Object element) {
		System.out.println("getParent" +element.toString());
		return null;
	}

	public boolean hasChildren(Object element) {
		if (GitConstants.FILE.equals(((RepositoryFile) element).getFileOrDirectory())) {
			return false;
		} else {
			return true;
		}
	}

	public Object[] getElements(Object inputElement) {  //first time when setInput is called on tree viewer
		System.out.println("getElements" +inputElement.toString());
		return getChildren(inputElement);
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}