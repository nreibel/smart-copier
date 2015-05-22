package com.github.nreibel.smartcopier.policy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
		InputStream fis =  new FileInputStream(f);
		byte[] buffer = new byte[1024];
		MessageDigest complete = null;

		try {
			complete = MessageDigest.getInstance("SHA-256");

			int numRead;
			do {
				numRead = fis.read(buffer);
				if (numRead > 0) complete.update(buffer, 0, numRead);
			} while (numRead != -1);

		}
		finally {
			fis.close();
		}

		return complete.digest();
	}
}
