package myusarisoy.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizFragment extends Fragment {

    View root;

    public boolean isRunning = false;

    private int scoreCount;

    RequestQueue requestQueue;

    private ImageView imageView;

    private boolean booleanFirst, booleanSecond, booleanThird, booleanFourth;

    private TextView textViewScore, textViewTimer, textViewQuestion;

    private Button buttonFirst, buttonSecond, buttonThird, buttonFourth;

    private ArrayList<String> questionList = new ArrayList<>();
    private ArrayList<String> choiceList = new ArrayList<>();
    private ArrayList<Boolean> correctList = new ArrayList<>();
    public static ArrayList<String> scoreList = new ArrayList<>(10);

    private int[] quizImages = {  R.drawable.question1, R.drawable.question2
                                , R.drawable.question3, R.drawable.question4
                                , R.drawable.question5, R.drawable.question6
                                , R.drawable.question7, R.drawable.question8
                                , R.drawable.question9, R.drawable.question10};

    public int index = 0;

    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_quiz, container, false);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        textViewScore = root.findViewById(R.id.textViewScore);
        textViewScore.setText("Score: " + scoreCount);
        textViewTimer = root.findViewById(R.id.textViewTimer);
        textViewQuestion = root.findViewById(R.id.textViewQuestion);

        imageView = root.findViewById(R.id.imageQuiz);

        buttonFirst = root.findViewById(R.id.buttonFirst);
        buttonSecond = root.findViewById(R.id.buttonSecond);
        buttonThird = root.findViewById(R.id.buttonThird);
        buttonFourth = root.findViewById(R.id.buttonFourth);

        // Set your URL tto fetch API data.
        String url = "https://private-anon-a98702efdd-quizmasters.apiary-mock.com/questions";

        // Fetch data.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            for(int i = 0; i < response.length(); i++) {
                                JSONObject questionObject = response.getJSONObject(i);
                                String question = questionObject.getString("question");
                                questionList.add(question);
                                JSONArray choicesArray = questionObject.getJSONArray("choices");
                                for(int j = 0; j < choicesArray.length(); j++) {
                                    JSONObject currentObject = choicesArray.getJSONObject(j);
                                    String choice = currentObject.getString("choice");
                                    boolean correct = currentObject.getBoolean("correct");
                                    choiceList.add(choice);
                                    correctList.add(correct);
                                }
                                nextQuestion(questionList.lastIndexOf(index));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("myusarisoy", "" + error);
            }
        });
        requestQueue.add(jsonArrayRequest);

        // 100 seconds to complete the quiz.
        new CountDownTimer(100000,1000){
            @Override
            public void onTick(long millisUntilFinished){
                isRunning = true;
                textViewTimer.setText("Remaining Time: " + millisUntilFinished / 1000);
                if((millisUntilFinished / 1000) == 30)
                Toast.makeText(getActivity().getApplicationContext(), "Last " + 30 + " seconds!", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFinish(){
                isRunning = false;
                gotoResult();
            }
        }.start();

        // Click buttonFirst for the true answer.
        buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(booleanFirst == true) {
                    buttonFirst.setBackgroundColor(Color.GREEN);
                    scoreList.add("true");
                    buttonSecond.setEnabled(false);
                    buttonThird.setEnabled(false);
                    buttonFourth.setEnabled(false);
                    addScore();
                }
                // If first choice is false; set first button's background color 
                // as red, and set true button's background color as green.
                else {
                    buttonFirst.setBackgroundColor(Color.RED);

                    if(booleanSecond == true)
                        buttonSecond.setBackgroundColor(Color.GREEN);
                    else if(booleanThird == true)
                        buttonThird.setBackgroundColor(Color.GREEN);
                    else if(booleanFourth == true)
                        buttonFourth.setBackgroundColor(Color.GREEN);

                    scoreList.add("false");
                    buttonSecond.setEnabled(false);
                    buttonThird.setEnabled(false);
                    buttonFourth.setEnabled(false);
                }
                // 0.5 seconds to go to next question.
                new CountDownTimer(500,500){
                    @Override
                    public void onTick(long millisUntilFinished){}
                    @Override
                    public void onFinish(){
                        enableButton();
                        if(index == questionList.size() - 1){
                            gotoResult();
                        }
                        else{
                            nextQuestion(index);
                            index++;
                            buttonFirst.setBackgroundResource(R.color.buttonBackground);
                            buttonSecond.setBackgroundResource(R.color.buttonBackground);
                            buttonThird.setBackgroundResource(R.color.buttonBackground);
                            buttonFourth.setBackgroundResource(R.color.buttonBackground);
                        }
                    }
                }.start();
            }
        });

        // Click buttonSecond for the true answer.
        buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(booleanSecond == true) {
                    buttonSecond.setBackgroundColor(Color.GREEN);
                    scoreList.add("true");
                    buttonFirst.setEnabled(false);
                    buttonThird.setEnabled(false);
                    buttonFourth.setEnabled(false);
                    addScore();
                }
                // If second choice is false; set second button's background color 
                // as red, and set true button's background color as green.
                else {
                    buttonSecond.setBackgroundColor(Color.RED);

                    if(booleanFirst == true)
                        buttonFirst.setBackgroundColor(Color.GREEN);
                    else if(booleanThird == true)
                        buttonThird.setBackgroundColor(Color.GREEN);
                    else if(booleanFourth == true)
                        buttonFourth.setBackgroundColor(Color.GREEN);

                    scoreList.add("false");
                    buttonFirst.setEnabled(false);
                    buttonThird.setEnabled(false);
                    buttonFourth.setEnabled(false);
                }
                // 0.5 seconds to go to next question.
                new CountDownTimer(500,500){
                    @Override
                    public void onTick(long millisUntilFinished){}
                    @Override
                    public void onFinish(){
                        enableButton();
                        if(index == questionList.size() - 1){
                            gotoResult();
                        }
                        else{
                            nextQuestion(index);
                            index++;
                            buttonFirst.setBackgroundResource(R.color.buttonBackground);
                            buttonSecond.setBackgroundResource(R.color.buttonBackground);
                            buttonThird.setBackgroundResource(R.color.buttonBackground);
                            buttonFourth.setBackgroundResource(R.color.buttonBackground);
                        }
                    }
                }.start();
            }
        });

        // Click buttonThird for the true answer.
        buttonThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(booleanThird == true) {
                    buttonThird.setBackgroundColor(Color.GREEN);
                    scoreList.add("true");
                    buttonFirst.setEnabled(false);
                    buttonSecond.setEnabled(false);
                    buttonFourth.setEnabled(false);
                    addScore();
                }
                // If third choice is false; set third button's background color 
                // as red, and set true button's background color as green.
                else {
                    buttonThird.setBackgroundColor(Color.RED);

                    if(booleanFirst == true)
                        buttonFirst.setBackgroundColor(Color.GREEN);
                    else if(booleanSecond == true)
                        buttonSecond.setBackgroundColor(Color.GREEN);
                    else if(booleanFourth == true)
                        buttonFourth.setBackgroundColor(Color.GREEN);

                    scoreList.add("false");
                    buttonFirst.setEnabled(false);
                    buttonSecond.setEnabled(false);
                    buttonFourth.setEnabled(false);
                }
                // 0.5 seconds to go to next question.
                new CountDownTimer(500,500){
                    @Override
                    public void onTick(long millisUntilFinished){}
                    @Override
                    public void onFinish(){
                        enableButton();
                        if(index == questionList.size() - 1){
                            gotoResult();
                        }
                        else{
                            nextQuestion(index);
                            index++;
                            buttonFirst.setBackgroundResource(R.color.buttonBackground);
                            buttonSecond.setBackgroundResource(R.color.buttonBackground);
                            buttonThird.setBackgroundResource(R.color.buttonBackground);
                            buttonFourth.setBackgroundResource(R.color.buttonBackground);
                        }
                    }
                }.start();
            }
        });

        // Click buttonFourth for the true answer.
        buttonFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(booleanFourth == true) {
                    buttonFourth.setBackgroundColor(Color.GREEN);
                    scoreList.add("true");
                    buttonFirst.setEnabled(false);
                    buttonSecond.setEnabled(false);
                    buttonThird.setEnabled(false);
                    addScore();
                }
                // If fourth choice is false; set fourth button's background color 
                // as red, and set true button's background color as green.
                else {
                    buttonFourth.setBackgroundColor(Color.RED);

                    if(booleanFirst == true)
                        buttonFirst.setBackgroundColor(Color.GREEN);
                    else if(booleanSecond == true)
                        buttonSecond.setBackgroundColor(Color.GREEN);
                    else if(booleanThird == true)
                        buttonThird.setBackgroundColor(Color.GREEN);

                    scoreList.add("false");
                    buttonFirst.setEnabled(false);
                    buttonSecond.setEnabled(false);
                    buttonThird.setEnabled(false);
                }
                // 0.5 seconds to go to next question.
                new CountDownTimer(500,500){
                    @Override
                    public void onTick(long millisUntilFinished){}
                    @Override
                    public void onFinish(){
                        enableButton();
                        if(index == questionList.size() - 1){
                            gotoResult();
                        }
                        else{
                            nextQuestion(index);
                            index++;
                            buttonFirst.setBackgroundResource(R.color.buttonBackground);
                            buttonSecond.setBackgroundResource(R.color.buttonBackground);
                            buttonThird.setBackgroundResource(R.color.buttonBackground);
                            buttonFourth.setBackgroundResource(R.color.buttonBackground);
                        }
                    }
                }.start();
            }
        });

        return root;
    }

    // Set questions to textView and choices to button for each question.
    public void nextQuestion(int index) {
            index++;

            textViewQuestion.setText((index + 1) + ". " + questionList.get(index));
            buttonFirst.setText(choiceList.get(index * 4));
            buttonSecond.setText(choiceList.get((index * 4) + 1));
            buttonThird.setText(choiceList.get((index * 4) + 2));
            buttonFourth.setText(choiceList.get((index * 4) + 3));

            // Set specific image from array for each question.
            imageView.setImageResource(quizImages[index]);

            booleanFirst = correctList.get(index * 4);
            booleanSecond = correctList.get((index * 4) + 1);
            booleanThird = correctList.get((index * 4) + 2);
            booleanFourth = correctList.get((index * 4) + 3);
    }

    // If the choice is correct, add 10 score.
    public void addScore(){
        scoreCount += 10;
        textViewScore.setText("Score: " + scoreCount);
    }

    // After go to next question, set all buttons enabled.
    public void enableButton(){
        buttonFirst.setEnabled(true);
        buttonSecond.setEnabled(true);
        buttonThird.setEnabled(true);
        buttonFourth.setEnabled(true);
    }

    // Go to next activity, Result.
    public void gotoResult(){
        Intent resultIntent = new Intent(getActivity(), Result.class);
        resultIntent.putStringArrayListExtra("scoreList", scoreList);
        resultIntent.putExtra("score", scoreCount);
        startActivity(resultIntent);
    }

}