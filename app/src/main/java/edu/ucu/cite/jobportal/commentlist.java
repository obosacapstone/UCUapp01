package edu.ucu.cite.jobportal;

public class commentlist {
    public String heading;
    private String idcomment, idno, idpost ,comment, date, time, img,firstname,middlename,lastname;



    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getIdpost() {
        return idpost;
    }

    public void setIdpost(String idpost) {
        this.idpost = idpost;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(String idcomment) {
        this.idcomment = idcomment;
    }

    public commentlist(String idcomment, String idno, String idpost
            , String comment, String date, String time, String img, String firstname, String middlename, String lastname) {
        this.idcomment = idcomment;
        this.idno = idno;
        this.idpost = idpost;
        this.comment = comment;
        this.date = date;
        this.time = time;
        this.img = img;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;

    }
}
