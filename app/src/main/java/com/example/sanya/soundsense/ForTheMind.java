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

public class ForTheMind extends AppCompatActivity implements AdapterView.OnItemClickListener, SeekBar.OnSeekBarChangeListener {
    ArrayAdapter<String> trackAdapter;
    int moodBarValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forthemind);

        // setup the seekbar change listener to filter the tracks
        SeekBar moodBar = (SeekBar) findViewById(R.id.mind_ratebar);
        moodBar.setOnSeekBarChangeListener(this);

        // setup the click on the image to start the heart activity
        ImageView mindIcon = (ImageView) findViewById(R.id.mindicon);
        mindIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeMood = new Intent(getApplicationContext(), ForTheHeart.class);
                startActivity(changeMood);
            }
        });


        // fill up the list with tracks
        ListView mindTracks = (ListView) findViewById(R.id.mindtracks);
        ArrayList<String> tracks = new ArrayList<String>();
        tracks.add("Track 01 - You are always on my mind");
        tracks.add("Track 02 - Free your mind");
        tracks.add("Track 03 - If you could read my mind");
        tracks.add("Track 04 - Windmills of your mind");

        trackAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tracks);
        mindTracks.setAdapter(trackAdapter);
        mindTracks.setOnItemClickListener(this);
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
        startPlayer.putExtra("mood", "mind");
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

