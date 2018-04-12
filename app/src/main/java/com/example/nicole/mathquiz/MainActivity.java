package com.example.nicole.mathquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //sets the easy radio button by default
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioButton easy = findViewById(R.id.easy);
        easy.setChecked(true);
    }

    //checkbox and radio button selections
    //verifications of what quiz type and difficulty is checked
    //intents set for selections
    public void startQuiz(View view) {
        ArrayList<String> types = new ArrayList<>();
        CheckBox cBox = findViewById(R.id.add);
        if (cBox.isChecked()) {
            types.add("addition");
        }
        cBox = findViewById(R.id.subtract);
        if (cBox.isChecked()) {
            types.add("subtraction");
        }
        cBox = findViewById(R.id.multiply);
        if (cBox.isChecked()) {
            types.add("multiplication");
        }
        cBox = findViewById(R.id.divide);
        if (cBox.isChecked()) {
            types.add("division");
        }
        if (types.size() < 1) {
            Toast.makeText(this, "Must choose at least 1 quiz type", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioGroup levelGroup = findViewById(R.id.levels);
        int radioButtonID = levelGroup.getCheckedRadioButtonId();
        View radioButton = levelGroup.findViewById(radioButtonID);
        int idx = levelGroup.indexOfChild(radioButton);
        Intent i = new Intent(this, quiz.class);
        Bundle b = new Bundle();
        b.putInt("level", idx + 1);
        b.putStringArrayList("questionTypes", types);
        i.putExtras(b);
        startActivity(i);
    }


}
