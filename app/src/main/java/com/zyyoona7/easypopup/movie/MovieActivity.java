package com.zyyoona7.easypopup.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.alphamovie.lib.AlphaMovieView;
import com.zyyoona7.easypopup.R;

public class MovieActivity extends AppCompatActivity {
    private AlphaMovieView alphaMovieView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        alphaMovieView = (AlphaMovieView) findViewById(R.id.video_player);
        alphaMovieView.setVideoFromAssets("nobelopen.webm");
        alphaMovieView.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        alphaMovieView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        alphaMovieView.onPause();
    }
}
