package com.healthkart.newsreaderapplication.Async;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.healthkart.newsreaderapplication.Constants;
import com.healthkart.newsreaderapplication.MyApplication;
import com.healthkart.newsreaderapplication.NewsReaderActivity;
import com.healthkart.newsreaderapplication.Model.NewsModel;
import com.healthkart.newsreaderapplication.Utility.MessageAlertDialog;
import com.healthkart.newsreaderapplication.adaptor.NewsAdapter;
import com.healthkart.newsreaderapplication.parser.JSONParser;
import com.healthkart.newsreaderapplication.parser.ServerConstant;

/**
 * @author Neelam Kushwah
 * @date 09-10-2015 Class Description:This Async class is used to integrate
 *       webservice
 **/
public class FetchingReviewsAsync extends AsyncTask<Void, String, String> {
	private ProgressDialog dialog;
	private Context ctx;
	private String url;
	private NewsAdapter adaptor;
	private ArrayList<NewsModel> arrNewsModel;

	// Constructor
	public FetchingReviewsAsync(Context ctx, String url, NewsAdapter adaptor,
			ArrayList<NewsModel> arrNewsModel) {
		this.url = url;
		this.ctx = ctx;
		this.adaptor = adaptor;
		this.arrNewsModel = arrNewsModel;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = new ProgressDialog(ctx);
		dialog.setCancelable(false);
		dialog.setMessage("Fetching Details..");
		dialog.show(); // this shows progress dialog
	}

	// This method called after on pre exceute method.
	// This method returns the response coming from server and if internet is
	// not connected or connection timed out then it returns timedout
	@Override
	protected String doInBackground(Void... params) {
		String result = null;
		if (ServerConstant.isConnectingToInternet(ctx)) {

			JSONParser xml = new JSONParser();

			try {
				result = xml.getDataFromUrl(ctx, url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			result = "timeout";
		}
		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		StartAction(result);

	}

	private void StartAction(String result) {

		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
			dialog = null;
		}

		if (result != null) {
			if (result.equals("timeout")) {
				MessageAlertDialog.showErrorAlert(
						"Connection timed out.Please try again.", ctx);
			} else {
				// put data in bundle
				MyApplication.NewsDataBundle.putString(
						Constants.MOVIE_DETAILS_DATA, result.toString());

				// call get data method and notify adapter
				NewsReaderActivity a = new NewsReaderActivity();
				a.getData(ctx, arrNewsModel);
				if (adaptor != null) {
					adaptor.notifyDataSetChanged();
				}
			}

		}
	}
}