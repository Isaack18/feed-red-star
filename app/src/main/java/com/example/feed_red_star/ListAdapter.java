package com.example.feed_red_star;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<FeedItem> {
	List<FeedItem> listItems;

	public ListAdapter(Context context, int textViewResourceId, List<FeedItem> items) {
		super(context, textViewResourceId, items);
		listItems = items;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		FeedItem item = getItem(position);
		if (view == null) {
			view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_feed, null);
		}

		TextView textTitulo = (TextView) view.findViewById(R.id.textTitulo);
		textTitulo.setText(item.getTitulo());

		return view;
	}
}
