package com.example.feed_red_star;

import android.os.AsyncTask;

public class GetBlogPostsTask extends AsyncTask<Object, Void, String> {

	@Override
	protected String doInBackground(Object... arg0) {
		int responseCode = -1;

		return "Response Code: " + responseCode;
	}
}