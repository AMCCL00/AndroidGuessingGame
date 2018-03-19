package com.example.andrewmcclary.guessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity{

    private TextView mTextView;
    private TextView nTextView;
    private Button mButton;
    private EditText mEditText;
    private int randomNumber;
    private int numberOfGuesses = 4;
    public static final String TAG = "DIDWIN";
    public static final String CORRECTGUESS = "Correct Guess is ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing_screen);

        mButton = findViewById(R.id.submit_button);
        mEditText = findViewById(R.id.user_guess);
        mTextView = findViewById(R.id.guessing_directions);
        nTextView = findViewById(R.id.number_of_guesses);

        setListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startGame();
    }

    private void setListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mEditText.getText().toString();
                int guess = Integer.parseInt(input);
                checkGuess(guess);
                mEditText.setText("");
                numberOfGuesses --;
                nTextView.setText("You have " + (numberOfGuesses + 1) + " guesses left.");
            }
        });
    }

    private void checkGuess(int guess){
        boolean didWin = false;
        Intent intent = new Intent(GameActivity.this, ResultsActivity.class);
        if (guess == randomNumber) {
            didWin = true;
            intent.putExtra(TAG,didWin);
            startActivity(intent);
        }else if (guess < randomNumber){
            if (numberOfGuesses == 0){
                intent.putExtra(CORRECTGUESS, randomNumber);
                startActivity(intent);
            }else{
             mTextView.setText("Higher");
            }
        }else if(guess > randomNumber){
            if (numberOfGuesses == 0){
                intent.putExtra(CORRECTGUESS, randomNumber);
                startActivity(intent);
            }else{
                mTextView.setText("Lower");
            }
        }
    }

    private void startGame(){
        randomNumber = (int)Math.ceil(Math.random()*100);
        Log.d("@@@@", String.valueOf(randomNumber));
        mTextView.setText("Guess a Number");
        numberOfGuesses = 4;
        nTextView.setText("");
    }
}
