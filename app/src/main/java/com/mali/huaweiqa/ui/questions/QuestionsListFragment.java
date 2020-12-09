package com.mali.huaweiqa.ui.questions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.questions.Question;

import java.util.ArrayList;

public class QuestionsListFragment extends Fragment {

    private QuestionsListViewModel questionsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        questionsViewModel =
                new ViewModelProvider(this).get(QuestionsListViewModel.class);
        View root = inflater.inflate(R.layout.category_main, container, false);
        final ListView questionsList = root.findViewById(R.id.category_list);
        final QuestionsListAdapter adapter = new QuestionsListAdapter(getActivity().getBaseContext());

        // retrieve data on changes
        questionsViewModel.getQuestions(getArguments().getInt("CategoryIndex")).observe(getViewLifecycleOwner(), new Observer<ArrayList<Question>>() {
            @Override
            public void onChanged(ArrayList<Question> questions) {
                adapter.setQuestions(questions);
                questionsList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });


        questionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                System.out.println("Item: " + position + "was clicked");
                Bundle bundle = new Bundle();
                Navigation.findNavController(view).navigate(R.id.nav_questions, bundle);
            }});
        return root;
    }



}
