package com.example.feed_red_star;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetBlogPostsTask extends AsyncTask<Void, Void, Void> {
	private static final String TAG = GetBlogPostsTask.class.getSimpleName();
	private static final Integer NUMBER_OF_POSTS = 20;
	private static final String FEED_URL = "http://blog.teamtreehouse.com/api/get_recent_summary/?count=" + NUMBER_OF_POSTS.toString();

	private ArrayList<FeedItem> Feeds;
	private ListAdapter Adapter;

	public GetBlogPostsTask(ArrayList<FeedItem> list, ListAdapter adapter)
	{
		this.Feeds = list;
		this.Adapter = adapter;
	}

	@Override
	protected Void doInBackground(Void... params) {
		int responseCode;

		try {
			URL blogFeedUrl = new URL(FEED_URL);
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

				JSONObject jsonResponse = new JSONObject(responseData);
				String status = jsonResponse.getString("status");
				Log.v(TAG, status);

				JSONArray jsonPosts = jsonResponse.getJSONArray("posts");
				for (int i = 0; i < jsonPosts.length(); i++) {
					FeedItem item = new FeedItem();

					JSONObject jsonPost = jsonPosts.getJSONObject(i);
					item.setTitulo(jsonPost.getString("title"));

					Feeds.add(item);
				}
			}
			else {
				Log.i(TAG, "Código de respuesta HTTP erróneo: " + responseCode);
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

		return null;
	}

	@Override
	protected void onPostExecute(Void params) {
		Adapter.notifyDataSetChanged();
	}
}