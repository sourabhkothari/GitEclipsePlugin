package com.psl.git.component.jface;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.psl.git.service.GitService;
import com.psl.git.util.CommonGitUtils;
import com.psl.git.util.GitConstants;

public class GitLoginWindow extends Dialog {
	private GitService gitService = new GitService();
	private Text txtUrl;
	private Text txtUser;
	private Text txtPassword;
	private String url = GitConstants.GIT_ROOT;
	private String user = "";
	private String password = "";
	Button loginButton = null;
	boolean isAuthenticate = false;
	Shell parentShell;

	public GitLoginWindow(Shell parentShell) {
		super(parentShell);
		this.parentShell = parentShell;
	}

	/*
	 * @Override public void create() { super.create(); setTitle("Login Page");
	 * //setMessage("This is a TitleAreaDialog", IMessageProvider.INFORMATION);
	 * }
	 */

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout layout = new GridLayout(2, false);
		layout.marginRight = 5;
		layout.marginLeft = 10;
		container.setLayout(layout);

		Label lblUrl = new Label(container, SWT.NONE);
		lblUrl.setText("Url:*");

		txtUrl = new Text(container, SWT.BORDER);
		txtUrl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		txtUrl.setText(url);
		txtUrl.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				Text textWidget = (Text) e.getSource();
				String urlText = textWidget.getText();
				url = urlText;
			}
		});

		Label lblUser = new Label(container, SWT.NONE);
		lblUser.setText("User:*");

		txtUser = new Text(container, SWT.BORDER);
		txtUser.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		txtUser.setText(user);
		txtUser.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				Text textWidget = (Text) e.getSource();
				String userText = textWidget.getText();
				user = userText;
			}
		});

		Label lblPassword = new Label(container, SWT.NONE);
		GridData gd_lblNewLabel1 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_lblNewLabel1.horizontalIndent = 2;
		lblPassword.setLayoutData(gd_lblNewLabel1);
		lblPassword.setText("Password:*");

		txtPassword = new Text(container, SWT.BORDER | SWT.PASSWORD);
		txtPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		txtPassword.setText(password);
		txtPassword.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				Text textWidget = (Text) e.getSource();
				String passwordText = textWidget.getText();
				password = passwordText;
			}
		});
		return container;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Connect GitHub Repository");
	}

	// override method to use "Login" as label for the OK button
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		loginButton = createButton(parent, IDialogConstants.OK_ID, "Login",
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);

	}

	@Override
	protected Point getInitialSize() {
		return new Point(450, 170);
	}

	@Override
	protected void okPressed() {
		url = txtUrl.getText();
		user = txtUser.getText();
		password = txtPassword.getText();
		System.out.println(" url " + url + " user " + user + "password "
				+ password);
		super.okPressed();
		if (validateUserEntry()) {
			System.out.println("valid");
			// call REST service
			gitService.validateGitUser(url, user, password);
			close();
		} else {
			System.out.println("invalid");
			open();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private boolean validateUserEntry() {

		if (CommonGitUtils.isEmpty(getUrl())
				|| CommonGitUtils.isEmpty(getUser())
				|| CommonGitUtils.isEmpty(getPassword())) {

			MessageDialog.openError(parentShell, "Error",
					"all fields are mandatory");
			return false;
		}
		return true;
	}

}