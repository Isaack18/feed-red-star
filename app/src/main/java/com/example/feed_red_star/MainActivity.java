package com.example.feed_red_star;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

	private ArrayList<FeedItem> Feeds = new ArrayList<FeedItem>();
	private ListAdapter adapter = null;

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
}
