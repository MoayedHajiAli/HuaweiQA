package com.mali.huaweiqa.domain.users_profile;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import com.mali.huaweiqa.domain.users_profile.Student;

/**
 * Handles user registry and authentication
 */
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
        userListRef.child(encodeUserEmail(UserID)).addListenerForSingleValueEvent(new ValueEventListener() {
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

    /**
     * Register new student in the system
     * @param student
     */
    public void addNewStudent(Student student){
        // save the user in the DB
        userListRef.child(encodeUserEmail(student.getStudentID())).setValue(student);

    }

    /**
     * Authenticate a user login. In case of success it will call onAuthenticatedUser from the lister.
     * In case wrong email, or password it will it will call onWrongEmail, or onWrongPassword.
     * @param UserID
     * @param password
     * @param listener
     */
    public void getStudent(String UserID, String password, UserAuthenticationListener listener){
        // obtain use from DB
        userListRef.child(encodeUserEmail(UserID)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot);
                Student student = dataSnapshot.getValue(Student.class);
                if(student == null){
                    listener.onWrongEmail();
                }
                else{
                    if(password.equals("") || student.getPassword().equals(password))
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

    /**
     * Register a new user if it is not already in the system
     * @param student
     */
    public void addIfNotContained(Student student){
        // obtain use from DB
        Boolean doesContain;
        userListRef.child(encodeUserEmail(student.getStudentID())).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot);
                Student student = dataSnapshot.getValue(Student.class);
                if(student == null){
                    addNewStudent(student);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public static String encodeUserEmail(String userEmail) {
        userEmail =  userEmail.replace(".", ",");
        userEmail =  userEmail.replace("*", ",");
        return userEmail; }

    public static String decodeUserEmail(String userEmail) {
        return userEmail.replace(",", ".");
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
