package myusarisoy.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class UsernameActivity extends AppCompatActivity {

    private EditText editTextUsername;

    public String username;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_username);

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            editTextUsername = findViewById(R.id.editTextUsername);
        }

    public void buttonNext(View view) {
        username = editTextUsername.getText().toString();

        if(username.equals(""))
            Toast.makeText(UsernameActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
        else {
            saveUsername(view);

            Intent intent = new Intent(UsernameActivity.this, Categories.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
    }

    public void saveUsername(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", 0); // Private Mode
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.apply();
    }

}