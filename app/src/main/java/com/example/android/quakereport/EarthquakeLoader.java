package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

/**
 * Created by PWT on 2017/6/12.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private static final String TAG = "EarthquakeLoader";

    /** 查询 URL */
    private String mUrl;

    public EarthquakeLoader (Context context,String url){
        super(context);
        this.mUrl = url;
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.i(TAG, "loadInBackground: ");
        if(TextUtils.isEmpty(mUrl)){
            return null;
        }
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }

    @Override
    protected void onStartLoading() {
        Log.i(TAG, "onStartLoading: ");
        forceLoad();
    }
}
