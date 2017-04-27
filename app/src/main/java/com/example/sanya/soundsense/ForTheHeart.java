package com.example.sanya.soundsense;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

/**
 * Created by sanya on 2017.04.25..
 */

public class ForTheHeart extends AppCompatActivity implements AdapterView.OnItemClickListener, SeekBar.OnSeekBarChangeListener {
    ArrayAdapter<String> trackAdapter;
    int moodBarValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fortheheart);

        // setup the seekbar change listener to filter the tracks
        SeekBar moodBar = (SeekBar) findViewById(R.id.heart_ratebar);
        moodBar.setOnSeekBarChangeListener(this);

        // setup the click on the image to start the mind activity
        ImageView heartIcon = (ImageView) findViewById(R.id.hearticon);
        heartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeMood = new Intent(getApplicationContext(), ForTheMind.class);
                startActivity(changeMood);
            }
        });

        // fill up the list with tracks
        ListView heartTracks = (ListView) findViewById(R.id.heartracks);
        ArrayList<String> tracks = new ArrayList<String>();
        tracks.add("Track 01 - Hungry heart");
        tracks.add("Track 02 - Total eclipse of the heart");
        tracks.add("Track 03 - Unbreak my heart");
        tracks.add("Track 04 - Heart of glass");

        trackAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tracks);
        heartTracks.setAdapter(trackAdapter);
        heartTracks.setOnItemClickListener(this);
    }

    /**
     * clicking on a track in the list starts the player with the track playing, also passes the mood and rating for creating a playlist
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedTrack = trackAdapter.getItem(position);
        Intent startPlayer = new Intent(this, MusicPlayer.class);
        startPlayer.putExtra("track", selectedTrack);
        startPlayer.putExtra("mood", "heart");
        startPlayer.putExtra("rating", moodBarValue);
        startActivity(startPlayer);
    }

    /**
     * sliding the seekbar changes the rating that is needed to filter the tracks
     *
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        moodBarValue = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // has to be implemented
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // has to be implemented
    }
}

