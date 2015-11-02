package com.psl.git.listner;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.psl.git.model.RepositoryFile;
import com.psl.git.service.GitService;
import com.psl.git.util.GitConstants;


/*this is a Swing based listeners.
need to convert to SWT

Note : currently not using in this project
*/

public class onSelectionListener implements TreeSelectionListener {

	JTree tree = null;
	GitService gitService = new GitService();
	String userName = null;
	String password = null;

	public onSelectionListener(JTree tree, String userName, String password) {
		super();
		System.out.println("onSelectionListner.construst");
		this.tree = tree;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();
		if (node == null)
			// Nothing is selected.
			return;
		if (node.getUserObject() != null) { // if it is not root node
			RepositoryFile file = (RepositoryFile) node.getUserObject();
			if (GitConstants.FILE.equalsIgnoreCase(file.getFileOrDirectory())) {
				gitService.openFileEditor(file);
			} /*
			 * else { gitViewService.createSubTree(userName, password,
			 * file.getFileHtmlPath(), node); }
			 */
		}
	}
}
