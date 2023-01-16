package edu.ucu.cite.jobportal;

public class commentlist {
    public String heading;
    private String id, idno, idpost ,comment, date, time;

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

    public commentlist(String id, String idno, String idpost, String comment, String date, String time) {
        this.id = id;
        this.idno = idno;
        this.idpost = idpost;
        this.comment = comment;
        this.date = date;
        this.time = time;
    }
}
