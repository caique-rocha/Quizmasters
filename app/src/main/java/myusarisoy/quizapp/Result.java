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
    ArrayList<String> scores = new ArrayList<>();

    public Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10;

    TextView txtScore;

    Button btnScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn10 = findViewById(R.id.btn10);

        txtScore = findViewById(R.id.txtScore);
        txtScore.setText(getIntent().getStringExtra("score"));

        scores = getIntent().getStringArrayListExtra("scoreList");

        btnScore = findViewById(R.id.btnScore);
        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreIntent = new Intent(Result.this, Score.class);
                scoreIntent.putExtra("score", txtScore.getText().toString());
                startActivity(scoreIntent);
            }
        });
    }

}
