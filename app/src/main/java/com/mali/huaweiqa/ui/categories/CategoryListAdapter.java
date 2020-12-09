package com.mali.huaweiqa.ui.categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.questions.QuestionsCategory;
import com.mali.huaweiqa.domain.questions.QuestionsLibrary;
import com.mali.huaweiqa.domain.users_profile.Student;

import java.util.ArrayList;
import java.util.Locale;

public class CategoryListAdapter extends ArrayAdapter<Student> {

    private ArrayList<QuestionsCategory> categories;
    public CategoryListAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_catefory, parent, false);
        TextView nameView = rowView.findViewById(R.id.category_title);
        nameView.setText(categories.get(position).getCategoryTitle());
        return rowView;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    public void setCategories(ArrayList<QuestionsCategory> categories) {
        this.categories = categories;
    }
}
