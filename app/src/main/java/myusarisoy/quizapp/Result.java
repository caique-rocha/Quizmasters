package myusarisoy.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Result extends AppCompatActivity {

    public ArrayList<String> scoreList = new ArrayList<>(10);

    public Button button1, button2, button3, button4, button5,
            button6, button7, button8, button9, button10;

    public TextView textViewScore;

    public Button buttonDetailedScore;

    public Bundle bundle;

    public int blankAnswer;

    public int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);

        buttonDetailedScore = findViewById(R.id.buttonDetailedScore);

        textViewScore = findViewById(R.id.textViewScore);

        bundle = getIntent().getExtras();

        score = bundle.getInt("score");

        textViewScore.setText("Score: " + score);

        scoreList = getIntent().getStringArrayListExtra("scoreList");

        if (scoreList.get(0).equals("true"))
            button1.setBackgroundColor(Color.rgb(0, 153, 0));
        else if (scoreList.get(0).equals("false"))
            button1.setBackgroundColor(Color.rgb(193, 37, 37));
        else if (scoreList.get(0).equals("")) {
            button1.setBackgroundColor(Color.rgb(0, 0, 150));
            blankAnswer += 1;
        }

        if (scoreList.get(1).equals("true"))
            button2.setBackgroundColor(Color.rgb(0, 153, 0));
        else if (scoreList.get(1).equals("false"))
            button2.setBackgroundColor(Color.rgb(193, 37, 37));
        else if (scoreList.get(1).equals("")) {
            button2.setBackgroundColor(Color.rgb(0, 0, 150));
            blankAnswer += 1;
        }

        if (scoreList.get(2).equals("true"))
            button3.setBackgroundColor(Color.rgb(0, 153, 0));
        else if (scoreList.get(2).equals("false"))
            button3.setBackgroundColor(Color.rgb(193, 37, 37));
        else if (scoreList.get(2).equals("")) {
            button3.setBackgroundColor(Color.rgb(0, 0, 150));
            blankAnswer += 1;
        }

        if (scoreList.get(3).equals("true"))
            button4.setBackgroundColor(Color.rgb(0, 153, 0));
        else if (scoreList.get(3).equals("false"))
            button4.setBackgroundColor(Color.rgb(193, 37, 37));
        else if (scoreList.get(3).equals("")) {
            button4.setBackgroundColor(Color.rgb(0, 0, 150));
            blankAnswer += 1;
        }

        if (scoreList.get(4).equals("true"))
            button5.setBackgroundColor(Color.rgb(0, 153, 0));
        else if (scoreList.get(4).equals("false"))
            button5.setBackgroundColor(Color.rgb(193, 37, 37));
        else if (scoreList.get(4).equals("")) {
            button5.setBackgroundColor(Color.rgb(0, 0, 150));
            blankAnswer += 1;
        }

        if (scoreList.get(4 + 1).equals("true"))
            button6.setBackgroundColor(Color.rgb(0, 153, 0));
        else if (scoreList.get(4 + 1).equals("false"))
            button6.setBackgroundColor(Color.rgb(193, 37, 37));
        else if (scoreList.get(4 + 1).equals("")) {
            button6.setBackgroundColor(Color.rgb(0, 0, 150));
            blankAnswer += 1;
        }

        if (scoreList.get(4 + 2).equals("true"))
            button7.setBackgroundColor(Color.rgb(0, 153, 0));
        else if (scoreList.get(4 + 2).equals("false"))
            button7.setBackgroundColor(Color.rgb(193, 37, 37));
        else if (scoreList.get(4 + 2).equals("")) {
            button7.setBackgroundColor(Color.rgb(0, 0, 150));
            blankAnswer += 1;
        }

        if (scoreList.get(4 + 3).equals("true"))
            button8.setBackgroundColor(Color.rgb(0, 153, 0));
        else if (scoreList.get(4 + 3).equals("false"))
            button8.setBackgroundColor(Color.rgb(193, 37, 37));
        else if (scoreList.get(4 + 3).equals("")) {
            button8.setBackgroundColor(Color.rgb(0, 0, 150));
            blankAnswer += 1;
        }

        if (scoreList.get(4 + 4).equals("true"))
            button9.setBackgroundColor(Color.rgb(0, 153, 0));
        else if (scoreList.get(4 + 4).equals("false"))
            button9.setBackgroundColor(Color.rgb(193, 37, 37));
        else if (scoreList.get(4 + 4).equals("")) {
            button9.setBackgroundColor(Color.rgb(0, 0, 150));
            blankAnswer += 1;
        }

        if (scoreList.get(4 + 5).equals("true"))
            button10.setBackgroundColor(Color.rgb(0, 153, 0));
        else if (scoreList.get(4 + 5).equals("false"))
            button10.setBackgroundColor(Color.rgb(193, 37, 37));
        else if (scoreList.get(4 + 5).equals("")) {
            button10.setBackgroundColor(Color.rgb(0, 0, 150));
            blankAnswer += 1;
        }

        buttonDetailedScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreIntent = new Intent(Result.this, Score.class);
                scoreIntent.putExtra("score", score);
                scoreIntent.putExtra("blankAnswer", blankAnswer);
                startActivity(scoreIntent);
            }
        });

    }

}