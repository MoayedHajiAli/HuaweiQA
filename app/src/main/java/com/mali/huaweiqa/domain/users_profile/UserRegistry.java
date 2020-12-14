package com.mali.huaweiqa.domain.users_profile;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.huawei.hms.hwid.A;

import java.util.ArrayList;

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
                System.out.println(dataSnapshot);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    System.out.println(snapshot);
                    if(snapshot != null){
                        Student student = snapshot.getValue(Student.class);
                        allStudents.add((student));
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public void addNewUser(User user){
        // save the user in the DB
        userListRef.child(user.getUserID()).setValue(user);
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
