package com.example.admin.loginmovie;

public class UserProfile {
    public String umur;
    public String useremail;
    public String username;

    public UserProfile() {

    }

    public UserProfile(String umur, String useremail, String username) {
        this.umur=umur;
        this.useremail=useremail;
        this.username=username;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
