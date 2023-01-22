package edu.ucu.cite.jobportal;

public class trendinglist {
    public String heading;
    private String id,idno,post,img,date,time,reactcount,commentcount,firstname,middlename,lastname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getReactcount() {
        return reactcount;
    }

    public void setReactcount(String reactcount) {
        this.reactcount = reactcount;
    }

    public String getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(String commentcount) {
        this.commentcount = commentcount;
    }

    public trendinglist(String id, String idno, String post, String img, String date, String time, String reactcount, String commentcount, String firstname, String middlename, String lastname) {
        this.id = id;
        this.idno = idno;
        this.post = post;
        this.img = img;
        this.date = date;
        this.time = time;
        this.reactcount = reactcount;
        this.commentcount = commentcount;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;

    }







}