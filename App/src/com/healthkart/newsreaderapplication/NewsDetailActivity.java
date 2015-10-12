package com.healthkart.newsreaderapplication;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.healthkart.newsreaderapplication.Utility.SharePreferanceWrapperSingleton;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * @author Neelam Kushwah
 * @date 09-10-2015
 * **/
public class NewsDetailActivity extends Activity {
	private SharePreferanceWrapperSingleton objSPS;
	private ImageView iv_Pic;
	private TextView tv_Title, tv_MovieName, tv_MovieNameValue,
			tv_ReviewerName, tv_ReviewerValue, tv_PublicationDate,
			tv_PublicationDateValue, tv_Summary;
	private Typeface font;
	private DisplayImageOptions options;
	private String imageUrl, summary, date, name, title;
	private RelativeLayout rl_Header, rl_MovieTitle, rl_Reviewer,
			rl_Publication, rlTop;
	private RelativeLayout.LayoutParams param;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_news_detail);
		objSPS = SharePreferanceWrapperSingleton.getSingletonInstance();
		objSPS.setPref(this);
		objSPS.setEditor();
		font = (Typeface.createFromAsset(getAssets(), "AlegreyaSans-Light.ttf"));
		// initialize object of DisplayImageOption class.This is used to load
		// image from url by using universal image loader library
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.background)
				.showImageForEmptyUri(R.drawable.background)
				.showImageOnFail(R.drawable.background).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(5)).build();
		// get data which is send by NewsReader Activity
		imageUrl = getIntent().getExtras().getString("IMAGEURL");
		name = getIntent().getExtras().getString("REVIEWER");
		date = getIntent().getExtras().getString("PUBLICATION_DATE");
		summary = getIntent().getExtras().getString("SUMMARY");
		title = getIntent().getExtras().getString("TITLE");

		setParameter();		// set all parameters of views.

	}

	private void setParameter() {
		//resolve ids
		tv_Title = (TextView) findViewById(R.id.tv_Title);
		rl_Header = (RelativeLayout) findViewById(R.id.rl_Header);
		rl_MovieTitle = (RelativeLayout) findViewById(R.id.rl_MovieTitle);
		rl_Publication = (RelativeLayout) findViewById(R.id.rl_Publication);
		tv_ReviewerName = (TextView) findViewById(R.id.tv_ReviewerName);
		tv_ReviewerValue = (TextView) findViewById(R.id.tv_ReviewerValue);
		rl_Reviewer = (RelativeLayout) findViewById(R.id.rl_Reviewer);
		rlTop = (RelativeLayout) findViewById(R.id.rlTop);
		iv_Pic = (ImageView) findViewById(R.id.iv_Pic);
		tv_MovieName = (TextView) findViewById(R.id.tv_MovieName);
		tv_MovieNameValue = (TextView) findViewById(R.id.tv_MovieNameValue);
		tv_PublicationDate = (TextView) findViewById(R.id.tv_PublicationDate);
		tv_PublicationDateValue = (TextView) findViewById(R.id.tv_PublicationDateValue);
		tv_Summary = (TextView) findViewById(R.id.tv_Summary);
		
		//setting text size
		tv_Title.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				objSPS.getValueFromSharedPref(Constants.TEXT_HEADING_SIZE));
		tv_MovieName.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
		tv_MovieNameValue.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
		tv_ReviewerName.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
		tv_ReviewerValue.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
		tv_PublicationDate.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
		tv_PublicationDateValue.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
		tv_Summary.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				objSPS.getValueFromSharedPref(Constants.TEXT_SIZE));
		//setting height
		iv_Pic.getLayoutParams().height = (int) (objSPS
				.getValueFromSharedPref(Constants.SCREEN_HEIGHT) * .35);
		//setting type face
		tv_Title.setTypeface(font, Typeface.BOLD);
		tv_MovieName.setTypeface(font, Typeface.BOLD);
		tv_MovieNameValue.setTypeface(font, Typeface.BOLD);
		tv_ReviewerValue.setTypeface(font, Typeface.BOLD);
		tv_ReviewerName.setTypeface(font, Typeface.BOLD);
		tv_PublicationDate.setTypeface(font, Typeface.BOLD);

		tv_PublicationDateValue.setTypeface(font, Typeface.BOLD);
		tv_Summary.setTypeface(font, Typeface.BOLD);
//setting padding
		rl_Header.setPadding(
				objSPS.getValueFromSharedPref(Constants.PADDING_LEFT) * 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_TOP) / 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_LEFT) * 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_TOP) / 2);
		rl_MovieTitle.setPadding(
				objSPS.getValueFromSharedPref(Constants.PADDING_LEFT) * 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_TOP) / 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_LEFT) * 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_TOP) / 2);
		rl_Reviewer.setPadding(
				objSPS.getValueFromSharedPref(Constants.PADDING_LEFT) * 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_TOP) / 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_LEFT) * 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_TOP) / 2);
		rl_Publication.setPadding(
				objSPS.getValueFromSharedPref(Constants.PADDING_LEFT) * 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_TOP) / 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_LEFT) * 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_TOP) / 2);
		tv_Summary.setPadding(
				objSPS.getValueFromSharedPref(Constants.PADDING_LEFT) * 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_TOP) * 5,
				objSPS.getValueFromSharedPref(Constants.PADDING_LEFT) * 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_TOP) * 2);
		rlTop.setPadding(objSPS.getValueFromSharedPref(Constants.PADDING_LEFT),
				objSPS.getValueFromSharedPref(Constants.PADDING_TOP) / 2,
				objSPS.getValueFromSharedPref(Constants.PADDING_LEFT),
				objSPS.getValueFromSharedPref(Constants.PADDING_TOP) / 2);
		//setting margin
		param = (RelativeLayout.LayoutParams) iv_Pic.getLayoutParams();
		param.bottomMargin = objSPS
				.getValueFromSharedPref(Constants.PADDING_TOP);
		iv_Pic.setLayoutParams(param);
		param = (RelativeLayout.LayoutParams) tv_MovieNameValue
				.getLayoutParams();
		param.leftMargin = objSPS
				.getValueFromSharedPref(Constants.PADDING_LEFT);
		tv_MovieNameValue.setLayoutParams(param);
		//load image from url
		try {
			ImageLoader.getInstance().displayImage(imageUrl, iv_Pic, options);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//setting text 
		tv_MovieNameValue.setText(title);
		tv_PublicationDateValue.setText(date);
		tv_ReviewerValue.setText(name);
		tv_Summary.setText(summary);
	}
}
