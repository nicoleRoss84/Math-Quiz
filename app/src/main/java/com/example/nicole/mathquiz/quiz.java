package com.example.nicole.mathquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class quiz extends AppCompatActivity {

    //class variables
    int top = 0;
    int bottom = 0;
    int answer = 0;
    int score = 0;
    int level = 1;
    int asked = 0;
    int questions = 10;
    ArrayList<String> types;

    //gets selections for quiz type and difficulty from main
    //calls newProblem method upon creation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            this.level = b.getInt("level");
            this.types = b.getStringArrayList("questionTypes");
            Log.d("OnCreate", "onCreate: this level: " + this.level);
        } else {
            Log.d("OnCreate", "onCreate: b is null");
        }
        TextView num = findViewById(R.id.numQuestions);
        num.setText(" " + questions);
        newProblem();
    }

    //submits the answer and tests users input
    //gives toast for correct and incorrect answers
    public void submitAnswer(View view) {
        EditText getAnswer = findViewById(R.id.get_answer);
        String userAnswer = getAnswer.getText().toString();
        if (userAnswer.length() > 0 && this.answer == Integer.parseInt(userAnswer)) {
            this.score++;
            Toast.makeText(this, "Correct Score: " + this.score, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect Score: " + this.score, Toast.LENGTH_SHORT).show();
        }
        if (this.asked < 10) {
            newProblem();
        } else {
            finishQuiz();
        }
    }

    //sends final score to Score class
    private void finishQuiz() {
        Intent i = new Intent(this, Score.class);
        Bundle b = new Bundle();
        b.putInt("correct", this.score);
        b.putInt("asked", this.asked);
        i.putExtras(b);
        startActivity(i);
    }

    //sets difficulty levels of problems
    private int randomInt() {
        int multiplier = 10;
        if (this.level > 1) {
            multiplier = 100;
        } else if (this.level > 2) {
            multiplier = 1000;
        }
        return (int) (Math.random() * multiplier) + 1;
    }

    //produces math problems
    private void newProblem() {
        String qType = getQuestionType();
        this.asked += 1;
        this.top = randomInt();
        this.bottom = randomInt();
        String symbol;
        if (qType.equals("multiplication")) {
            this.answer = this.top * this.bottom;
            symbol = "x";
        } else if (qType.equals("division")) {
            int t = this.top * this.bottom;
            this.answer = this.top;
            this.top = t;
            symbol = "\u00F7";
        } else if (qType.equals("subtraction")) {
            if (this.bottom > this.top) {
                int tmp = this.top;
                this.top = this.bottom;
                this.bottom = tmp;
            }
            this.answer = this.top - this.bottom;
            symbol = "-";
        } else {
            this.answer = this.top + this.bottom;
            symbol = "+";
        }
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        TextView vTop = findViewById(R.id.question_top);
        vTop.setText(formatter.format(this.top));
        TextView vBottom = findViewById(R.id.question_bottom);
        vBottom.setText(formatter.format(this.bottom));
        TextView vSymbol = findViewById(R.id.math_symbol);
        vSymbol.setText(symbol);
        EditText getAnswer = findViewById(R.id.get_answer);
        getAnswer.setText("");
        TextView vAsked = findViewById(R.id.numAsked);
        vAsked.setText("" + this.asked + " ");
    }

    //gets the questions types
    private String getQuestionType() {
        int tSize = this.types.size();
        int id = asked % tSize;
        return this.types.get(id);

    }


}
