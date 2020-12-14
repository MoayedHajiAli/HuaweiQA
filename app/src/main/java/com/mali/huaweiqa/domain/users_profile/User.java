package com.mali.huaweiqa.domain.users_profile;

import com.huawei.hms.hwid.A;
import com.mali.huaweiqa.domain.quizzes.Quiz;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class User {

    private String name, email, password;
    private File imgFile;
    private String userID;
    private UserType userType;

    public User(){}

    public User(String name, String userID) {
        this.name = name;
        this.userID = userID;
        this.email = "";
        this.password = "";
    }

    public User withEmail(String email){
        setEmail(email);
        return this;
    }

    public User withPassword(String password){
        setPassword(password);
        return this;
    }

    public User withImage(File imgFile){
        setImgFile(imgFile);
        return this;
    }




    // setter, getters for serializations

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public File getImgFile() {
        return imgFile;
    }

    public String getUserID() {
        return userID;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImgFile(File imgFile) {
        this.imgFile = imgFile;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
