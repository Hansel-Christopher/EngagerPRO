package com.example.hansel_christopher.engagerpro;

public class Complaint {
    public String id;
    public String title;
    public String dept;
    public String descr;
    public int num;
    public String uid;
    public String area;

    public Complaint(){

    }
    public Complaint(String title, String dept) {
        this.title = title;
        this.dept = dept;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDept(String dept){
        this.dept = dept;
    }

    public void setDescr(String descr){
        this.descr = descr;
    }
    public void setUid(String uid){
        this.uid = uid;
    }
    public String getTitle(){
        return this.title;
    }
    public String getUser(){
        return this.uid;
    }
    public String getDept() { return  this.dept; }
    public Complaint(String id, String title, String dept, String descr, int num, String uid) {
        this.id = id;
        this.title = title;
        this.dept = dept;
        this.descr = descr;
        this.num = num;
        this.uid = uid;
    }
}
