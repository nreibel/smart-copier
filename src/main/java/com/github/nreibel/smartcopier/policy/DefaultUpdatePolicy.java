package com.github.nreibel.smartcopier.policy;

import java.io.File;

public class DefaultUpdatePolicy implements IUpdatePolicy {

	@Override
	public boolean checkDoUpdate(File src, File dst) {
		return true;
	}
}
