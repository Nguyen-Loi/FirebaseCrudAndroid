package com.nguyenloi.crudfirebasetotal;

public class Student {
    private String image, userName, email ;
    private int number;

    public Student() {
    }

    public Student(String image, String userName, String email, int number) {
        this.image = image;
        this.userName = userName;
        this.email = email;
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
