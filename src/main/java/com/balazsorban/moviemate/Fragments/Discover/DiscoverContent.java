package com.balazsorban.moviemate.Fragments.Discover;

import com.balazsorban.moviemate.NetworkUtils.parseJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DiscoverContent {
    private JSONArray parsedDiscover;
    public static final List<DiscoverItem> ITEMS = new ArrayList<>();


    public DiscoverContent(String API_KEY) throws JSONException {
        parsedDiscover = new parseJSON(API_KEY).getResults();
        for (int i = 1; i < parsedDiscover.length(); i++) {
            addItem(createDiscoverItem(i));
        }

    }

    private static void addItem(DiscoverItem item) {
        ITEMS.add(item);
    }

    private DiscoverItem createDiscoverItem(int position) throws JSONException {

        return new DiscoverItem(parsedDiscover.getJSONObject(position));
    }
    public List<DiscoverItem> getItems(){
        return ITEMS;
    }

    public static class DiscoverItem {
        public final String id, title, overview, vote_average, poster_path;
        public DiscoverItem(JSONObject discoverItem) throws JSONException {
            this.id = discoverItem.getString("id");
            this.title = discoverItem.getString("title");
            this.overview = discoverItem.getString("overview");
            this.vote_average = discoverItem.getString("vote_average");
            this.poster_path = discoverItem.getString("poster_path");
        }


        @Override
        public String toString() {
            return title;
        }
    }
}
