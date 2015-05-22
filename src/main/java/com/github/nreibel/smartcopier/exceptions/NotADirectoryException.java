package com.github.nreibel.smartcopier.exceptions;

import java.io.File;
import java.io.IOException;

public class NotADirectoryException extends IOException {

	private static final long serialVersionUID = -7589540008138810531L;

	public NotADirectoryException(File file) {
		this(file, null);
	}

	public NotADirectoryException(File file, Throwable cause) {
		super("Not a directory: " + file, cause);
	}

}
