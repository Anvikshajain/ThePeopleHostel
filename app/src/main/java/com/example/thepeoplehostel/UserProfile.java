package com.example.thepeoplehostel;

class UserProfile {
    public String userName;
    public String userEmail;
    public String userEnroll;
    public String userCollege;
    public String userPhone;
    public String userRoom;
    public String userBranch;


    public UserProfile(){
    }

    public UserProfile(String uname, String uemail, String uenroll, String ucollege, String uphone, String uroom, String ubranch) {
        this.userName = uname;
        this.userEmail = uemail;
        this.userEnroll = uenroll;
        this.userCollege = ucollege;
        this.userPhone = uphone;
        this.userRoom = uroom;
        this.userBranch = ubranch;
    }

    /*public UserProfile(String email, String name, String phone, String room,String Enroll,String College,String Branch) {
        this.userName = name;
        this.userEmail = email;
        this.userPhone = phone;
        this.userRoom = room;
        this.userEnroll=Enroll;
        this.userCollege=College;
        this.userBranch=Branch;
    }*/

    public String getUserName() {
        return userName;
    }

    public String getUserEnroll() {
        return userEnroll;
    }

    public String getUserBranch() {
        return userBranch;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserEnroll(String userEnroll) {
        this.userEnroll = userEnroll;
    }

    public String getUserCollege() {
        return userCollege;
    }

    public void setUserCollege(String userCollege) {
        this.userCollege = userCollege;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserRoom() {
        return userRoom;
    }

    public void setUserRoom(String userRoom) {
        this.userRoom = userRoom;
    }

    public void setUserBranch(String userBranch) {
        this.userBranch = userBranch;
    }
}
