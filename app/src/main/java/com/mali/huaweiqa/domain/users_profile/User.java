package com.mali.huaweiqa.domain.users_profile;

import java.io.File;
import java.util.UUID;

public class User {

    private String name;
    private File imgFile;
    private UUID userID;

    public User(String name, File imgFile) {
        this.name = name;
        this.imgFile = imgFile;
        this.userID = UUID.randomUUID();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgFile(File imgFile) {
        this.imgFile = imgFile;
    }

    public String getName() {
        return name;
    }

    public File getImgFile() {
        return imgFile;
    }
}
