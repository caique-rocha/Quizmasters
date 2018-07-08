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
import android.widget.EditText;
import android.widget.Toast;

public class UsernameActivity extends AppCompatActivity {

    //ActionBar aBar;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening);
        //display the logo during 1 second,
        new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                //set the new Content of your activity
                UsernameActivity.this.setContentView(R.layout.activity_username);
            }
        }.start();

        //aBar = getSupportActionBar();
        //aBar.setTitle("Quiz App");
        //aBar.setSubtitle("by @myusarisoy");
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("About").setIcon(R.drawable.about).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add("Exit").setIcon(R.drawable.exit).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = item.getTitle().toString();
        if(title.equals("About"))
            Toast.makeText(UsernameActivity.this, "Quiz App is an application that tests" + "\nyour information in specific categories.", Toast.LENGTH_LONG).show();
        if(title.equals("Exit"))
            finish();
        return super.onOptionsItemSelected(item);
    }
*/
    public void doOp(View view) {
        et = findViewById(R.id.etUname);
        String username = et.getText().toString();
        Intent i = new Intent(UsernameActivity.this, Categories.class);
        i.putExtra("username", username);
        startActivity(i);
    }
}
