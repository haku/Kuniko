package com.vaguehope.kuniko;

import java.io.File;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Constants;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class GitCloneTask extends AsyncTask<Void, Void, Void> {
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	private final Context context;
	private final String uri;
	private final File targetDir;
	
	private Exception exception;
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	public GitCloneTask (Context context, String uri, File targetDir) {
		this.context = context;
		this.uri = uri;
		this.targetDir = targetDir;
	}
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	@Override
	protected void onPreExecute () {
		String msg;
		if (this.targetDir.exists()) {
			this.cancel(true);
			msg = "Directory already exists: " + this.targetDir.getAbsolutePath();
		}
		else {
			msg = "Cloning: " + this.uri + " ...";
		}
		Toast.makeText(this.context, msg, Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected Void doInBackground (Void... params) {
		if (this.isCancelled()) return null;
		
		try {
			CloneCommand clone = Git.cloneRepository();
			clone.setURI(this.uri);
			clone.setDirectory(this.targetDir);
			clone.setBranch(Constants.HEAD);
			clone.call();
		}
		catch (Exception e) {
			this.exception = e;
		}
		
		return null;
	}
	
	@Override
	protected void onCancelled () {
		Toast.makeText(this.context, "Clone canceled.", Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onPostExecute (Void result) {
		String msg;
		if (this.exception == null) {
			msg = "Clone complete: " + this.targetDir.getAbsolutePath();
		}
		else {
			msg = "Error: " + this.exception.getMessage();
		}
		Toast.makeText(this.context, msg, Toast.LENGTH_LONG).show();
	}
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
}
