package com.example.nicole.mathquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Score extends AppCompatActivity {
    //class variables
    int correct = 0;
    int asked = 10;

    //keeps track and gets score
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            this.asked = b.getInt("asked");
            this.correct = b.getInt("correct");
        }
        showScore();
    }

    //displays score
    public void showScore() {
        TextView num = findViewById(R.id.numCorrect);
        num.setText("" + this.correct);
        TextView vAsked = findViewById(R.id.numAsked);
        vAsked.setText("" + this.asked);
    }

    //restart quiz button
    public void restartQuiz(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
