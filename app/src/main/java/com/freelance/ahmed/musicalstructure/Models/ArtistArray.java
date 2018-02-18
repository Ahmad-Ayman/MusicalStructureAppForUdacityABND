package com.freelance.ahmed.musicalstructure.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 2/17/2018.
 */

public class ArtistArray {
    @SerializedName("image")
    @Expose
    private List<ArtistData> artist = null;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("bio")
    @Expose
    private BioContent content;

    public List<ArtistData> getArtist() {
        return artist;
    }

    public void setArtist(ArrayList<ArtistData> artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BioContent getContent() {
        return content;
    }

    public void setContent(BioContent content) {
        this.content = content;
    }

    public class BioContent {

        @SerializedName("content")
        @Expose
        private String contentBio;


        public void setContentBio(String contentBio) {
            this.contentBio = contentBio;
        }

        public String getContentBio() {
            return contentBio;
        }
    }


}
