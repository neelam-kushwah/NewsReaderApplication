package com.healthkart.newsreaderapplication.parser;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author name:Neelam Kushwah Class name:Server Constant Class Description:This
 *         class is used to save all server constants
 **/
public class ServerConstant {

	public static final String BASE_URL = "http://api.nytimes.com/svc/movies/v2/reviews/search?&api-key=";

	public static final int TIMEOUT_CONNECTION = 30000;
	public static final int TIMEOUT_SOCKET = 40000;

	/**
	 * Checking for all possible internet providers
	 * **/
	public static boolean isConnectingToInternet(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}

		}
		return false;
	}

}