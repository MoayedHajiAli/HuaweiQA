package com.mali.huaweiqa.ui.categories;

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
import com.mali.huaweiqa.domain.questions.QuestionsCategory;
import com.mali.huaweiqa.domain.questions.QuestionsLibrary;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {

    private CategoryViewModel categoryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoryViewModel =
                new ViewModelProvider(this).get(CategoryViewModel.class);
        View root = inflater.inflate(R.layout.category_main, container, false);
        final ListView categoriesList = root.findViewById(R.id.category_list);
        final CategoryListAdapter adapter = new CategoryListAdapter(getActivity().getBaseContext());

        // retrieve data on changes
        categoryViewModel.getCategories().observe(getViewLifecycleOwner(), new Observer<ArrayList<QuestionsCategory>>() {
            @Override
            public void onChanged(ArrayList<QuestionsCategory> categories) {
                adapter.setCategories(categories);
                categoriesList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });


        // move to the list of questions upon the click on the category
        categoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                System.out.println("Item: " + position + "was clicked");
                Bundle bundle = new Bundle();
                bundle.putInt("CategoryIndex", position);
                Navigation.findNavController(view).navigate(R.id.nav_questions, bundle);
            }});
        return root;
    }



}
