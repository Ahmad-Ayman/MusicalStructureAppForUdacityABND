package com.freelance.ahmed.musicalstructure.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 2/17/2018.
 */

public class Track {
    @SerializedName("name")
    @Expose
    private String trackName;

    @SerializedName("url")
    @Expose
    private String trackUrl;

    @SerializedName("artist")
    @Expose
    private Artist trackArtist;


    public String getTrackName() {
        return trackName;
    }


    public void setTrackName(String name) {
        this.trackName = name;
    }


    public String getTrackUrl() {
        return trackUrl;
    }


    public void setName(String url) {
        this.trackUrl = url;
    }


    public Artist getTrackArtist() {
        return trackArtist;
    }


    public void setTrackArtist(Artist artist) {
        this.trackArtist = artist;
    }

    public class Artist {

        @SerializedName("name")
        @Expose
        private String artistName;

        @SerializedName("mbid")
        @Expose
        private String artistMbid;

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        public String getArtistMbid() {
            return artistMbid;
        }

        public void setArtistMbid(String artistMbid) {
            this.artistMbid = artistMbid;
        }
    }


}
