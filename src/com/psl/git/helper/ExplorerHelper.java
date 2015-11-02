package com.psl.git.helper;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;

import com.psl.git.actions.FileExplorer;
import com.psl.git.model.User;

public class ExplorerHelper {
	
	@SuppressWarnings("deprecation")
	public void openGitFileExplorer(User user){
		//new FileExplorer();
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] extensions = reg
				.getConfigurationElementsFor("com.psl.git.actions.FileExplorer");
		for (int i = 0; i < extensions.length; i++) {
			IConfigurationElement extension = extensions[i];
			System.out.println(extension.getName());
			System.out.println(extension.getDeclaringExtension());
		}
		
		IWorkbenchWindow window =
				 PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		 Platform.getExtensionRegistry().getExtensions("com.psl.git.actions.FileExplorer");
				 IWorkbenchPage
				 page = window.getActivePage();
			try {
				FileExplorer fileExplorer = (FileExplorer)page.showView("com.psl.git.actions.FileExplorer");
				fileExplorer.addRootElement(user);
				fileExplorer.refreshViewer(user);
			} catch (PartInitException e) {
				e.printStackTrace();
			} 
	}

}
