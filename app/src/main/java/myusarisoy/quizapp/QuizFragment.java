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

import java.util.ArrayList;

public class QuizFragment extends Fragment {

    boolean isRunning = false;
    int scoreCount;
//    int countDownTimer = 100;
    RequestQueue requestQueue;
    ImageView imageView;
    TextView txtScore, txtTimer, txtQuestion;
    Button btnFirst, btnSecond, btnThird, btnFourth;
    ArrayList<String> questionList = new ArrayList<>();
    ArrayList<String> choiceList = new ArrayList<>();
    ArrayList<Boolean> correctList = new ArrayList<>();
    private int[] quizImages = {  R.drawable.question1, R.drawable.question2
                                , R.drawable.question3, R.drawable.question4
                                , R.drawable.question5, R.drawable.question6
                                , R.drawable.question7, R.drawable.question8
                                , R.drawable.question9, R.drawable.question10};
    private String[] quizAnswers = { "1972", "1995", "September 23, 2008", "Pikachu", "Astana",
                                     "Gollum", "1004", "Ferrari", "Apple", "Android-Web"};
    public int index = 0;
    View root;

    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_quiz, container, false);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        this.txtScore = root.findViewById(R.id.txtScore);
        txtScore.setText("Score: " + scoreCount);
        txtTimer = root.findViewById(R.id.txtTimer);
        txtQuestion = root.findViewById(R.id.txtQuestion);
        imageView = root.findViewById(R.id.imageQuiz);
        btnFirst = root.findViewById(R.id.btnFirst);
        btnSecond = root.findViewById(R.id.btnSecond);
        btnThird = root.findViewById(R.id.btnThird);
        btnFourth = root.findViewById(R.id.btnFourth);
        String url = "https://private-anon-a98702efdd-quizmasters.apiary-mock.com/questions";

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

        new CountDownTimer(100000,1000){
            @Override
            public void onTick(long millisUntilFinished){
                isRunning = true;
                txtTimer.setText("Remaining Time: " + millisUntilFinished / 1000);
                if((millisUntilFinished / 1000) == 30)
                Toast.makeText(getActivity().getApplicationContext(), "Last " + 30 + " seconds!", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFinish(){
                isRunning = false;
                gotoResult();
            }
        }.start();

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnFirst.getText().toString().equals(quizAnswers[index])) {
                    btnFirst.setBackgroundColor(Color.GREEN);
                    //scoreList.add(index, "true");
                    upScore();
                    //resultAct.questionNumbers[index].setBackgroundColor(Color.GREEN);
                }
                else
                    btnFirst.setBackgroundColor(Color.RED);
                    //scoreList.add(index, "false");
                //resultAct.questionNumbers[index].setBackgroundColor(Color.RED);
                new CountDownTimer(500,500){
                    @Override
                    public void onTick(long millisUntilFinished){

                    }

                    @Override
                    public void onFinish(){
                        if(index == questionList.size() - 1){
                            /*Toast.makeText(getActivity(), "Final Score: " + scoreCount
                                    , Toast.LENGTH_LONG).show();*/
                            gotoResult();
                        }
                        else{
                            nextQuestion(index);
                            index++;
                            btnFirst.setBackgroundResource(R.color.buttonBackground);
                        }
                    }
                }.start();
            }
        });

        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnSecond.getText().toString().equals(quizAnswers[index])) {
                    btnSecond.setBackgroundColor(Color.GREEN);
                    //scoreList.add(index, "true");
                    upScore();
                    //resultAct.questionNumbers[index].setBackgroundColor(Color.GREEN);
                }
                else
                    btnSecond.setBackgroundColor(Color.RED);
                    //scoreList.add(index, "false");
                //resultAct.questionNumbers[index].setBackgroundColor(Color.RED);
                new CountDownTimer(500,500){
                    @Override
                    public void onTick(long millisUntilFinished){

                    }

                    @Override
                    public void onFinish(){
                        if(index == questionList.size() - 1){
                            /*Toast.makeText(getActivity(), "Final Score: " + scoreCount
                                    , Toast.LENGTH_LONG).show();*/
                            gotoResult();
                        }
                        else{
                            nextQuestion(index);
                            index++;
                            btnSecond.setBackgroundResource(R.color.buttonBackground);
                        }
                    }
                }.start();
            }
        });

        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnThird.getText().toString().equals(quizAnswers[index])) {
                    btnThird.setBackgroundColor(Color.GREEN);
                    //scoreList.add(index, "true");
                    upScore();
                   //resultAct.questionNumbers[index].setBackgroundColor(Color.GREEN);
                }
                else
                    btnThird.setBackgroundColor(Color.RED);
                    //scoreList.add(index, "false");
                //resultAct.questionNumbers[index].setBackgroundColor(Color.RED);
                new CountDownTimer(500,500){
                    @Override
                    public void onTick(long millisUntilFinished){

                    }

                    @Override
                    public void onFinish(){
                        if(index == questionList.size() - 1){
                            /*Toast.makeText(getActivity(), "Final Score: " + scoreCount
                                    , Toast.LENGTH_LONG).show();*/
                            gotoResult();
                        }
                        else{
                            nextQuestion(index);
                            index++;
                            btnThird.setBackgroundResource(R.color.buttonBackground);
                        }
                    }
                }.start();
            }
        });

        btnFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnFourth.getText().toString().equals(quizAnswers[index])) {
                    btnFourth.setBackgroundColor(Color.GREEN);
                    //scoreList.add(index, "true");
                    upScore();
                    //resultAct.questionNumbers[index].setBackgroundColor(Color.GREEN);
                }
                else
                    btnFourth.setBackgroundColor(Color.RED);
                    //scoreList.add(index, "false");
                //resultAct.questionNumbers[index].setBackgroundColor(Color.RED);
                new CountDownTimer(500,500){
                    @Override
                    public void onTick(long millisUntilFinished){

                    }

                    @Override
                    public void onFinish(){
                        if(index == questionList.size() - 1){
                            /*Toast.makeText(getActivity(), "Final Score: " + scoreCount
                                    , Toast.LENGTH_LONG).show();*/
                            gotoResult();
                        }
                        else{
                            nextQuestion(index);
                            index++;
                            btnFourth.setBackgroundResource(R.color.buttonBackground);
                        }
                    }
                }.start();
            }
        });

        /*txtQuestion = root.findViewById(R.id.txtQuestion);
        btnA = root.findViewById(R.id.btnFirst);
        btnB = root.findViewById(R.id.btnSecond);
        btnC = root.findViewById(R.id.btnThird);
        btnD = root.findViewById(R.id.btnFourth);

        // Change Data
        String question = getArguments().getString("question");
        String optionA = getArguments().getString("optionA");
        String optionB = getArguments().getString("optionB");
        String optionC = getArguments().getString("optionC");
        String optionD = getArguments().getString("optionD");

        // Set Data
        txtQuestion.setText(question);
        btnA.setText(optionA);
        btnB.setText(optionB);
        btnC.setText(optionC);
        btnD.setText(optionD);*/

        return root;
    }

    public void nextQuestion(int index) {
            index++;
            txtQuestion.setText(questionList.get(index));
            btnFirst.setText(choiceList.get(index * 4));
            btnSecond.setText(choiceList.get((index * 4) + 1));
            btnThird.setText(choiceList.get((index * 4) + 2));
            btnFourth.setText(choiceList.get((index * 4) + 3));
            imageView.setImageResource(quizImages[index]);

        /*Bundle b = new Bundle();
        b.putString("question", txtQuestion.toString());
        b.putString("optionA", btnFirst.toString());
        b.putString("optionB", btnSecond.toString());
        b.putString("optionC", btnThird.toString());
        b.putString("optionD", btnFourth.toString());

        qf.setArguments(b);*/
    }

    public void upScore(){
        scoreCount += 10;
        txtScore.setText("Score: " + scoreCount);
    }

    public void gotoResult(){
        Intent resultIntent = new Intent(getActivity(), Score.class);
        resultIntent.putExtra("score", scoreCount);
        //resultIntent.putStringArrayListExtra("totalScores", scoreList);
        startActivity(resultIntent);
    }
}
