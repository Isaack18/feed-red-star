package com.example.feed_red_star;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetBlogPostsTask extends AsyncTask<Object, Void, String> {
	private static final String TAG = GetBlogPostsTask.class.getSimpleName();

	private String url = "http://blog.teamtreehouse.com/api/get_recent_summary/?count=";
	public int postsCount = 10;

	@Override
	protected String doInBackground(Object... arg0) {
		int responseCode = -1;

		try {
			URL blogFeedUrl = new URL(url + postsCount);
			HttpURLConnection connection = (HttpURLConnection) blogFeedUrl.openConnection();
			connection.connect();

			responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				Reader reader = new InputStreamReader(inputStream);
				int contentLength = connection.getContentLength();
				char[] charArray = new char[contentLength];
				reader.read(charArray);
				String responseData = new String(charArray);

				Log.i(TAG, "Data: <<" + responseData + ">>");
			}
			else {
				Log.i(TAG, "Error HTTP Response Code: " + responseCode);
			}
		}
		catch (MalformedURLException e) {
			Log.e(TAG, "Excepción: ", e);
		}
		catch (IOException e) {
			Log.e(TAG, "Excepción: ", e);
		}
		catch (Exception e) {
			Log.e(TAG, "Excepción: ", e);
		}

		return "Response Code: " + responseCode;
	}
}