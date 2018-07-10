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

    ArrayList<String> scoreList = new ArrayList<>(10);

    public Button button1, button2, button3, button4, button5,
                  button6, button7, button8, button9, button10;

    TextView textViewScore;

    Button buttonDetailedScore;

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

        final int score = getIntent().getIntExtra("score", 0);
        textViewScore.setText("Score: " + score);

        scoreList = getIntent().getStringArrayListExtra("scoreList");

        button1.setText("1. " + scoreList.get(0));
        button2.setText("2. " + scoreList.get(1));
        button3.setText("3. " + scoreList.get(2));
        button4.setText("4. " + scoreList.get(3));
        button5.setText("5. " + scoreList.get(4));
        button6.setText("6. " + scoreList.get(4 + 1));
        button7.setText("7. " + scoreList.get(4 + 2));
        button8.setText("8. " + scoreList.get(4 + 3));
        button9.setText("9. " + scoreList.get(4 + 4));
        button10.setText("10. " + scoreList.get(4 + 5));

        buttonDetailedScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreIntent = new Intent(Result.this, Score.class);
                scoreIntent.putExtra("score", score);
                startActivity(scoreIntent);
            }
        });

    }

}
