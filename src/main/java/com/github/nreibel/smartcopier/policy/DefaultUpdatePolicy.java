package com.github.nreibel.smartcopier.policy;

import java.io.File;

public class DefaultUpdatePolicy implements IUpdatePolicy {

	private final boolean accept;
	
	public DefaultUpdatePolicy(boolean accept) {
		this.accept = accept;
	}

	@Override
	public boolean checkDoUpdate(File src, File dst) {
		return accept;
	}
}
