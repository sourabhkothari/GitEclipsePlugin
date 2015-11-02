package com.psl.git.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IStorage;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.psl.git.component.swt.StringInput;
import com.psl.git.component.swt.StringStorage;
import com.psl.git.component.swt.TreeContentProvider;
import com.psl.git.component.swt.TreeLabelProvider;
import com.psl.git.model.RepositoryFile;
import com.psl.git.model.User;
import com.psl.git.service.GitService;
import com.psl.git.util.GitConstants;

public class FileExplorer extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.psl.git.actions.FileExplorer";

	private GitService gitService = new GitService();
	public static final int COLUMN_ELEMENT = 0;
	public static final int COLUMN_NUMBER = 1;

	private TreeViewer treeviewer;
	private Tree tree;
	private TreeColumn column0;

	// private TreeColumn column1;
	// private TreeSorter sorter = new TreeSorter(this);

	/**
	 * The constructor.
	 */
	public FileExplorer() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it
	 */
	public void createPartControl(Composite parent) {
		tree = new Tree(parent, SWT.MULTI);
		tree.setHeaderVisible(false);

		column0 = new TreeColumn(tree, SWT.NONE);
		column0.setText("PluginUI");
		column0.setWidth(300);
		// sorter.addColumn(column0, COLUMN_ELEMENT);
		tree.addListener(SWT.Expand, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("directory Expanding ={" + e.item.getData()
						+ "}");
			}
		});
		tree.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				System.out.println("selected ={" + e.item.getData() + "}");
				RepositoryFile file = (RepositoryFile) e.item.getData();
				if (GitConstants.FILE.equals(file.getFileOrDirectory())) {
					IEditorDescriptor desc = PlatformUI.getWorkbench()
							.getEditorRegistry()
							.getDefaultEditor(file.getFileName());
					System.out.println(desc.getId());
					IStorage storage = new StringStorage(file.getFileContent(),
							file.getFileName());
					IStorageEditorInput input = new StringInput(storage);
					IWorkbenchPage page = PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getActivePage();
					if (page != null)
						try {
							if (desc.getId() != null
									&& desc.getId().contains("MavenPomEditor")) { // not
								// able
								// to
								// resolve
								// this
								// issue.
								// not able to open MavenPomEditor (i.e.
								// org.eclipse.m2e.editor.MavenPomEditor) for
								// pom.xml
								// so opening pom.xml in XMLMultiPageEditorPart
								// manually
								page.openEditor(input,
										"org.eclipse.wst.xml.ui.internal.tabletree.XMLMultiPageEditorPart");
							}
							page.openEditor(input, desc.getId());
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				}
			}
		});
		treeviewer = new TreeViewer(tree);
		treeviewer.setContentProvider(new TreeContentProvider());
		treeviewer.setLabelProvider(new TreeLabelProvider());
		System.out.println("createPartControl");
	}

	public void refreshViewer(User user) {
		treeviewer.add(treeviewer.getInput(), user.getRepositoryFiles()
				.toArray());
		treeviewer.refresh();
		treeviewer.expandToLevel(2);
		addMenuItem();
	}

	public void addRootElement(User user) {
		List<RepositoryFile> list = new ArrayList<RepositoryFile>();
		RepositoryFile file = new RepositoryFile();
		file.setFileName(user.getRepositoryName());
		file.setFileOrDirectory(GitConstants.DIRECTORY);
		file.setRootFile(true);
		file.setFileHtmlPath(user.getUrl());
		list.add(file);
		treeviewer.setInput(list);
		refreshViewer();
	}

	public void refreshViewer() {
		treeviewer.refresh();
	}

	@Override
	public void setFocus() {
		// empty
	}

	public void addMenuItem() {
		/*
		 * final Menu menu = new Menu(tree); tree.setMenu(menu); final MenuItem
		 * checkoutMenuItem = new MenuItem(menu, SWT.PUSH);
		 * checkoutMenuItem.setText("Check Out");
		 */
		MenuManager menuMgr = new MenuManager();

		Menu menu = menuMgr.createContextMenu(treeviewer.getControl());
		menuMgr.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				if (treeviewer.getSelection().isEmpty()) {
					return;
				}

				if (treeviewer.getSelection() instanceof IStructuredSelection) {
						Action action = new Action() {

						public void run() {
							RepositoryFile file = (RepositoryFile)((IStructuredSelection) treeviewer
									.getSelection()).toList().get(0);
								System.out.println("new html path : "
										+ file
										.getFileHtmlPath());
								gitService.checkOutRepositoryFiles("sourabhkothari", "willpower@123", file);
							}
					};
					action.setText("Check Out");
					manager.add(action);

					Action testMenuAction = new Action() {

						public void run() {
							System.out
							.println("inside testMenuAction...its for test only");
						}
					};

					testMenuAction.setText("Don't Select Me");
					manager.add(testMenuAction);

				}
			}
		});
		menuMgr.setRemoveAllWhenShown(true);
		treeviewer.getControl().setMenu(menu);

	}
}