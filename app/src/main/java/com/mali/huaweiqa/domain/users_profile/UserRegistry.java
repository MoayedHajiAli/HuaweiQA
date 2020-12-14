package com.mali.huaweiqa.domain.users_profile;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import com.mali.huaweiqa.domain.Students_profile.Student;

public class UserRegistry {

    private static UserRegistry _instance;
    private String USER_LIST = "USER_LIST";
    private String STUDENTS_LIST = "STUDENTS_LIST";
    private final FirebaseDatabase database;
    private final DatabaseReference userListRef;
    private ArrayList<Student> allStudents;

    private UserRegistry(){
        // retrieve quizzes from the database
        this.database = FirebaseDatabase.getInstance();
        this.userListRef = database.getReference().child(USER_LIST).child(STUDENTS_LIST);
        this.allStudents = new ArrayList<>();
        loadStudents();
    }


    public static UserRegistry getInstance(){
        if(_instance == null){
            _instance = new UserRegistry();
        }
        return _instance;
    }

    private void loadStudents(){
        userListRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HashMap<String, String> tmp = (HashMap<String, String>) snapshot.getValue();
                    System.out.println(tmp);
                    addStudentToList(tmp.get("studentID"));
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void addStudentToList(String UserID){
        // obtain use from DB
        userListRef.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot);
                Student student = dataSnapshot.getValue(Student.class);
                if(student != null){
                    allStudents.add(student);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void addNewStudent(Student student){
        // save the user in the DB
        userListRef.child(student.getStudentID()).setValue(student);
    }

    public void getStudent(String UserID, String password, UserAuthenticationListener listener){
        // obtain use from DB
        userListRef.child(UserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot);
                Student student = dataSnapshot.getValue(Student.class);
                if(student == null){
                    listener.onWrongEmail();
                }
                else{
                    if(student.getPassword().equals(password))
                        listener.onAuthenticatedUser(student);
                    else
                        listener.onWrongPassword();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public ArrayList<Student> getStudents(){
        return this.allStudents;
    }
    public interface UserAuthenticationListener{
        void onAuthenticatedUser(Student student);
        void onWrongPassword();
        void onWrongEmail();
    }
}
