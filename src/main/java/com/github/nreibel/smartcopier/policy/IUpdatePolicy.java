package com.github.nreibel.smartcopier.policy;

import java.io.File;
import java.io.IOException;

public interface IUpdatePolicy {
	
	/**
	 * Validates the replacement of dst with src
	 * @param src File candidate for copying
	 * @param dst File candidate for replacement
	 * @return <code>true</code> if src must replace dst, <code>false</code> otherwise
	 * @throws IOException
	 */
	boolean checkDoUpdate(File src, File dst) throws IOException;
}