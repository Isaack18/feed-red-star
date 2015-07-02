package com.example.feed_red_star;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends Activity {

	private ArrayList<FeedItem> Feeds = new ArrayList<FeedItem>();
	private ListAdapter adapter = null;
	public static final int NUMBER_OF_POSTS = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		if (adapter == null) {
			adapter = new ListAdapter(this, 0, Feeds);
		}

		ListView list = (ListView) findViewById(R.id.listFeeds);
		list.setAdapter(adapter);

		FeedItem item = new FeedItem();
		item.setTitulo("Hola Mundo");
		Feeds.add(item);
		adapter.notifyDataSetChanged();

		if (isNetworkAvailable()) {
			Toast.makeText(this, "Red disponible", Toast.LENGTH_LONG).show();
			GetBlogPostsTask getBlogPostsTask = new GetBlogPostsTask();
			getBlogPostsTask.postsCount = NUMBER_OF_POSTS;
			getBlogPostsTask.execute();
		}
		else {
			Toast.makeText(this, "Red no disponible", Toast.LENGTH_LONG).show();
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	private boolean isNetworkAvailable() {
		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();

		boolean isAvailable = false;
		if (networkInfo != null && networkInfo.isConnected()) {
			isAvailable = true;
		}

		return isAvailable;
	}
}
