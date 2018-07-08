package myusarisoy.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    TextView txtTrueAnswer, txtWrongAnswer, txtFinalScore;
    Button btnStartAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        int allQuestions = 10;
        int trueQuestions = getIntent().getIntExtra("score", 0);

        txtTrueAnswer = findViewById(R.id.txtTrueAnswer);
        txtTrueAnswer.setText("True Answer(s): " + (trueQuestions / 10));

        txtWrongAnswer = findViewById(R.id.txtWrongAnswer);
        txtWrongAnswer.setText("Wrong Answer(s): " + (allQuestions - (trueQuestions) / 10));

        txtFinalScore = findViewById(R.id.txtFinalScore);
        txtFinalScore.setText("Total Score: " + trueQuestions);

        btnStartAgain = findViewById(R.id.btnStartAgain);
        btnStartAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoStart = new Intent(Score.this, UsernameActivity.class);
                startActivity(gotoStart);
            }
        });
    }
}
