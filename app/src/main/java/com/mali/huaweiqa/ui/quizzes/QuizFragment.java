package com.mali.huaweiqa.ui.quizzes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.questions.Question;
import com.mali.huaweiqa.domain.quizzes.Quiz;
import com.mali.huaweiqa.domain.quizzes.QuizSession;

public class QuizFragment extends Fragment implements View.OnClickListener {

    private QuizSession quizSession;
    private TextView questionBody;
    private Button choice1, choice2, choice3, choice4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_question, container, false);
        this.questionBody = root.findViewById(R.id.question_body);
        this.choice1 = root.findViewById(R.id.first_choice);
        this.choice2 = root.findViewById(R.id.second_choice);
        this.choice3 = root.findViewById(R.id.third_choice);
        this.choice4 = root.findViewById(R.id.fourth_choice);
        this.choice1.setOnClickListener(this);
        this.choice2.setOnClickListener(this);
        this.choice3.setOnClickListener(this);
        this.choice4.setOnClickListener(this);

        quizSession = (QuizSession) getArguments().getSerializable("Quiz");
        System.out.println("Starting a quiz");
        quizSession.start();
        updateFields(quizSession.nextQuestion());
        System.out.println(quizSession.getCurrentQuestion());
        System.out.println(quizSession.getCurrentQuestion().getCorrectAnswerBody());
        return root;
    }

    @Override
    public void onClick(View v) {
        int chosenAnswer = -1;
        switch (v.getId()){
            case R.id.first_choice:
                chosenAnswer = 1;
                break;
            case R.id.second_choice:
                chosenAnswer = 2;
                break;
            case R.id.third_choice:
                chosenAnswer = 3;
                break;
            case R.id.fourth_choice:
                chosenAnswer = 4;
                break;
        }
        if(!quizSession.isFinished()){
            // update the fields to the next question
            System.out.println("Starting a new question");
            quizSession.evaluateAnswer(chosenAnswer);
            updateFields(quizSession.nextQuestion());
        }
        else{
            // finish the quiz
            Toast.makeText(getContext(), "You score is " + String.valueOf(quizSession.getScore()), Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        }

    }

    private void updateFields(Question question){
        this.questionBody.setText(question.getQuestionBody());
        this.choice1.setText(question.getChoices().get(0));
        this.choice2.setText(question.getChoices().get(1));
        this.choice3.setText(question.getChoices().get(2));
        this.choice4.setText(question.getChoices().get(3));
    }
}
