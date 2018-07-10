package myusarisoy.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    TextView textViewTrueAnswer, textViewWrongAnswer, textViewFinalScore;

    Button buttonStartAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        textViewTrueAnswer = findViewById(R.id.textViewTrueAnswer);
        textViewWrongAnswer = findViewById(R.id.textViewWrongAnswer);
        textViewFinalScore = findViewById(R.id.textViewFinalScore);

        buttonStartAgain = findViewById(R.id.buttonStartAgain);

        int allQuestions = 10;
        int trueQuestions = getIntent().getIntExtra("score", 0);

        textViewTrueAnswer.setText("True Answer(s): " + (trueQuestions / 10));
        textViewWrongAnswer.setText("Wrong Answer(s): " + (allQuestions - (trueQuestions) / 10));
        textViewFinalScore.setText("Total Score: " + trueQuestions);

        buttonStartAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoStart = new Intent(Score.this, UsernameActivity.class);
                startActivity(gotoStart);
            }
        });

    }

}
