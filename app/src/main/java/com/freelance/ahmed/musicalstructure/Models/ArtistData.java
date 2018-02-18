package com.freelance.ahmed.musicalstructure.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ahmed on 2/17/2018.
 */

public class ArtistData {

    @SerializedName("#text")
    @Expose
    private String imageLink;

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getImageLink() {
        return imageLink;
    }
}
