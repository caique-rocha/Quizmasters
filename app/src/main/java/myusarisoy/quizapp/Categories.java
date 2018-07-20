package myusarisoy.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Categories extends AppCompatActivity {

    TextView textViewReady;

    Button buttonStartQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        textViewReady = findViewById(R.id.textViewReady);
        buttonStartQuiz = findViewById(R.id.buttonStartQuiz);

        // Fetch username data from the previous activity.
        textViewReady.setText(getIntent().getStringExtra("username") + "\nIf You're Ready,\nLet's Start the Quiz!");

        // Start quiz.
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Categories.this, Quiz.class);
                startActivity(intent);
            }
        });

    }

}