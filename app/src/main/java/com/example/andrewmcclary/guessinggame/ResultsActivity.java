package com.example.andrewmcclary.guessinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    private TextView mTextview;
    private Button mButton;
    private boolean didWin;
    private int correctGuess;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        mTextview = findViewById(R.id.text_results);
        mButton = findViewById(R.id.button_play_again);
        mImageView = findViewById(R.id.image_results);
        didWin = getIntent().getExtras().getBoolean(GameActivity.TAG);
        correctGuess = getIntent().getExtras().getInt(GameActivity.CORRECT_GUESS);
        results();
        playAgain();
    }

    private void playAgain(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void results() {
        if (didWin){
            mTextview.setText("You Won!");
            mImageView.setImageDrawable(getResources().getDrawable(R.drawable.happy_face));
        }else if(!didWin){
            mTextview.setText("Sorry! You are out of guesses, The correct number was " + correctGuess + ".");
            mImageView.setImageDrawable(getResources().getDrawable(R.drawable.sadface));
        }
    }
}
