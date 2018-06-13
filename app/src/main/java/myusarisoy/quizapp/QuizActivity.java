package myusarisoy.quizapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuizActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    ViewPager vp;

    public void apiQuestions(){
        String url = "https://private-anon-a98702efdd-quizmasters.apiary-mock.com/questions";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i = 0; i < response.length(); i++) {
                                JSONObject jrResponse = response.getJSONObject(i);
                                // Log.d("" + i, jrResponse.toString());

                                JSONArray choiceArray = jrResponse.getJSONArray("choices");

                                for(int j = 0; j < choiceArray.length(); j++) {
                                    JSONObject currentChoice = choiceArray.getJSONObject(j);
                                    // Log.d("" + j, currentChoice.toString());

                                    String choice = currentChoice.getString("choice");
                                    // boolean correct = currentChoice.getBoolean("correct");

                                    // textView.append(questionObject + "\n\n");
                                    // textView2.append(choice + "\n" + correct);
                                    // textView.append(jrResponse.toString());
                                    // textView2.append(choice.toString());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("myusarisoy", "Error...");
            }
        });
        requestQueue.add(request);
    }

    String [][] pageData = {};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        vp = findViewById(R.id.vp);

        FragmentPagerAdapter fpa = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                String question = pageData[position][0];
                String ans1 = pageData[position][1];
                String ans2 = pageData[position][2];
                String ans3 = pageData[position][3];
                String ans4 = pageData[position][4];

                Bundle b = new Bundle() ;
                b.putString("question", question);
                b.putString("answer1", ans1);
                b.putString("answer2", ans2);
                b.putString("answer3", ans3);
                b.putString("answer4", ans4);

                QuizQuestions qq = new QuizQuestions();
                qq.setArguments(b);
                return qq;
            }

            @Override
            public int getCount() {
                return pageData.length;
            }
        };

        // Set PagerAdapter to ViewPager
        vp.setAdapter(fpa);
    }

    public void importantTask() {
        Log.e("myusarisoy", "Important Task...");
    }

}
