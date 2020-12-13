package com.mali.huaweiqa.ui.questions;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.mali.huaweiqa.R;

import java.util.ArrayList;

public class QuestionFormDialog extends DialogFragment {

    private static final String TAG = "QuestionFormDialog";
    private ArrayList<EditText> choices = new ArrayList<>();
    private EditText questionBody;
    private Button bConfirm;
    private newQuestionListener listener;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.question_form, container, false);

        bConfirm = root.findViewById(R.id.questionFormConfirmation);
        choices.add(root.findViewById(R.id.choice1Form));
        choices.add(root.findViewById(R.id.choice2Form));
        choices.add(root.findViewById(R.id.choice3Form));
        choices.add(root.findViewById(R.id.choice4Form));
        questionBody = root.findViewById(R.id.questionBodyForm);

        bConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> choicesStr = new ArrayList<>();
                for(EditText ed : choices){
                    choicesStr.add(ed.getText().toString());
                }
                listener.onNewQuestion(questionBody.getText().toString(), choicesStr, -1);
                getDialog().dismiss();
            }
        });

        return root;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (newQuestionListener) getTargetFragment();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }
    }

    public interface newQuestionListener {
        void onNewQuestion(String questionBody, ArrayList<String> choices, int correctChoice);
    }

}
