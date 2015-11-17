package com.example.asynctaskprogressbar;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	ProgressBar progressbar;
	TextView result;
	//MyTask task = new MyTask(progressbar, result); // async task
	Button showProgressBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressbar = (ProgressBar) findViewById(R.id.progress);
		showProgressBtn = (Button) findViewById(R.id.btn);
		result = (TextView) findViewById(R.id.Result);

		showProgressBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				MyTask task = new MyTask(progressbar, result);
				task.execute();
			}
		});

	}

	private class MyTask extends AsyncTask<Integer, Integer, Integer> {

		ProgressBar progressbar;
		TextView result;

		public MyTask(ProgressBar progressbar, TextView result) {
			this.progressbar = progressbar;
			this.result = result;
		}

		protected void onPreExecute() {
			super.onPreExecute();
			progressbar.setMax(100);
			progressbar.setVisibility(View.VISIBLE);
			result.setText("0");

		}

		protected Integer doInBackground(Integer... params) {

			try {
				for (int i = 1; i <= 100; i++) {

					Thread.sleep(1000);
					publishProgress(i);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				return 0;
			}
			return 1;
		}

		protected void onProgressUpdate(Integer... values) {

			super.onProgressUpdate(values[0]);

			progressbar.setProgress(values[0]);
			Integer s = values[0];
			result.setText(s);
		}

		protected void onPostExecute(Integer result) {

			super.onPostExecute(result);
			progressbar.setProgress(0);
			progressbar.setVisibility(View.INVISIBLE);
		}

	}

}