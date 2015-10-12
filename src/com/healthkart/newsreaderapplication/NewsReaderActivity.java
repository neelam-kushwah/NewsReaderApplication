package com.healthkart.newsreaderapplication;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cuubonandroid.sugaredlistanimations.SpeedScrollListener;
import com.healthkart.newsreaderapplication.Async.FetchingReviewsAsync;
import com.healthkart.newsreaderapplication.Model.NewsModel;
import com.healthkart.newsreaderapplication.Utility.MessageAlertDialog;
import com.healthkart.newsreaderapplication.Utility.SharePreferanceWrapperSingleton;
import com.healthkart.newsreaderapplication.adaptor.NewsAdapter;
import com.healthkart.newsreaderapplication.parser.ServerConstant;

/**
 * @author Neelam Kushwah
 * @date 09-10-2015
 **/
public class NewsReaderActivity extends Activity {
	private TextView tv_Title;
	private SharePreferanceWrapperSingleton objSPS;
	private Context ctx;
	private Typeface font;
	private RelativeLayout rl_Header;
	private ListView lv_News;
	private NewsModel newsModel;
	private ArrayList<NewsModel> arrNewsModel;
	private NewsAdapter newsAdaptor;
	private String url;
	private SpeedScrollListener listener;

	private String API = "f248611a4d1a3f708a97609d225b0d1b:3:73172373";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_news);
		ctx = this;
		objSPS = SharePreferanceWrapperSingleton.getSingletonInstance();
		objSPS.setPref(ctx);
		objSPS.setEditor();
		font = (Typeface.createFromAsset(getAssets(), "AlegreyaSans-Light.ttf"));
		listener = new SpeedScrollListener();

		setParameter();// set Parameters of Views
		setListAdaptor();
		// below code is used for fetching data from new york json api
		url = ServerConstant.BASE_URL + API;
		new FetchingReviewsAsync(ctx, url, newsAdaptor, arrNewsModel).execute();
	}

	private void setListAdaptor() {
		// setting Adapter
		ArrayList<String> arrNumber = new ArrayList<String>();

		for (int i = 0; i < arrNewsModel.size(); i++) {
			arrNumber.add(arrNewsModel.get(i).getMovieid());
		}
		newsAdaptor = new NewsAdapter(ctx, arrNewsModel, objSPS, font,
				listener, arrNumber);
		lv_News.setAdapter(newsAdaptor);
		lv_News.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// Start NewsDetail Activity and pass data to it.
				Intent i = new Intent(ctx, NewsDetailActivity.class);
				i.putExtra("IMAGEURL", arrNewsModel.get(pos).getImage());
				i.putExtra("REVIEWER", arrNewsModel.get(pos).getByline());
				i.putExtra("PUBLICATION_DATE", arrNewsModel.get(pos)
						.getPublication_date());
				i.putExtra("SUMMARY", arrNewsModel.get(pos).getSummary_short());
				i.putExtra("TITLE", arrNewsModel.get(pos).getDisplay_title());

				startActivity(i);
			}
		});
	}

	/*
	 * Function name:getData() Function Description:This method is used to parse
	 * response
	 */
	public void getData(Context context, ArrayList<NewsModel> arrNewsModel) {
		String jsonString = null;
		JSONObject jsonData = null;
		JSONArray resultArray = null;
		try {
			jsonString = MyApplication.NewsDataBundle
					.getString(Constants.MOVIE_DETAILS_DATA);// get data from
																// bundle
			MyApplication.NewsDataBundle.remove(Constants.MOVIE_DETAILS_DATA);// remove
			// data
			// from
			// bundle
		} catch (NullPointerException e1) {
			e1.printStackTrace();
			jsonString = null;
		}
		if (jsonString != null) {

			try {
				jsonData = new JSONObject(jsonString);
			} catch (JSONException e) {
				e.printStackTrace();
				jsonData = null;
			} catch (NullPointerException e) {
				e.printStackTrace();
				jsonData = null;
			}
			try {
				if (jsonData.getString("status").equals("OK")) {

					try {
						resultArray = jsonData.getJSONArray("results");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					Log.i("Length", "" + resultArray.length());

					for (int i = 0; i < resultArray.length(); i++) {
						JSONObject obj;
						JSONObject objMedia, objResource;
						String pic = null;
						NewsModel newsModel = null;

						try {
							obj = resultArray.getJSONObject(i);
							newsModel = new NewsModel();
							newsModel.setByline(obj.getString("byline"));
							newsModel.setDisplay_title(obj
									.getString("display_title"));
							newsModel.setMovieid(obj.getString("nyt_movie_id"));
							newsModel.setPublication_date(obj
									.getString("publication_date"));
							newsModel.setSummary_short(obj
									.getString("summary_short"));
							objMedia = obj.getJSONObject("multimedia");
							objResource = objMedia.getJSONObject("resource");
							pic = objResource.getString("src");
							newsModel.setImage(pic);
						} catch (JSONException e) {
							e.printStackTrace();
						}

						arrNewsModel.add(newsModel);

					}
					JSONObject json = new JSONObject();

					String arrayList = json.toString();

					System.out.println("Result array length => "
							+ resultArray.length());

				} else {
					MessageAlertDialog.showErrorAlert(
							"Something went wrong.Please try again!", context);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}

	}

	private void setParameter() {
		// initialize array of news model type
		arrNewsModel = new ArrayList<NewsModel>();
		// resolve ids
		tv_Title = (TextView) findViewById(R.id.tv_Title);
		rl_Header = (RelativeLayout) findViewById(R.id.rl_Header);
		lv_News = (ListView) findViewById(R.id.lv_News);
		// setting text size and typeface
		tv_Title.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				objSPS.getValueFromSharedPref(Constants.TEXT_HEADING_SIZE));
		tv_Title.setTypeface(font, Typeface.BOLD);
		// setting padding
		rl_Header.setPadding(
				objSPS.getValueFromSharedPref(Constants.PADDING_LEFT),
				objSPS.getValueFromSharedPref(Constants.PADDING_TOP) / 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_LEFT),
				objSPS.getValueFromSharedPref(Constants.PADDING_TOP) / 2);

	}
}
