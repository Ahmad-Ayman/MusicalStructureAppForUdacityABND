package com.freelance.ahmed.musicalstructure.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 2/17/2018.
 */

public class TracksListArray {

    @SerializedName("track")
    @Expose
    private List<Track> tracks = null;


    public List<Track> getTracks() {
        return tracks;
    }


    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }


}
