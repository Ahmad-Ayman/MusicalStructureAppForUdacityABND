package com.freelance.ahmed.musicalstructure.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahmed on 2/17/2018.
 */

public class AllTracksData {
    @SerializedName("tracks")
    @Expose
    private TracksListArray tracksListArray;

    public TracksListArray getTracksListArray() {
        return tracksListArray;
    }

    public void setTracksListArray(TracksListArray tracksListArray) {
        this.tracksListArray = tracksListArray;
    }
}



