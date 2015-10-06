package com.psl.git.listner;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;

import com.psl.git.model.RepositoryFile;
import com.psl.git.service.GitService;

/*this is a Swing based listeners.
 need to convert to SWT
*/

public class OnTreeExpandListener implements TreeWillExpandListener {

	GitService gitService = new GitService();
	String userName = null;
	String password = null;
	String root = "root";

	public OnTreeExpandListener(String userName, String password) {
		super();
		System.out.println("OnTreeExpandListner.construst");
		this.userName = userName;
		this.password = password;
	}

	@Override
	public void treeWillExpand(TreeExpansionEvent event)
			throws ExpandVetoException {

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath()
				.getLastPathComponent();
		if (node != null && node.getUserObject() != null) { // if it is not root
															// node
			RepositoryFile file = (RepositoryFile) node.getUserObject();
			if (!file.isExpanded()) {
				gitService.createSubTree(userName, password,
						file.getFileHtmlPath(), node);
				file.setExpanded(true);
			}
		}
	}

	@Override
	public void treeWillCollapse(TreeExpansionEvent event)
			throws ExpandVetoException {
		// do nothing
	}

}
