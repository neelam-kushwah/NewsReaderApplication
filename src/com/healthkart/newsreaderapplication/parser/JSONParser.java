package com.healthkart.newsreaderapplication.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

/**
 * @ author name:Neelam Kushwah Class name:Json Parser Class Description:This
 * class is used to send request to server and get its response
 **/
public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	// constructor
	public JSONParser() {

	}

	// This method is used to get data from server
	public String getDataFromUrl(Context cntext, String urlWithParam)
			throws IOException {
		InputStream inputStream = null;
		String result = "";

		try {

			// 1. create HttpClient
			HttpParams httpParameters = new BasicHttpParams();

			// 1. create HttpClient
			HttpClient httpclient = new DefaultHttpClient(httpParameters);

			HttpConnectionParams.setConnectionTimeout(httpParameters,
					ServerConstant.TIMEOUT_CONNECTION);
			HttpConnectionParams.setSoTimeout(httpParameters,
					ServerConstant.TIMEOUT_SOCKET);
			// 2. make get request to the given URL
			HttpGet httpGet = new HttpGet(urlWithParam);

			HttpResponse httpResponse = httpclient.execute(httpGet);

			// 9. receive response as inputStream
			inputStream = httpResponse.getEntity().getContent();
			// 10. convert inputstream to string
			if (inputStream != null) {
				System.out.println("inputstream : " + inputStream);
				result = convertInputStreamToString(inputStream);
			} else {
				result = "Did not work!";
			}
			System.out.println("result : " + result);

		} catch (Exception e) {
			Log.d("InputStream", e.getLocalizedMessage());
			result = "timeout";
		}
		return result;
	}

	// This method is used to convert input stream to string
	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();
		return result;

	}

}
