package com.github.nreibel.smartcopier.policy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UpdateIfContentDifferentPolicy implements IUpdatePolicy {


	@Override
	public boolean checkDoUpdate(File f1, File f2) throws IOException {

		BufferedReader br1 = new BufferedReader(new FileReader(f1));
		BufferedReader br2 = new BufferedReader(new FileReader(f2));

		try {
			while(true) {
				String str1 = br1.readLine();
				String str2 = br2.readLine();

				if (str1 == null) return str2 != null;
				if (str2 == null) return str1 != null;
				if (!str1.equals(str2)) return true;
			}
		}
		finally {
			br1.close();
			br2.close();
		}
	}

}
