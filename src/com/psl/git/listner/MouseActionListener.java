package com.psl.git.listner;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.psl.git.model.RepositoryFile;
import com.psl.git.service.GitService;
import com.psl.git.util.GitConstants;

/*this is a Swing based listeners.
 need to convert to SWT
 
 currently not using in this project
*/

public class MouseActionListener extends MouseAdapter {
	JPopupMenu popup;
	JTree tree = null;
	GitService gitService = new GitService();
	String userName = null;
	String password = null;

	public MouseActionListener(JPopupMenu popup, JTree tree, String userName,
			String password) {
		this.popup = popup;
		this.tree = tree;
		this.userName = userName;
		this.password = password;
	}

	public void mouseReleased(MouseEvent e) {
		System.out.println("MouseActionListner...");
		if (SwingUtilities.isRightMouseButton(e)) {
			int x = e.getX();
			int y = e.getY();
			JTree tree = (JTree) e.getSource();
			TreePath path = tree.getPathForLocation(x, y);
			if (path == null)
				return;

			tree.setSelectionPath(path);
			if (e.isPopupTrigger()) {
				popup.show((JComponent) e.getSource(), e.getX(), e.getY());
			}
		}

		if (SwingUtilities.isLeftMouseButton(e)) {

			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
					.getLastSelectedPathComponent();
			if (node == null)
				// Nothing is selected.
				return;
			if (node.getUserObject() != null) { // if it is not root node
				RepositoryFile file = (RepositoryFile) node.getUserObject();
				if (GitConstants.FILE.equalsIgnoreCase(file.getFileOrDirectory())) {
					gitService.openFileEditor(file);
				}
			}

		}

	}
}
