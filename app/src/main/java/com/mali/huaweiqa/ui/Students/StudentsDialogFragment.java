package com.mali.huaweiqa.ui.Students;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.mali.huaweiqa.R;
import com.mali.huaweiqa.domain.users_profile.Student;
import com.mali.huaweiqa.domain.users_profile.Teacher;

import java.util.ArrayList;

public class StudentsDialogFragment extends DialogFragment {

    private static final String TAG = "MyCustomDialog";
    private StudentViewModel teacherViewModel;
//    public OnInputSelected mOnInputSelected;
    private Button bConfirm;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        teacherViewModel =
                new ViewModelProvider(this).get(StudentViewModel.class);
        View root = inflater.inflate(R.layout.student_main, container, false);
        final RecyclerView studentsList = root.findViewById(R.id.students_list);
        final StudentListAdapter adapter = new StudentListAdapter(true);
        bConfirm = root.findViewById(R.id.quizConfirmation);

        teacherViewModel.getStudents().observe(getViewLifecycleOwner(), new Observer<ArrayList<Student>>() {
            @Override
            public void onChanged(ArrayList<Student> students) {
                adapter.setStudents(students);
                studentsList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        // make the confirmation button visible
        bConfirm.setVisibility(View.VISIBLE);
        bConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(adapter.getSelectedStudents().get(0).getName());
                getDialog().dismiss();
            }
        });

        return root;

//        mActionCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: closing dialog");
//                getDialog().dismiss();
//            }
//        });
//
//        mActionOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: capturing input.");
//
//                String input = mInput.getText().toString();
//                if (!input.equals("")) {
////
////                    //Easiest way: just set the value.
////                    MainFragment fragment = (MainFragment) getActivity().getFragmentManager().findFragmentByTag("MainFragment");
////                    fragment.mInputDisplay.setText(input);
//
//                    mOnInputSelected.sendInput(input);
//                }
//
//
//                getDialog().dismiss();
//            }
//        });

    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            mOnInputSelected = (OnInputSelected) getTargetFragment();
//        } catch (ClassCastException e) {
//            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
//        }
//    }
//
//    public interface OnInputSelected {
//        void sendInput(String input);
//    }
}
