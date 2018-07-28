package com.example.yusuf.futurestep.data;

public class User {

    private String mUsername;
    private String mPassword;
    private String mName;
    private String mMobile;

    public User(String username, String password, String name, String mobile){

        mUsername = username;
        mPassword = password;
        mName = name;
        mMobile = mobile;
    }

    public User(){

        mUsername = this.mUsername;
        mPassword = this.mPassword;
        mName = this.mName;
        mMobile = this.mMobile;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getName() {
        return mName;
    }

    public String getMobile() {
        return mMobile;
    }
}
