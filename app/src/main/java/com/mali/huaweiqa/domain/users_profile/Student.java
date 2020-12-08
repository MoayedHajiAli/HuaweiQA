package com.mali.huaweiqa.domain.users_profile;

import java.io.File;

public class Student extends User{

    private Integer totalScore;

    public Student(String name, File imgFile) {
        super(name, imgFile);
    }

}
