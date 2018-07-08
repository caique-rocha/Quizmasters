package myusarisoy.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Categories extends AppCompatActivity {

    TextView tv;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        tv = findViewById(R.id.tvAsk);
        tv.setText(getIntent().getStringExtra("username") + ", \nLet's Start the Quiz!");
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Categories.this, Quiz.class);
                startActivity(i);
            }
        });
    }
}
