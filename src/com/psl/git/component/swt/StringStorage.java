package com.psl.git.component.swt;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

public class StringStorage implements IStorage {
	private String string;
	private String fileName;
	public StringStorage(String input , String fileName) {
		this.string = input;
		this.fileName = fileName;
	}

	public InputStream getContents() throws CoreException {
		return new ByteArrayInputStream(string.getBytes());
	}

	public IPath getFullPath() {
		return null;
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

	public String getName() {
		return fileName;
	}

	public boolean isReadOnly() {
		return true;
	}

	
}