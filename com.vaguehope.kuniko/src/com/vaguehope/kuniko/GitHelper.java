package com.vaguehope.kuniko;

import java.io.File;

import android.os.Environment;

public class GitHelper {
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	private GitHelper () {}
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	public static File getGitDirectory (String url) {
		if (url == null || url.length() < 0) throw new IllegalArgumentException();
		
		int x = url.lastIndexOf("/");
		if (x < 1) throw new IllegalArgumentException("Can not get name from: " + url);
		String name = url.substring(x);
		if (name == null || name.length() < 1) throw new IllegalArgumentException("Can not get name from: " + url);
		
		File sdcard = Environment.getExternalStorageDirectory();
		File coDir = new File(sdcard, "src");
		
		File ret = new File(coDir, name);
		return ret;
	}
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
}
