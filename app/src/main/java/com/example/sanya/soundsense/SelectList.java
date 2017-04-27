package com.example.sanya.soundsense;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by sanya on 2017.04.26..
 */

public class SelectList extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayAdapter<String> trackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectlist);

        // fill the list with playlists
        ListView listTracks = (ListView) findViewById(R.id.list_playlists);
        ArrayList<String> tracks = new ArrayList<String>();
        tracks.add("Something for the old");
        tracks.add("Something for the new");
        tracks.add("Something for the borrowed");
        tracks.add("Something for the blue");

        trackAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tracks);
        listTracks.setAdapter(trackAdapter);
        listTracks.setOnItemClickListener(this);
    }

    /**
     * a playlist is clicked, start the playback of the tracks it includes
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
        startPlayer.putExtra("mood", "*selectlist*");
        startActivity(startPlayer);
    }
}

