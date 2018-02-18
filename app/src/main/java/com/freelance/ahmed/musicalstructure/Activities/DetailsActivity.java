package com.freelance.ahmed.musicalstructure.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.freelance.ahmed.musicalstructure.Adapters.TracksAdapter;
import com.freelance.ahmed.musicalstructure.ApiClients.ApiClient;
import com.freelance.ahmed.musicalstructure.BuildConfig;
import com.freelance.ahmed.musicalstructure.Interfaces.ApiInterface;
import com.freelance.ahmed.musicalstructure.Models.AllArtistData;
import com.freelance.ahmed.musicalstructure.Models.AllTracksData;
import com.freelance.ahmed.musicalstructure.Models.ArtistData;
import com.freelance.ahmed.musicalstructure.Models.Track;
import com.freelance.ahmed.musicalstructure.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity {
    private ImageView mPoster;
    private ImageView mPlay;
    private TextView mName;
    private TextView mBio;
    private final static String NAME_KEY = "NAME_KEY";
    private final static String URL_KEY = "URL_KEY";
    private final static String MBID_KEY = "MBID_KEY";
    private final static String TRACKNAME_KEY = "TRACK_KEY";
    String artistName, artistUrl, artistMbid, trackName, artistBio, artistPoster;
    ApiInterface apiInterface;
    private final static String METHOD = "artist.getinfo";
    private final static String FORMAT = "json";
    private final static String API_KEY = BuildConfig.API_KEY;
    private final static String BASE_POSTER_URL = "https://lastfm-img2.akamaized.net/i/u";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        artistName = i.getExtras().getString(NAME_KEY);
        artistUrl = i.getExtras().getString(URL_KEY);
        artistMbid = i.getExtras().getString(MBID_KEY);
        trackName = i.getExtras().getString(TRACKNAME_KEY);
        mPoster = findViewById(R.id.poster);
        mPlay = findViewById(R.id.openLink);
        mBio = findViewById(R.id.artistBio);
        mName = findViewById(R.id.artistName);
        mName.setText(artistName);
        this.setTitle(trackName);
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(artistUrl));
                startActivity(i);
            }
        });
        getArtistDataFromRetrofit();


    }

    private void getArtistDataFromRetrofit() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<AllArtistData> call = apiInterface.getAllArtistData(METHOD, artistMbid, API_KEY, FORMAT);
        call.enqueue(new Callback<AllArtistData>() {
            @Override
            public void onResponse(Call<AllArtistData> call, Response<AllArtistData> response) {

                List<ArtistData> rlist = response.body().getArtistListArray().getArtist();

                artistBio = response.body().getArtistListArray().getContent().getContentBio();

                if (rlist != null && !rlist.isEmpty()) {
                    artistPoster = rlist.get(3).getImageLink();
                    showArtistsDataView(artistPoster, artistBio);

                    Log.e(getResources().getString(R.string.showdatalog), getResources().getString(R.string.successLog));
                } else {

                    Log.e(getResources().getString(R.string.showdatalog), getResources().getString(R.string.errorLog));
                }
            }

            @Override
            public void onFailure(Call<AllArtistData> call, Throwable t) {
                Log.d(getResources().getString(R.string.failure), t.toString());

            }
        });

    }

    private void showArtistsDataView(String urlOfPoster, String bioo) {
        Picasso.with(this).load(urlOfPoster).fit()
                .placeholder(R.drawable.noimage).centerCrop()
                .error(R.drawable.error).centerCrop()
                .into(mPoster);
        mBio.setText(bioo);


    }
}
