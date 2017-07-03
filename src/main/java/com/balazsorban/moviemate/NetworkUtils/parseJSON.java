package com.balazsorban.moviemate.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class parseJSON {
    private JSONArray results;

    public parseJSON(String API_KEY) {
        try {
            results = new DownloadDiscoverTask().execute(API_KEY).get().getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public JSONArray getResults() throws JSONException {
        return results;
    }

    JSONObject getResult(int i) throws JSONException {
        return results.getJSONObject(i);
    }
}
