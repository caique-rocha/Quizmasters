package myusarisoy.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

public class Opening extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening);

        FirebaseApp.initializeApp(this);

        // Display the logo during 1 second.
        new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                // Set the new content.
                Intent intent = new Intent(Opening.this, UsernameActivity.class);
                startActivity(intent);
            }
        }.start();
    }
}