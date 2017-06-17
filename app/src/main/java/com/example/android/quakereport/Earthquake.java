package com.example.android.quakereport;

/**
 * Created by PWT on 2017/6/6.
 */

public class Earthquake {

    private double mMagnitude;

    private String mLocation;

    private long mTimeInMilliseconds;

    private String mUrl;

    public Earthquake(double mMagnitude, String mLocation, long mDate ,String url) {
        this.mMagnitude = mMagnitude;
        this.mLocation = mLocation;
        this.mTimeInMilliseconds = mDate;
        this.mUrl = url;
    }

    public double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
    public String getmUrl() {
        return mUrl;
    }
}
