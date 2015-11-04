package com.psl.git.helper;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.psl.git.actions.FileExplorer;
import com.psl.git.model.User;
import com.psl.git.util.GitConstants;

public class ExplorerHelper {

	@SuppressWarnings("deprecation")
	public void openGitFileExplorer(User user) {
		// new FileExplorer();
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = reg
				.getConfigurationElementsFor(GitConstants.TREEVIEW_EXTENTION_ID);
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		Platform.getExtensionRegistry().getExtensions(
				GitConstants.TREEVIEW_EXTENTION_ID);
		IWorkbenchPage page = window.getActivePage();
		try {
			FileExplorer fileExplorer = (FileExplorer) page
					.showView(GitConstants.TREEVIEW_EXTENTION_ID);
			fileExplorer.addRootElement(user);
			fileExplorer.refreshViewer(user);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

}
