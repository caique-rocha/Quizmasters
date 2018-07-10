package myusarisoy.quizapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UsernameActivity extends AppCompatActivity {

        EditText editTextUsername;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.opening);

            // Display the logo during 1 second.
            new CountDownTimer(1000,1000){
                @Override
                public void onTick(long millisUntilFinished){}

                @Override
                public void onFinish(){
                    // Set the new content.
                    UsernameActivity.this.setContentView(R.layout.activity_username);
                }
            }.start();

        }

        // Go to next activity, Categories.
        public void gotoNextActivity(View view) {
            editTextUsername = findViewById(R.id.editTextUsername);
            String username = editTextUsername.getText().toString();
            Intent intent = new Intent(UsernameActivity.this, Categories.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }

}
