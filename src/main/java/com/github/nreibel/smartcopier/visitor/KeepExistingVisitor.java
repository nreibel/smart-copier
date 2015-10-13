package com.github.nreibel.smartcopier.visitor;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class KeepExistingVisitor implements ISmartVisitor {

	@Override
	public void fileAdded(File oldFile, File newFile) throws IOException {
		if (oldFile.isFile()) FileUtils.copyFile(oldFile, newFile);
		else FileUtils.copyDirectory(oldFile, newFile);
	}

	@Override
	public void fileModified(File oldFile, File newFile) throws IOException {
		if (oldFile.isFile()) FileUtils.copyFile(oldFile, newFile);
		else FileUtils.copyDirectory(oldFile, newFile);
	}

	@Override
	public void fileDeleted(File oldFile, File newFile) throws IOException {
		// Do nothing
	}

	@Override
	public void fileNotChanged(File oldFile, File newFile) throws IOException {
		// Do nothing
	}
}