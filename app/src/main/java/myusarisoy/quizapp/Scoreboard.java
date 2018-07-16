package myusarisoy.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class Scoreboard extends AppCompatActivity {

    TextView textViewScore, textViewName;
    ListView listViewScores;
    BaseAdapter baseAdapter;
    LayoutInflater layoutInflater;

    ArrayList<GetScore> scoreList = new ArrayList<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);



        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("/Quizmasters");

        textViewScore = findViewById(R.id.textViewScore);
        textViewName = findViewById(R.id.textViewName);
        listViewScores = findViewById(R.id.listViewScores);
        layoutInflater = LayoutInflater.from(this);

        final int score = getIntent().getIntExtra("score", 0);
        textViewScore.setText(score + "");

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", 0); // Private Mode
        String name = sharedPreferences.getString("username", "");
        textViewName.setText(name + "");

        baseAdapter = new BaseAdapter()
        {
            public int getCount()
            {
                return scoreList.size();
            }

            public Object getItem(int i)
            {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup)
            {
                if (view == null)
                    view  = layoutInflater.inflate(android.R.layout.simple_list_item_1, null);

                TextView tv = view.findViewById(android.R.id.text1);

                GetScore getScore = scoreList.get(i);
                tv.setText(getScore.toString());

                return view;
            }
        };


        databaseReference.addValueEventListener(new ValueEventListener()
        {
            public void onDataChange(DataSnapshot wholeScoreEntry)
            {
                scoreList.clear();
                Iterator<DataSnapshot> iterator = wholeScoreEntry.getChildren().iterator();
                while (iterator.hasNext())
                {
                    GetScore scoreItem = iterator.next().getValue(GetScore.class);
                    scoreList.add(scoreItem);
                }

                baseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listViewScores.setAdapter(baseAdapter);
    }

    public void addItem(View view)
    {
        try
        {
            int total = new Integer(textViewScore.getText().toString());
            String name = textViewName.getText().toString();

            GetScore getScore = new GetScore(total, name);

            DatabaseReference newItemRef = databaseReference.push();
            newItemRef.setValue(getScore);

            new CountDownTimer(2000,1000){
                @Override
                public void onTick(long millisUntilFinished){
                    Toast.makeText(Scoreboard.this, "Score is added. Redirecting to main page.", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFinish(){
                    Intent intent = new Intent(Scoreboard.this, Categories.class);
                    startActivity(intent);
                    }
                }.start();
        } catch (Exception e) {
            Toast.makeText(this, "Error ...", Toast.LENGTH_SHORT).show();
        }

    }

}