package com.psl.git.listner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.psl.git.model.RepositoryFile;
import com.psl.git.service.GitService;


/*this is a Swing based listeners.
	need to convert to SWT
*/

public class MenuActionListener implements ActionListener {

	GitService gitService = new GitService();
	String userName = null;
	String password = null;
	String root = "root";
	JTree tree = null;

	public MenuActionListener(JTree tree, String userName, String password) {
		super();
		System.out.println("MenuActionListner.construst");
		this.tree = tree;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action performed....");
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
				.getLastSelectedPathComponent();

		if (e.getActionCommand().equals("CheckIn")) {
			System.out.println("CheckIn");
			System.out.println(node.toString());
		}
		if (e.getActionCommand().equals("CheckOut")) {
			System.out.println("CheckOut starts....");
			RepositoryFile file = (RepositoryFile)node.getUserObject();
			try {
				gitService.checkOutRepositoryFiles(userName, password, file);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println("check out finish.....");
		}
		if (e.getActionCommand().equals("Compare")) {
			System.out.println("Compare");
			System.out.println(node.toString());
		}
	}

}
