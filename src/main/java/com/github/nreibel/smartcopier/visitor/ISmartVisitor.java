package com.github.nreibel.smartcopier.visitor;

import java.io.File;
import java.io.IOException;

public interface ISmartVisitor {

	void fileAdded(File oldFile, File newFile)
			throws IOException;

	void fileModified(File oldFile, File newFile)
			throws IOException;

	void fileDeleted(File oldFile, File newFile)
			throws IOException;

	void fileNotChanged(File oldFile, File newFile)
			throws IOException;
}