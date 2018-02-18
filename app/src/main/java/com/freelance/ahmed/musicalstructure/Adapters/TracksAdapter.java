package com.freelance.ahmed.musicalstructure.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freelance.ahmed.musicalstructure.Activities.DetailsActivity;
import com.freelance.ahmed.musicalstructure.Interfaces.ItemClickListner;
import com.freelance.ahmed.musicalstructure.Models.Track;
import com.freelance.ahmed.musicalstructure.Models.TracksListArray;
import com.freelance.ahmed.musicalstructure.R;

import java.util.List;

/**
 * Created by ahmed on 2/17/2018.
 */

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.TracksAdapterViewHolder> {
    private Context mContext;
    String trackName;
    String url;
    String artistName;
    String artistMbid;
    private final static String NAME_KEY = "NAME_KEY";
    private final static String URL_KEY = "URL_KEY";
    private final static String MBID_KEY = "MBID_KEY";
    private final static String TRACKNAME_KEY = "TRACK_KEY";
    List<Track> trackdata;

    public TracksAdapter(Context context, List<Track> tracksListArrays) {
        mContext = context;
        trackdata = tracksListArrays;
    }


    @Override
    public TracksAdapter.TracksAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tracks_list_item, null);
        TracksAdapter.TracksAdapterViewHolder rcv = new TracksAdapter.TracksAdapterViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(TracksAdapter.TracksAdapterViewHolder holder, int position) {

        trackName = trackdata.get(position).getTrackName();
        url = trackdata.get(position).getTrackUrl();
        artistName = trackdata.get(position).getTrackArtist().getArtistName();
        artistMbid = trackdata.get(position).getTrackArtist().getArtistMbid();
        holder.mTrackName.setText(trackName);
        holder.setItemClickListener(new ItemClickListner() {
            @Override
            public void onItemClick(View v, int pos) {
                trackName = trackdata.get(pos).getTrackName();
                artistName = trackdata.get(pos).getTrackArtist().getArtistName();
                artistMbid = trackdata.get(pos).getTrackArtist().getArtistMbid();
                url = trackdata.get(pos).getTrackUrl();
                Intent i = new Intent(mContext, DetailsActivity.class);
                i.putExtra(NAME_KEY, artistName);
                i.putExtra(MBID_KEY, artistMbid);
                i.putExtra(URL_KEY, url);
                i.putExtra(TRACKNAME_KEY, trackName);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (null == trackdata)
            return 0;
        return trackdata.size();
    }

    public void setTracksData(List<Track> tracksData) {

        this.trackdata = tracksData;
        notifyDataSetChanged();
    }

    public class TracksAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mTrackName;
        private ItemClickListner itemClickListener;

        public TracksAdapterViewHolder(View view) {
            super(view);
            mTrackName = (TextView) view.findViewById(R.id.tv_track);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(view, getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListner ic) {
            this.itemClickListener = ic;
        }
    }

}
