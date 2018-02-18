package com.freelance.ahmed.musicalstructure.Interfaces;

import com.freelance.ahmed.musicalstructure.Models.AllArtistData;
import com.freelance.ahmed.musicalstructure.Models.AllTracksData;
import com.freelance.ahmed.musicalstructure.Models.AllTracksData;
import com.freelance.ahmed.musicalstructure.Models.TracksListArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ahmed on 2/17/2018.
 */

public interface ApiInterface {

    @GET("2.0/")
    Call<AllTracksData> getAllTracks(@Query("method") String method, @Query("country") String country, @Query("api_key") String Key, @Query("format") String format);

    @GET("2.0/")
    Call<AllArtistData> getAllArtistData(@Query("method") String method, @Query("mbid") String mbid, @Query("api_key") String Key, @Query("format") String format);
}
