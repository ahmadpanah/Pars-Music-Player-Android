package ir.pars.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView play,prev,next,coverArt;
    SeekBar mSeekBarTime,mSeekBarVol;
    TextView songTitle;
    MediaPlayer mMediaPlayer;
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.play_btn);
        prev = findViewById(R.id.prev_btn);
        next = findViewById(R.id.next_btn);
        mSeekBarTime = findViewById(R.id.seek_bar_time);
        mSeekBarVol = findViewById(R.id.seekbar_volume);
        coverArt = findViewById(R.id.cover_art);
        songTitle = findViewById(R.id.song_title);

        // creating an ArrayList to store our songs
        ArrayList<Integer> songs = new ArrayList<>();
        songs.add(0,R.raw.evan);
        songs.add(1,R.raw.ashoob);

        mMediaPlayer = MediaPlayer.create(getApplicationContext(), songs.get((currentIndex)));

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    play.setImageResource(R.drawable.ic_play_arrow_24);
                } else {
                    mMediaPlayer.start();
                    play.setImageResource(R.drawable.ic_pause_24);
                }
                SongNames();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMediaPlayer != null){
                    play.setImageResource(R.drawable.ic_pause_24);
                }
                if (currentIndex < songs.size() -1 ){
                    currentIndex++;
                } else {
                    currentIndex = 0;
                }
                if (mMediaPlayer.isPlaying()){
                    mMediaPlayer.stop();
                }
                mMediaPlayer = MediaPlayer.create(getApplicationContext(),songs.get(currentIndex));
                mMediaPlayer.start();
                SongNames();
            }

        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer != null){
                    play.setImageResource(R.drawable.ic_pause_24);
                }
                if (currentIndex > 0){
                    currentIndex--;
                } else {
                    currentIndex = songs.size() -1;
                }
                if (mMediaPlayer.isPlaying()){
                    mMediaPlayer.stop();
                }

                mMediaPlayer = MediaPlayer.create(getApplicationContext(),songs.get(currentIndex));
                mMediaPlayer.start();
                SongNames();
            }
        });
        };

    private void SongNames(){
        if (currentIndex == 0){
            songTitle.setText("Aroome Joon - Evan Band");
            coverArt.setImageResource(R.drawable.evan);
        } else if (currentIndex == 1){
            songTitle.setText("Ashoob - Ali Sedighi");
            coverArt.setImageResource(R.drawable.ashoob);
        }
    }
    }
