package myusarisoy.quizapp;

import android.content.Intent;
import android.content.res.Configuration;
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

    private CountDownTimer countDownTimer;

    private int scoreCount, trueCounter = 0, allQuestions = 0;

    RequestQueue requestQueue;

    private ImageView imageView;

    private boolean booleanFirst, booleanSecond, booleanThird, booleanFourth;

    private TextView textViewScore, textViewRate, textViewTimer, textViewQuestion;

    private Button buttonFirst, buttonSecond, buttonThird, buttonFourth;

    private ArrayList<String> questionList = new ArrayList<>();
    private ArrayList<String> choiceList = new ArrayList<>();
    private ArrayList<Boolean> correctList = new ArrayList<>();
    public static ArrayList<String> scores = new ArrayList<>(10);

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
        textViewRate = root.findViewById(R.id.textViewRate);
        textViewRate.setText("Questions: " + (scoreCount / 10) + " / " + allQuestions);
        textViewTimer = root.findViewById(R.id.textViewTimer);
        textViewQuestion = root.findViewById(R.id.textViewQuestion);

        imageView = root.findViewById(R.id.imageQuiz);

        buttonFirst = root.findViewById(R.id.buttonFirst);
        buttonSecond = root.findViewById(R.id.buttonSecond);
        buttonThird = root.findViewById(R.id.buttonThird);
        buttonFourth = root.findViewById(R.id.buttonFourth);

        // Set your URL tto fetch API data.
        String url = "http://private-67011-apiforquizmasters.apiary-mock.com/questions";

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

        // 10 seconds to complete each of the questions.
        countDownTimer = new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished){
                textViewTimer.setText("Remaining Time: " + millisUntilFinished / 1000);
            }
            @Override
            public void onFinish(){
                if(buttonFirst.isPressed() == false || buttonSecond.isPressed() == false
                        || buttonThird.isPressed() == false || buttonFourth.isPressed() == false) {
                    scores.add("");
                    buttonSecond.setEnabled(false);
                    buttonThird.setEnabled(false);
                    buttonFourth.setEnabled(false);

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
                                countDownTimer.cancel();
                                countDownTimer.start();
                            }
                        }
                    }.start();
                }
            }
        }.start();


        // Click buttonFirst for the true answer.
        buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(booleanFirst == true) {
                    buttonFirst.setBackgroundColor(Color.GREEN);
                    scores.add("true");
                    buttonSecond.setEnabled(false);
                    buttonThird.setEnabled(false);
                    buttonFourth.setEnabled(false);
                    trueCounter+=1;
                    addScore();
                }
                else if(booleanFirst == false) {
                    buttonFirst.setBackgroundColor(Color.RED);

                    if(booleanSecond == true)
                        buttonSecond.setBackgroundColor(Color.GREEN);
                    else if(booleanThird == true)
                        buttonThird.setBackgroundColor(Color.GREEN);
                    else if(booleanFourth == true)
                        buttonFourth.setBackgroundColor(Color.GREEN);

                    scores.add("false");
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
                            countDownTimer.cancel();
                            countDownTimer.start();
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
                    scores.add("true");
                    buttonFirst.setEnabled(false);
                    buttonThird.setEnabled(false);
                    buttonFourth.setEnabled(false);
                    trueCounter+=1;
                    addScore();
                }
                else if(booleanSecond == false) {
                    buttonSecond.setBackgroundColor(Color.RED);

                    if(booleanFirst == true)
                        buttonFirst.setBackgroundColor(Color.GREEN);
                    else if(booleanThird == true)
                        buttonThird.setBackgroundColor(Color.GREEN);
                    else if(booleanFourth == true)
                        buttonFourth.setBackgroundColor(Color.GREEN);

                    scores.add("false");
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
                            countDownTimer.cancel();
                            countDownTimer.start();
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
                    scores.add("true");
                    buttonFirst.setEnabled(false);
                    buttonSecond.setEnabled(false);
                    buttonFourth.setEnabled(false);
                    trueCounter+=1;
                    addScore();
                }
                else if(booleanThird == false) {
                    buttonThird.setBackgroundColor(Color.RED);

                    if(booleanFirst == true)
                        buttonFirst.setBackgroundColor(Color.GREEN);
                    else if(booleanSecond == true)
                        buttonSecond.setBackgroundColor(Color.GREEN);
                    else if(booleanFourth == true)
                        buttonFourth.setBackgroundColor(Color.GREEN);

                    scores.add("false");
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
                            countDownTimer.cancel();
                            countDownTimer.start();
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
                    scores.add("true");
                    buttonFirst.setEnabled(false);
                    buttonSecond.setEnabled(false);
                    buttonThird.setEnabled(false);
                    trueCounter+=1;
                    addScore();
                }
                else if(booleanFourth == false) {
                    buttonFourth.setBackgroundColor(Color.RED);

                    if(booleanFirst == true)
                        buttonFirst.setBackgroundColor(Color.GREEN);
                    else if(booleanSecond == true)
                        buttonSecond.setBackgroundColor(Color.GREEN);
                    else if(booleanThird == true)
                        buttonThird.setBackgroundColor(Color.GREEN);

                    scores.add("false");
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
                            countDownTimer.cancel();
                            countDownTimer.start();
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

            // Percentage of True (True Questions / All Questions)
            textViewRate.setText("Questions: " + trueCounter + " / " + index);

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
        Intent resultIntent = new Intent(getActivity().getApplicationContext(), Result.class);
        resultIntent.putStringArrayListExtra("scoreList", scores);
        resultIntent.putExtra("score", scoreCount);
        startActivity(resultIntent);
    }

}