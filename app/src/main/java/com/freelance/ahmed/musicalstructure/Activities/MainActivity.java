package com.freelance.ahmed.musicalstructure.Activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.freelance.ahmed.musicalstructure.Adapters.TracksAdapter;
import com.freelance.ahmed.musicalstructure.ApiClients.ApiClient;
import com.freelance.ahmed.musicalstructure.BuildConfig;
import com.freelance.ahmed.musicalstructure.Interfaces.ApiInterface;
import com.freelance.ahmed.musicalstructure.Models.AllTracksData;
import com.freelance.ahmed.musicalstructure.Models.Track;
import com.freelance.ahmed.musicalstructure.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rView;
    private LinearLayoutManager lLayout;
    private TracksAdapter rvAdapter;
    private ProgressBar mLoadingIndicator;
    private TextView error;
    private SwipeRefreshLayout swiping;
    ApiInterface apiInterface;
    int lastFirstVisiblePosition;
    private final static String METHOD = "geo.gettoptracks";
    private final static String COUNTRY = "egypt";
    private final static String FORMAT = "json";
    private final static String API_KEY = BuildConfig.API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(getResources().getString(R.string.titleofmain));
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        error = (TextView) findViewById(R.id.errormsg);
        rView = (RecyclerView) findViewById(R.id.rv_tracks);
        swiping = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        lLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rView.setLayoutManager(lLayout);
        getTracksDataFromRetrofit();
        swiping.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getTracksDataFromRetrofit();
                        rView.setVisibility(View.VISIBLE);
                        swiping.setRefreshing(false);
                    }
                }
        );

    }

    @Override
    protected void onPause() {
        super.onPause();
        lastFirstVisiblePosition = ((LinearLayoutManager) rView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lastFirstVisiblePosition = ((LinearLayoutManager) rView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((LinearLayoutManager) rView.getLayoutManager()).scrollToPosition(lastFirstVisiblePosition);
        lastFirstVisiblePosition = 0;
    }

    private void getTracksDataFromRetrofit() {
        rView.setAdapter(null);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<AllTracksData> call = apiInterface.getAllTracks(METHOD, COUNTRY, API_KEY, FORMAT);
        mLoadingIndicator.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<AllTracksData>() {
            @Override
            public void onResponse(Call<AllTracksData> call, Response<AllTracksData> response) {

                List<Track> rlist = response.body().getTracksListArray().getTracks();

                if (rlist != null && !rlist.isEmpty()) {
                    rvAdapter = new TracksAdapter(MainActivity.this, rlist);
                    rView.setAdapter(rvAdapter);
                    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
                    itemAnimator.setAddDuration(1000);
                    itemAnimator.setRemoveDuration(1000);
                    rView.setItemAnimator(itemAnimator);
                    showTracksDataView();
                    Log.e(getResources().getString(R.string.showdatalog), getResources().getString(R.string.successLog));
                } else {
                    showError();
                    Log.e(getResources().getString(R.string.showdatalog), getResources().getString(R.string.errorLog));
                }
            }

            @Override
            public void onFailure(Call<AllTracksData> call, Throwable t) {
                Log.d(getResources().getString(R.string.failure), t.toString());
                showError();
            }
        });
    }


    private void showTracksDataView() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        error.setVisibility(View.INVISIBLE);
        rView.setVisibility(View.VISIBLE);

    }

    private void showError() {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        rView.setVisibility(View.INVISIBLE);
        error.setVisibility(View.VISIBLE);

    }
}
