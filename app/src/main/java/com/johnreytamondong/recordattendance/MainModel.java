package com.johnreytamondong.recordattendance;

public class MainModel {

    String Name, Course, Section, Email, Phone, turl;

    MainModel() {

    }

    public MainModel(String name, String course, String section, String email, String phone, String turl) {
        Name = name;
        Course = course;
        Section = section;
        Email = email;
        Phone = phone;
        this.turl = turl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public void getEmail(String email) {
        Email = email;
    }

    public String getEmail() {
        return Email;
    }

    public String getTurl() {
        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl;
    }

}