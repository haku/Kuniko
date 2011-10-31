package com.vaguehope.kuniko;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
//	- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		wireGui();
	}
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	
	private Button btnRun;
	protected EditText txtUrl;
	
	private void wireGui () {
		this.btnRun = (Button) findViewById(R.id.btnClone);
		this.btnRun.setOnClickListener(this.btnClone_click);
		
		this.txtUrl = (EditText) findViewById(R.id.txtUrl);
		this.txtUrl.setEnabled(true);
	}
	
	OnClickListener btnClone_click = new OnClickListener() {
		@Override
		public void onClick (View v) {
			try {
				String url = MainActivity.this.txtUrl.getText().toString();
				File dir = GitHelper.getGitDirectory(url);
				GitCloneTask task = new GitCloneTask(MainActivity.this, url, dir);
				task.execute();
			}
			catch (Exception e) {
				Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
			}
		}
	};
	
// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
}