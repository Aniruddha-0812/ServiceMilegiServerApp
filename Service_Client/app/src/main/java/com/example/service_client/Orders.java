package com.example.service_client;

import com.google.firebase.Timestamp;

public  class Orders {

    private String job , first_name , last_name ,mobile , address , user_id;
    private Timestamp messageTime;
    private  boolean  is_complete;

   public Orders(){

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Orders(String job , Timestamp messageTime, String first_name, boolean isComplete , String last_name , String mobile , String address , String user_id){

        this.job = job;
        this.first_name = first_name;
        this.is_complete = isComplete;
        this.messageTime = messageTime;
        this.last_name = last_name;
        this.mobile = mobile;
        this.address = address;
        this.user_id = user_id;
    }


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    public boolean isIs_complete() {
        return is_complete;
    }

    public void setIs_complete(boolean is_complete) {
        this.is_complete = is_complete;
    }
}
