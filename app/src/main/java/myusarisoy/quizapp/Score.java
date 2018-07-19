package myusarisoy.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    TextView textViewTrueAnswer, textViewFalseAnswer, textViewNullAnswer, textViewFinalScore;

    Button buttonStartAgain;

    public int trueQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        textViewTrueAnswer = findViewById(R.id.textViewTrueAnswer);
        textViewNullAnswer = findViewById(R.id.textViewNullAnswer);
        textViewFalseAnswer = findViewById(R.id.textViewFalseAnswer);
        textViewFinalScore = findViewById(R.id.textViewFinalScore);

        buttonStartAgain = findViewById(R.id.buttonStartAgain);

        int allQuestions = 10;
        trueQuestions = getIntent().getIntExtra("score", 0);
        final int blankAnswer = getIntent().getIntExtra("blankAnswer", 0);

        // View true answer(s).
        textViewTrueAnswer.setText("True Answer(s): " + (trueQuestions / 10));
        textViewTrueAnswer.setTextColor(Color.rgb(0, 100, 0));

        // View null answer(s).
        textViewNullAnswer.setText("Null Answer(s): " + blankAnswer);
        textViewNullAnswer.setTextColor(Color.rgb(0, 0, 150));

        // View false answer(s).
        textViewFalseAnswer.setText("Wrong Answer(s): " + (allQuestions - ((trueQuestions) / 10) - blankAnswer));
        textViewFalseAnswer.setTextColor(Color.rgb(139, 0, 0));

        // View total score.
        textViewFinalScore.setText("Total Score: " + trueQuestions);

        // Go to next activity, Scoreboard().
        buttonStartAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoScoreboard = new Intent(Score.this, Scoreboard.class);
                gotoScoreboard.putExtra("score", trueQuestions);
                startActivity(gotoScoreboard);
            }
        });

    }

}