package com.mali.huaweiqa.ui.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.questions.Question;
import com.mali.huaweiqa.domain.questions.QuestionsCategory;
import com.mali.huaweiqa.domain.questions.QuestionsLibrary;

import java.util.ArrayList;
import java.util.Arrays;

public class CategoryFragment extends Fragment {

    private CategoryViewModel categoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // add dummy categories
        QuestionsLibrary.getInstance().addCategory("Science");
        QuestionsLibrary.getInstance().addCategory("Programming");
        QuestionsLibrary.getInstance().addCategory("Art");
        QuestionsLibrary.getInstance().addCategory("Math");
        QuestionsLibrary.getInstance().addCategory("Physics");
        QuestionsLibrary.getInstance().addCategory("General");

        categoryViewModel =
                new ViewModelProvider(this).get(CategoryViewModel.class);
        View root = inflater.inflate(R.layout.category_main, container, false);
        final ListView categoriesList = root.findViewById(R.id.category_list);
        final CategoryListAdapter adapter = new CategoryListAdapter(getActivity().getBaseContext());

        categoryViewModel.getCategories().observe(getViewLifecycleOwner(), new Observer<ArrayList<QuestionsCategory>>() {
            @Override
            public void onChanged(ArrayList<QuestionsCategory> categories) {
                adapter.setCategories(categories);
                categoriesList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        return root;
    }
}
