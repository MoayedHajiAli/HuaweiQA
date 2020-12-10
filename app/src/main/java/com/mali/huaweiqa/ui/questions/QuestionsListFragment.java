package com.mali.huaweiqa.ui.questions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.questions.Question;
import com.mali.huaweiqa.ui.Students.StudentsDialogFragment;

import java.util.ArrayList;

public class QuestionsListFragment extends Fragment implements QuestionsListAdapter.QuestionListener {

    private QuestionsListViewModel questionsViewModel;

    private Button makeQuizButton;
    private RecyclerView questionsList;
    private QuestionsListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        questionsViewModel =
                new ViewModelProvider(this).get(QuestionsListViewModel.class);
        View root = inflater.inflate(R.layout.quiz_list_main, container, false);
        questionsList = root.findViewById(R.id.questionListView);
        makeQuizButton = root.findViewById(R.id.makeQuiz);
        adapter = new QuestionsListAdapter(this);

        // retrieve data on changes
        questionsViewModel.getQuestions(getArguments().getInt("CategoryIndex")).observe(getViewLifecycleOwner(), new Observer<ArrayList<Question>>() {
            @Override
            public void onChanged(ArrayList<Question> questions) {
                adapter.setQuestions(questions);
                questionsList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });


//        questionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view,
//                                    int position, long id) {
//                System.out.println("Item: " + position + "was clicked");
//                Bundle bundle = new Bundle();
//                Navigation.findNavController(view).navigate(R.id.nav_questions, bundle);
//            }});

        // make a quiz when clicking make quiz
        makeQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentsDialogFragment studentListDialog = new StudentsDialogFragment();
                studentListDialog.show(getParentFragmentManager(),"StudentListDialoge");
            }
        });
        return root;
    }

    @Override
    public void onMakeQuizAction(Boolean isSelected) {
        if(isSelected) {
            makeQuizButton.setVisibility(View.VISIBLE);
        }
        else{
            makeQuizButton.setVisibility(View.GONE);
        }
    }
}
