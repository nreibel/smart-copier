package com.github.nreibel.smartcopier.policy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class UpdateIfHashDifferentPolicy implements IUpdatePolicy {

	@Override
	public boolean checkDoUpdate(File f1, File f2) throws IOException {
		try {
			byte[] chksum1 = UpdateIfHashDifferentPolicy.createChecksum(f1);
			byte[] chksum2 = UpdateIfHashDifferentPolicy.createChecksum(f2);
			return !Arrays.equals(chksum1, chksum2);
		}
		catch(NoSuchAlgorithmException ex) {
			throw new IOException(ex);
		}
	}

	private static byte[] createChecksum(File f) throws IOException, NoSuchAlgorithmException {
		InputStream fis = new FileInputStream(f);

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			fis = new DigestInputStream(fis, md);
			while (fis.read() >= 0);
			return md.digest();
		}
		finally {
			fis.close();
		}
	}
}
