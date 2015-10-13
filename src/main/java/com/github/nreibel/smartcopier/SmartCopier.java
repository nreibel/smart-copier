package com.github.nreibel.smartcopier;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.nreibel.smartcopier.exceptions.NotADirectoryException;
import com.github.nreibel.smartcopier.policy.DefaultUpdatePolicy;
import com.github.nreibel.smartcopier.policy.IUpdatePolicy;
import com.github.nreibel.smartcopier.visitor.DefaultSmartVisitor;
import com.github.nreibel.smartcopier.visitor.ISmartVisitor;


public class SmartCopier {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmartCopier.class);	

	private final File from;
	private final File to;
	private ISmartVisitor visitor = null;
	private IUpdatePolicy policy = null;

	private FileFilter filter = null;

	public SmartCopier(File from, File to) {
		this.from = from;
		this.to = to;
		this.visitor = new DefaultSmartVisitor();
		this.policy = new DefaultUpdatePolicy(true);
	}

	public SmartCopier setUpdatePolicy(IUpdatePolicy algorythm) {
		this.policy = algorythm;
		return this;
	}

	public SmartCopier setVisitor(ISmartVisitor visitor) {
		this.visitor = visitor;
		return this;
	}

	public SmartCopier setFileFilter(FileFilter filter) {
		this.filter = filter;
		return this;
	}

	public void start() throws IOException {
		visit(from, to);
	}

	private void visit(File oldFile, File newFile) throws IOException {
		
		if (!oldFile.isDirectory()) throw new NotADirectoryException(oldFile);
		
		LOGGER.trace("Visiting " + oldFile);

		// Find deleted files
		if (newFile.isDirectory() && newFile.exists()) {
			for (File dst : newFile.listFiles(filter)) {
				File src = new File(oldFile, dst.getName());
				if (!src.exists()) {
					LOGGER.debug(dst + " deleted");
					visitor.fileDeleted(src, dst);
				}
			}
		}

		// Find new or modified files
		for(File src : oldFile.listFiles(filter)) {

			File dst = new File(newFile, src.getName());

			if (!dst.exists()) {
				LOGGER.debug(src + " added");
				visitor.fileAdded(src, dst);
			}
			else if (src.isDirectory()) {
				visit(src, dst);
			}
			else {
				boolean updated = policy.checkDoUpdate(src, dst);
				if (updated) {
					LOGGER.debug(src + " modified");
					visitor.fileModified(src, dst);
				}
				else {
					LOGGER.trace(src + " not modified");
					visitor.fileNotChanged(src, dst);
				}
			}
		}
	}
}
