package com.healthkart.newsreaderapplication.Utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.healthkart.newsreaderapplication.Constants;

/**
 * @author Neelam Kushwah
 */
@SuppressLint({ "CommitPrefEdits", "NewApi" })
public class SharePreferanceWrapperSingleton {

	private static SharedPreferences pref;
	private Editor editor;

	private static SharePreferanceWrapperSingleton singleton;

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private SharePreferanceWrapperSingleton() {
	}

	/* Static 'instance' method */
	public static SharePreferanceWrapperSingleton getSingletonInstance() {

		if (null == singleton) {
			singleton = new SharePreferanceWrapperSingleton();
		}
		return singleton;
	}

	public int getValueFromSharedPref(String key) {

		return pref.getInt(key, 0);
	}

	public void setValueToSharedPref(String key, int value) {
		editor.putInt(key, value);
		editor.commit();

	}

	public void setValueToSharedPref(String key, String value) {
		editor.putString(key, value);
		editor.commit();

	}

	public String getValueFromShared_Pref(String key) {

		return pref.getString(key, "");
	}

	public void setValueToSharedPref(String key, boolean value) {
		editor.putBoolean(key, value);
		editor.commit();

	}

	public void setValueToSharedPrefLong(String key, long value) {
		editor.putLong(key, value);
		editor.commit();

	}

	public boolean getBoolValueFromSharedPref(String key) {

		return pref.getBoolean(key, false);
	}

	public long getLongValueFromSharedPref(String key) {

		return pref.getLong(key, 0);
	}

	public void setPreferances(SharePreferanceWrapperSingleton objSPS,
			int heightScreen, int widthScreen) {

		objSPS.setValueToSharedPref(Constants.SCREEN_WIDTH, widthScreen);
		objSPS.setValueToSharedPref(Constants.SCREEN_HEIGHT, heightScreen);

		objSPS.setValueToSharedPref(Constants.TEXT_HEADING_SIZE,
				(int) (heightScreen * 0.033));
		objSPS.setValueToSharedPref(Constants.TEXT_SIZE,
				(int) (heightScreen * 0.03));
		objSPS.setValueToSharedPref(Constants.TEXT_SIZE_SMALL,
				(int) (heightScreen * 0.025));

		objSPS.setValueToSharedPref(Constants.PADDING_LEFT,
				(int) (widthScreen * 0.02));

		objSPS.setValueToSharedPref(Constants.PADDING_TOP,
				(int) (heightScreen * 0.02));

		this.editor.commit();
	}

	@SuppressWarnings("static-access")
	public void setPref(Context context) {
		this.pref = context.getSharedPreferences("SecurityAppPref",
				context.MODE_PRIVATE);
		;
	}

	public void setEditor() {
		this.editor = pref.edit();
		this.editor.commit();

	}

	public void setEditor1() {
		this.editor = pref.edit();
	}

}
