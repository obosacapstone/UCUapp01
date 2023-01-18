package edu.ucu.cite.jobportal;

public class courselist {
    public String heading;
    private String course;


    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public courselist(String course){
        this.course = course;

    }

}
