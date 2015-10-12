package com.healthkart.newsreaderapplication;

/**Author name:Neelam Kushwah
 * Class Name:Screen Calculation Activity.
 * Class Description:This is a launcher activity and it is used to calculate
 *  device width and height and storing it in sqlite database before 
 *  finishing this activity**/
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;

import com.healthkart.newsreaderapplication.Utility.SharePreferanceWrapperSingleton;

public class ScreenCalculationActivity extends Activity {

	static int widthScreen = 0;
	static int heightScreen = 0;
	private int statusBarHeight, titleBarHeight;
	private final int LOW_DPI_STATUS_BAR_HEIGHT = 19;
	private final int MEDIUM_DPI_STATUS_BAR_HEIGHT = 25;
	private final int HIGH_DPI_STATUS_BAR_HEIGHT = 38;
	private DisplayMetrics metrics;
	private ProgressDialog progressDialog;
	private static SharePreferanceWrapperSingleton objSp;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		heightScreen = 0;
		init();

	}

	// This method starts the image serach activity
	public void showActivity() {

		Intent i = new Intent(ScreenCalculationActivity.this,
				NewsReaderActivity.class);
		startActivity(i);

	}

	// This method calculated device height nad width
	public void init() {
		try {
			// getting width and height of a screen in pixels
			Display display = getWindowManager().getDefaultDisplay();
			metrics = new DisplayMetrics();
			display.getMetrics(metrics);
			widthScreen = metrics.widthPixels;
			heightScreen = metrics.heightPixels;
			objSp = SharePreferanceWrapperSingleton.getSingletonInstance();

			new MyAsyncTask().execute();
		} catch (Exception e) {
			e.printStackTrace();
			finish();
		}
	}

	// This method calculates the status bar height
	public int getStatusBarHeight() {
		int statusBarHeight = 0;

		try {
			switch (metrics.densityDpi) {
			case DisplayMetrics.DENSITY_HIGH: {
				statusBarHeight = HIGH_DPI_STATUS_BAR_HEIGHT;
				break;
			}
			case DisplayMetrics.DENSITY_MEDIUM: {
				statusBarHeight = MEDIUM_DPI_STATUS_BAR_HEIGHT;
				break;
			}
			case DisplayMetrics.DENSITY_LOW: {
				statusBarHeight = LOW_DPI_STATUS_BAR_HEIGHT;
				break;
			}
			default: {
				statusBarHeight = MEDIUM_DPI_STATUS_BAR_HEIGHT;
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusBarHeight;
	}

	// This method gets title bar height
	public int getTitleBarHeight() {
		int viewTop = 0;
		try {
			viewTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT)
					.getTop();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewTop;
	}

	class MyAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			try {
				// show progress dialog
				progressDialog = ProgressDialog.show(
						ScreenCalculationActivity.this, "Loading...",
						"Please Wait...", true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				// find statusbar height
				statusBarHeight = getStatusBarHeight();
				titleBarHeight = getTitleBarHeight();
				heightScreen = heightScreen - statusBarHeight - titleBarHeight;// Calculates
																				// height
																				// screen
																				// in
																				// which
																				// application
																				// have
																				// to
																				// display.

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			try {
				if (progressDialog.isShowing())
					progressDialog.dismiss();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (widthScreen != 0 && heightScreen != 0) {
				objSp.setPref(getApplicationContext());
				objSp.setEditor();

				objSp.setPreferances(objSp, heightScreen, widthScreen);// save
																		// height
																		// and
																		// width
																		// in
																		// shared
																		// prefernce
				showActivity();
			}
			finish();
		}

	}

}
