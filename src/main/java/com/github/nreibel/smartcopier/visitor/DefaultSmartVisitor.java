package com.github.nreibel.smartcopier.visitor;

import java.io.File;
import java.io.IOException;

public class DefaultSmartVisitor implements ISmartVisitor {

	@Override
	public void fileAdded(File oldFile, File newFile) throws IOException {}

	@Override
	public void fileModified(File oldFile, File newFile) throws IOException {}

	@Override
	public void fileDeleted(File oldFile, File newFile) throws IOException {}

	@Override
	public void fileNotChanged(File oldFile, File newFile) throws IOException {}
}
