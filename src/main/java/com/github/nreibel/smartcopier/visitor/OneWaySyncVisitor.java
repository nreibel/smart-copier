package com.github.nreibel.smartcopier.visitor;

import java.io.File;
import java.io.IOException;

import com.github.nreibel.smartcopier.CopyUtils;

public class OneWaySyncVisitor implements ISmartVisitor {

	@Override
	public void fileAdded(File oldFile, File newFile) throws IOException {
		CopyUtils.copyRecursive(oldFile, newFile);
	}

	@Override
	public void fileModified(File oldFile, File newFile) throws IOException {
		CopyUtils.copyRecursive(oldFile, newFile);
	}

	@Override
	public void fileDeleted(File oldFile, File newFile) throws IOException {
		CopyUtils.deleteRecursive(newFile);
	}

	@Override
	public void fileNotChanged(File oldFile, File newFile) throws IOException {
		// Do nothing
	}
}