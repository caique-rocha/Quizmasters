package myusarisoy.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Categories extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        tv = findViewById(R.id.tvAsk);
        tv.setText("Dear " + getIntent().getStringExtra("username") + ",\nLet's Start the Quiz!");
    }

    public void startQuiz(View view) {
        Intent i = new Intent(Categories.this, QuizActivity.class);
        startActivity(i);
    }
}
