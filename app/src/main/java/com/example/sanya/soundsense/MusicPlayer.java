package com.example.sanya.soundsense;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sanya on 2017.04.26..
 */

public class MusicPlayer extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    final static int PLAYBACK_TYPE_HEART = 1;
    final static int PLAYBACK_TYPE_MIND = 2;
    final static int PLAYBACK_TYPE_RANDOM = 3;
    final static int PLAYBACK_TYPE_LIST = 4;
    boolean playing = true;
    int playbackType = 0;
    int[] moodLogos = {0, R.drawable.soundsenselogo_heart, R.drawable.soundsenselogo_mind, R.drawable.soundsenselogo_random, R.drawable.soundsenselogo_playlist};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musicplayer);

        // get to know what intent started the player
        Intent prevIntent = getIntent();
        String trackName = prevIntent.getStringExtra("track");
        String mood = prevIntent.getStringExtra("mood");
        int moodRate = prevIntent.getIntExtra("rating", 0);


        final ImageView logo = (ImageView) findViewById(R.id.moodlogo);
        final ImageView moodChange = (ImageView) findViewById(R.id.moodchange);

        // set the playback type
        switch (mood) {
            case "heart":
                playbackType = PLAYBACK_TYPE_HEART;
                /* here comes the code that generates the playlist with the proper rating of hearts */
                break;
            case "mind":
                playbackType = PLAYBACK_TYPE_MIND;
                /* here comes the code that generates the playlist with the proper rating of minds*/
                break;
            case "*random*":
                playbackType = PLAYBACK_TYPE_RANDOM;
                /* here comes a code that chooses a random track from the list and generates a random playlist */
                trackName = "Track " + String.valueOf(((int) (Math.random() * 899) + 100)) + " - I'm so random";
                break;
            case "*selectlist*":
                playbackType = PLAYBACK_TYPE_LIST;
                /* here comes the code that set the playback order to the playlist, selected */
                trackName = "First track of playlist \'" + trackName + "\'";
                break;
            default:
                break;
        }

        // change the logo accordingly to the mood received
        logo.setImageResource(moodLogos[playbackType]);

        // display the trackname
        TextView trackNameView = (TextView) findViewById(R.id.trackname);
        trackNameView.setText(trackName);

        // listen to the playbutton clicks
        final ImageView playButton = (ImageView) findViewById(R.id.playbutton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playing = !playing;
                if (playing) {
                    playButton.setImageResource(R.mipmap.pause);
                } else {
                    playButton.setImageResource(R.mipmap.play);
                }
            }
        });

        // setup the heart and mind rating bar to rate the track
        SeekBar heartSeekBar = (SeekBar) findViewById(R.id.heart_ratebar);
        SeekBar mindSeekBar = (SeekBar) findViewById(R.id.mind_ratebar);
        heartSeekBar.setOnSeekBarChangeListener(this);
        mindSeekBar.setOnSeekBarChangeListener(this);

        // the option to add the currently played track to a playlist
        FloatingActionButton addToPlaylist = (FloatingActionButton) findViewById(R.id.addtoplaylist);
        addToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Will be added to an existing playlist, or will be able to create a new one.", Toast.LENGTH_SHORT).show();
            }
        });

        // if clicked on the logo, it rotates through the moods and play options
        ImageView imageMoodLogo = (ImageView) findViewById(R.id.moodlogo);
        imageMoodLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playbackType < 4) {
                    playbackType++;
                } else {
                    playbackType = 1;
                }
                logo.setImageResource(moodLogos[playbackType]);
                moodChange.setVisibility(View.VISIBLE);
                moodChange.setClickable(true);
                moodChange.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent moodChangeIntent = null;
                        switch (playbackType) {
                            case PLAYBACK_TYPE_HEART:
                                moodChangeIntent = new Intent(getApplication(), ForTheHeart.class);
                                break;
                            case PLAYBACK_TYPE_MIND:
                                moodChangeIntent = new Intent(getApplication(), ForTheMind.class);
                                break;
                            case PLAYBACK_TYPE_RANDOM:
                                moodChangeIntent = new Intent(getApplicationContext(), MusicPlayer.class);
                                moodChangeIntent.putExtra("track", "*random*");
                                moodChangeIntent.putExtra("mood", "*random*");
                                moodChangeIntent.putExtra("rating", 0);
                                startActivity(moodChangeIntent);
                                break;
                            case PLAYBACK_TYPE_LIST:
                                moodChangeIntent = new Intent(getApplicationContext(), SelectList.class);
                                break;
                            default:
                                break;
                        }
                        startActivity(moodChangeIntent);
                    }
                });
            }
        });
    }

    // mood rating changes
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Toast.makeText(getApplication(), "This change will be stored", Toast.LENGTH_SHORT).show();
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
