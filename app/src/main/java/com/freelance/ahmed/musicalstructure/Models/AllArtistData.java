package com.freelance.ahmed.musicalstructure.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 2/17/2018.
 */

public class AllArtistData {
    @SerializedName("artist")
    @Expose
    private ArtistArray artistListArray;

    public ArtistArray getArtistListArray() {
        return artistListArray;
    }

    public void setArtistListArray(ArtistArray artistListArray) {
        this.artistListArray = artistListArray;
    }
}
