package myusarisoy.quizapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuizQuestions extends Fragment {
    View root;
    TextView q, ans1, ans2, ans3,ans4;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_quiz_questions, container, false);
        q = root.findViewById(R.id.question);
        ans1 = root.findViewById(R.id.answer1);
        ans2 = root.findViewById(R.id.answer2);
        ans3 = root.findViewById(R.id.answer3);
        ans4 = root.findViewById(R.id.answer4);

        // Change data
        String question = getArguments().getString("question");
        String answer1 = getArguments().getString("answer1");
        String answer2 = getArguments().getString("answer2");
        String answer3 = getArguments().getString("answer3");
        String answer4 = getArguments().getString("answer4");

        q.setText(question);
        ans1.setText(answer1);
        ans2.setText(answer2);
        ans3.setText(answer3);
        ans4.setText(answer4);


        // If you want to access context from fragment, use getActivity()
        QuizActivity qa = (QuizActivity) getActivity();
        qa.importantTask();

        return root;
    }
}
