package com.github.nreibel.smartcopier.policy;

import java.io.File;
import java.io.IOException;

public class UpdateIfNewerPolicy implements IUpdatePolicy {

	@Override
	public boolean checkDoUpdate(File src, File dst) throws IOException {
		return src.lastModified() > dst.lastModified();
	}
}
