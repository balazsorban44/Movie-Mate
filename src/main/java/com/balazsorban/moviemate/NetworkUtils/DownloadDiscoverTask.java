package com.balazsorban.moviemate.NetworkUtils;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class DownloadDiscoverTask extends AsyncTask<String, String, JSONObject> {

    @Override
    protected JSONObject doInBackground(String... strings) {
        String resultString = "";
        JSONObject resultJSON = null;
        String TMDB_BASE_URL="https://api.themoviedb.org/3/discover/movie",
                TMDB_PARAM_API="api_key",
                apiKey=strings[0],
                TMDB_PARAM_SORT="sort_by",
                sortBy="popularity.desc",
                TMDB_PARAM_ADULT="include_adult",
                adultVal="false",
                TMDB_PARAM_VIDEO="include_video",
                includeVid="false",
                TMDB_PARAM_PAGE="page",
                pageNum="1";
        Uri builtUri = Uri.parse(TMDB_BASE_URL).buildUpon()
                .appendQueryParameter(TMDB_PARAM_API,apiKey)
                .appendQueryParameter(TMDB_PARAM_SORT, sortBy)
                .appendQueryParameter(TMDB_PARAM_ADULT, adultVal)
                .appendQueryParameter(TMDB_PARAM_VIDEO, includeVid)
                .appendQueryParameter(TMDB_PARAM_PAGE,pageNum)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext()){
                resultString += scanner.next();
            }
            else return null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        try {
            return new JSONObject(resultString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultJSON;
    }
}
