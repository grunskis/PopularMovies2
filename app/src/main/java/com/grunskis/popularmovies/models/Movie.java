package com.grunskis.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class Movie implements Parcelable {
    private final int mId;
    private final String mTitle;
    private final Date mReleaseDate;
    private URL mPosterUrl;
    private final double mVoteAverage;
    private final String mPlot;

    public Movie(int id, String title, Date releaseDate, URL posterUrl, double voteAverage,
                 String plot) {
        mId = id;
        mTitle = title;
        mReleaseDate = releaseDate;
        mPosterUrl = posterUrl;
        mVoteAverage = voteAverage;
        mPlot = plot;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeLong(mReleaseDate.getTime());
        dest.writeString(mPosterUrl.toString());
        dest.writeDouble(mVoteAverage);
        dest.writeString(mPlot);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel src) {
            return new Movie(src);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private Movie(Parcel src) {
        mId = src.readInt();
        mTitle = src.readString();
        mReleaseDate = new Date(src.readLong());

        try {
            mPosterUrl = new URL(src.readString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        mVoteAverage = src.readDouble();
        mPlot = src.readString();
    }

    @Override
    public String toString() {
        return "Movie(title: '" + mTitle + "', posterUrl: '" + mPosterUrl.toString() + "')";
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getReleaseDate() {
        return mReleaseDate;
    }

    public URL getPosterUrl() {
        return mPosterUrl;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public String getPlot() {
        return mPlot;
    }
}
